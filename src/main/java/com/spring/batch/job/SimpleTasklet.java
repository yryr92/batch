package com.spring.batch.job;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleTasklet implements Tasklet {

    private WebClient webClient;

    @Override
    @Nullable
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // TODO Auto-generated method stub

        log.info(">>>> batch is working");

        

        return RepeatStatus.FINISHED;
    }
    
}
