package com.spring.batch.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto {

    BoxOfficeResult boxOfficeResult;
    int page;
    List<Movie> results;
    
}
