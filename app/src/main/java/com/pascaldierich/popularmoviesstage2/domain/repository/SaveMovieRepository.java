package com.pascaldierich.popularmoviesstage2.domain.repository;

import com.pascaldierich.popularmoviesstage2.data.storage.model.DataMovieObject;

/**
 * Created by Pascal Dierich on Jan, 2017.
 */

public interface SaveMovieRepository {

	boolean saveAsFavorite(DataMovieObject movieObject);

}
