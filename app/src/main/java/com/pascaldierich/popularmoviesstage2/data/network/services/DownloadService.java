package com.pascaldierich.popularmoviesstage2.data.network.services;

import com.pascaldierich.popularmoviesstage2.data.network.model.PageMovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pascaldierich on 10.12.16.
 */

public interface DownloadService {

    @GET("3/movie/popular")
    Call<PageMovies> getPopular(@Query("api_key") String api_key);

    @GET("3/movie/top_rated")
    Call<PageMovies> getTopRated(@Query("api_key") String api_key);

}
