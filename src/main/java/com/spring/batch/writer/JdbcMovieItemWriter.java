package com.spring.batch.writer;

import java.util.List;
import java.util.ArrayList;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.lang.NonNull;

import com.spring.batch.domain.Movie;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JdbcMovieItemWriter<T> implements ItemWriter<List<Movie>> {

    private final JdbcBatchItemWriter<T> delegate;

    @Override
    public void write(@NonNull Chunk<? extends List<Movie>> chunk) throws Exception {
        

    }



    
}
