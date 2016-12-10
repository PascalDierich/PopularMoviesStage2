package com.pascaldierich.popularmoviesstage2.data.network.services;

import com.pascaldierich.popularmoviesstage2.data.network.model.Movie;
import com.pascaldierich.popularmoviesstage2.data.network.model.Review;
import com.pascaldierich.popularmoviesstage2.data.network.model.Trailer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by pascaldierich on 10.12.16.
 */

public interface DetailService {

    @GET("/movie/{id}/reviews?api_key={api_key}")
    Call<Review> getReviews(@Path("id") String id, @Path("api_key") String api_key);

    @GET("/movie/{id}/videos?api_key={api_key}")
    Call<Trailer> getTrailers(@Path("id") String id, @Path("api_key") String api_key);

}
