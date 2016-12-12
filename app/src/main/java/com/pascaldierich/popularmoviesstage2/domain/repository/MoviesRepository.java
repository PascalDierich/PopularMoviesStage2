package com.pascaldierich.popularmoviesstage2.domain.repository;

import com.pascaldierich.popularmoviesstage2.data.network.model.pages.PageMovies;

/**
 * Created by pascaldierich on 10.12.16.
 */

public interface MoviesRepository {

    PageMovies downloadPopularMovies();

    PageMovies downloadTopRatedMovies();

}
