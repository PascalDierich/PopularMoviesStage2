package com.pascaldierich.popularmoviesstage2.domain.repository;

import com.pascaldierich.popularmoviesstage2.data.network.model.Movie;

import java.util.List;

/**
 * Created by pascaldierich on 10.12.16.
 */

public interface MoviesRepository {

    List<Movie> downloadPopularMovies();

    List<Movie> downloadTopRatedMovies();

}
