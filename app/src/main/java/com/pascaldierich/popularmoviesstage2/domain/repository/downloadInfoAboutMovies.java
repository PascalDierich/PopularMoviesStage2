package com.pascaldierich.popularmoviesstage2.domain.repository;

import com.pascaldierich.popularmoviesstage2.data.network.model.Review;
import com.pascaldierich.popularmoviesstage2.data.network.model.Trailer;

import java.util.List;

/**
 * Created by pascaldierich on 10.12.16.
 */

public interface downloadInfoAboutMovies {

    List<Trailer> downloadTrailer();

    List<Review> downloadReviews();
}
