package com.pascaldierich.popularmoviesstage2.domain.repository;

import android.graphics.Bitmap;

/**
 * Created by pascaldierich on 12.12.16.
 */

public interface SaveMovieRepository {

    boolean saveAsFavorite(String[] detailInfo, Bitmap bitmap);

}
