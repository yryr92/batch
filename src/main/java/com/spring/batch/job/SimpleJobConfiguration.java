package com.spring.batch.job;

import java.util.List;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.transaction.PlatformTransactionManager;

import com.spring.batch.domain.KobisMovie;
import com.spring.batch.domain.Movie;
import com.spring.batch.domain.ResponseDto;
import com.spring.batch.processor.MovieItemProcessor;
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
                     .start(movieStep(jobRepository, null))
                     .build();
    }

    @Bean
    public Step movieStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("movieStep", jobRepository)
            .<List<Movie>, List<Movie>>chunk(1, transactionManager)
            .reader(movieItemReader())
            .writer(movieItemWriter())
            .build();
        
    }

    @Bean
    public ItemReader<List<Movie>> movieItemReader() {
        return new MovieItemReader<>();
        
    }

    @Bean
    public ItemProcessor<ResponseDto, List<KobisMovie>> movieItemProcessor() {
        return new MovieItemProcessor();
    }

    @Bean
    public ItemWriter<List<Movie>> movieItemWriter() {
        return new ItemWriter<List<Movie>>() {

            @Override
            public void write(@NonNull Chunk<? extends List<Movie>> chunk) throws Exception {

                for(int i = 0; i < chunk.getItems().get(0).size(); i++) {
                    log.info(">>> what movie is ... {}", chunk.getItems().get(0).get(i).toString());
                }

            }
            
        };
        
    }

    @Bean
    public JdbcBatchItemWriter<List<Movie>> jdbcMovieItemWriter(DataSource dataSource, PagingQueryProvider queryProvider) {
        return null;
        
    }



    
}
