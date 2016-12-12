package com.pascaldierich.popularmoviesstage2.domain.repository;

import android.graphics.Bitmap;

import com.pascaldierich.popularmoviesstage2.data.storage.model.DataMovieObject;

/**
 * Created by pascaldierich on 12.12.16.
 */

public interface SaveMovieRepository {

    boolean saveAsFavorite(DataMovieObject movieObject);

}
