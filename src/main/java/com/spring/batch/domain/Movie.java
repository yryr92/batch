package com.spring.batch.domain;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Movie {
    @Id
    int id;
    String title;
    String poster_path;
    String overview;
    double popularity;
    String release_date;
    boolean video;
    double vote_average;
    int vote_count;
    String fileDownloadYn;
}
