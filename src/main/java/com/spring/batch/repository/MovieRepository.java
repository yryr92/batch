package com.spring.batch.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.batch.domain.Movie;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class MovieRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public MovieRepository(JdbcTemplate jdbcTemplate) {
      this.jdbcTemplate = jdbcTemplate;
    }


    public void saveAllMovies(List<Movie> lists) {

        //String sql = "inset into movies values(:id, :title, :backdrop_path, :overview, :popularity, :release_date, :video, :vote_average, :vote_count)";
        String sql = "insert into movies values(?, ?, ?, ?, ?, ?, ?, ?, ?, 'default')";
        
        //NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

        for(Movie m : lists) {
            // BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(m);
            
            log.info(">>> movie is {}", m.toString());
            jdbcTemplate.update(sql, m.getId(), m.getTitle(), m.getBackdrop_path(), m.getOverview(), 
                m.getPopularity(), m.getRelease_date(), m.isVideo(), m.getVote_average(), m.getVote_count());

            //namedParameterJdbcTemplate.queryForObject(sql, param, Movie.class);
        }
    }
    
}
