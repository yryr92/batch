package com.spring.batch.job;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.reactive.function.client.WebClient;

import com.spring.batch.domain.ResponseDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleTasklet implements Tasklet {

    private WebClient webClient;

    @Override
    @Nullable
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // TODO Auto-generated method stub

        log.info(">>>> batch is working");
        String uri = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=00729736c099d786c372ad83e58762ae&targetDt=20240407";
        
        //String movie =  WebClient.create().get().uri(uri).retrieve().bodyToMono(String.class).block();
        ResponseDto r = WebClient.create().get().uri(uri).retrieve().bodyToMono(ResponseDto.class).block();

        log.info(">>>> 1st movie is {}", r.getBoxOfficeResult().getDailyBoxOfficeList().get(0).getMovieNm());

        return RepeatStatus.FINISHED;
    }
    
}
