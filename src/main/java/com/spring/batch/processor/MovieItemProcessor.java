package com.spring.batch.processor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.spring.batch.domain.Movie;
import com.spring.batch.repository.MovieRepository;

public class MovieItemProcessor implements ItemProcessor<List<Movie>, List<Movie>> {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    @Nullable
    public List<Movie> process(@NonNull List<Movie> item) throws Exception {
        // TODO Auto-generated method stub

        movieRepository.saveAllMovies(item);

        return item;

    }
    
}
