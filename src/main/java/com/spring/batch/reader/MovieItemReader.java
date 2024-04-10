package com.spring.batch.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.lang.Nullable;
import org.springframework.web.reactive.function.client.WebClient;

import com.spring.batch.domain.ResponseDto;

public class MovieItemReader<T> implements ItemReader<ResponseDto> {

    @Override
    @Nullable
    public ResponseDto read()
            throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'read'");

        String uri = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=00729736c099d786c372ad83e58762ae&targetDt=20240407";
        
        ResponseDto r = WebClient.create().get().uri(uri).retrieve().bodyToMono(ResponseDto.class).block();

        return r;


    }

    
}
