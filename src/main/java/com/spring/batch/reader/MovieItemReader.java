package com.spring.batch.reader;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.lang.Nullable;
import org.springframework.web.reactive.function.client.WebClient;

import com.spring.batch.domain.Movie;
import com.spring.batch.domain.ResponseDto;

public class MovieItemReader<T> implements ItemReader<List<Movie>> {

    private int cnt = 0;

    
    @Override
    @Nullable
    public List<Movie> read()
            throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'read'");

        cnt++;
        
        // kobis
        //String uri = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=00729736c099d786c372ad83e58762ae&targetDt=20240407";
        
        String uri = "https://api.themoviedb.org/3/movie/popular?api_key=c42d20147c1790c9b633ffe3662cd13e&language=en-US&page=1";

        ResponseDto r = WebClient.create().get().uri(uri).retrieve().bodyToMono(ResponseDto.class).block();
        
        return cnt == 1 ? r.getResults() : null;

    }

    
}
