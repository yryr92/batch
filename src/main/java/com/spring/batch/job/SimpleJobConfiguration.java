package com.spring.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j // log 사용을 위한 lombok 어노테이션
@RequiredArgsConstructor // 생성자 DI를 위한 lombok 어노테이션
@Configuration
public class SimpleJobConfiguration {

    @Bean
    public Job simpleJob(JobRepository jobRepository) {
    return new JobBuilder("simpleJob", jobRepository)
                     .start(simpleStep(jobRepository, null))
                     .build();
    }

    @Bean
    public Step simpleStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("simpleStep", jobRepository)
    			.tasklet(simpleTasklet(), transactionManager)
    			.build();
        
    }

    @Bean
    public SimpleTasklet simpleTasklet() {
        return new SimpleTasklet();
        
    }

    
}
