package com.pascaldierich.popularmoviesstage2.domain.repository;

/**
 * Created by pascaldierich on 10.12.16.
 */

public interface MoviesRepository {

    void downloadPopularMovies();

    void downloadTopRatedMovies();

}
