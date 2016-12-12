package com.pascaldierich.popularmoviesstage2.domain.repository;

import com.pascaldierich.popularmoviesstage2.data.network.model.pages.PageReviews;
import com.pascaldierich.popularmoviesstage2.data.network.model.pages.PageTrailers;

/**
 * Created by pascaldierich on 10.12.16.
 */

public interface DetailInfoMoviesRepository {

    PageTrailers downloadTrailer(int id);

    PageReviews downloadReviews(int id);
}
