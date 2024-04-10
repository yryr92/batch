package com.spring.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.transaction.PlatformTransactionManager;

import com.spring.batch.domain.Movie;
import com.spring.batch.domain.ResponseDto;
import com.spring.batch.reader.MovieItemReader;

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

    @Bean
    public Job movieJob(JobRepository jobRepository) {
        return new JobBuilder("movieJob", jobRepository)
                     .start(simpleStep(jobRepository, null))
                     .build();
    }

    @Bean
    public Step movieStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("movieStep", jobRepository)
            .<ResponseDto, Movie>chunk(10, transactionManager)
            .reader(movieItemReader())
            .writer(movieItemWriter())
            .build();
        
    }

    @Bean
    public ItemReader<ResponseDto> movieItemReader() {
        return new MovieItemReader<>();
        
    }

    @Bean
    public ItemWriter<Movie> movieItemWriter() {
        return new ItemWriter<Movie>() {

            @Override
            public void write(@NonNull Chunk<? extends Movie> chunk) throws Exception {
                // TODO Auto-generated method stub
                for(Movie item : chunk.getItems()) {
                    log.info(">>> movie is {}", item.toString());
                }


            }
            
        };
        
    }



    
}
