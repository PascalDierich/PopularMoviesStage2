package com.pascaldierich.popularmoviesstage2.data.network.services;

import com.pascaldierich.popularmoviesstage2.data.network.model.Movie;
import com.pascaldierich.popularmoviesstage2.data.network.model.PageReviews;
import com.pascaldierich.popularmoviesstage2.data.network.model.PageTrailers;
import com.pascaldierich.popularmoviesstage2.data.network.model.Review;
import com.pascaldierich.popularmoviesstage2.data.network.model.Trailer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by pascaldierich on 10.12.16.
 */

public interface DetailService {

    @GET("3/movie/{id}/reviews")
    Call<PageReviews> getReviews(@Path("id") int id, @Query("api_key") String api_key);

    @GET("3/movie/{id}/videos")
    Call<PageTrailers> getTrailers(@Path("id") int id, @Query("api_key") String api_key);

}
