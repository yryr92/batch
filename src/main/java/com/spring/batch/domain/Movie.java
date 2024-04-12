package com.spring.batch.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Movie {
    int id;
    String title;
    String backdrop_path;
    String[] genreIds;
    String originalLanguage;
    String originalTitle;
    String overview;
    double popularity;
    String posterPath;
    String releaseDate;
    boolean video;
    double voteAverage;
    int voteCount;
}
