package com.pascaldierich.popularmoviesstage2.domain.repository;

import com.pascaldierich.popularmoviesstage2.data.network.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pascaldierich on 10.12.16.
 */

public interface MoviesRepository {

    ArrayList<Movie> downloadPopularMovies();

    ArrayList<Movie> downloadTopRatedMovies();

}
