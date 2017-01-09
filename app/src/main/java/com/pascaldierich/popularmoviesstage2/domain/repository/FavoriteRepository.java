package com.pascaldierich.popularmoviesstage2.domain.repository;

/**
 * Created by pascaldierich on 08.12.16.
 */

import com.pascaldierich.popularmoviesstage2.data.storage.model.DataMovieObject;

import java.util.ArrayList;

/**
 * A sample repository with CRUD operations on a model.
 */
public interface FavoriteRepository {

	ArrayList<DataMovieObject> getFavoriteMovies();

}
