package com.pascaldierich.popularmoviesstage2.domain.repository;

import com.pascaldierich.popularmoviesstage2.data.network.model.pages.PageMovies;

/**
 * Created by Pascal Dierich on Jan, 2017.
 */

public interface MoviesRepository {

	PageMovies downloadPopularMovies();

	PageMovies downloadTopRatedMovies();

}
