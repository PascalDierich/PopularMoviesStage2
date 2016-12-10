package com.pascaldierich.popularmoviesstage2.data.network.services;

import com.pascaldierich.popularmoviesstage2.data.network.model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by pascaldierich on 10.12.16.
 */

public interface DownloadService {

    @GET("/movie/popular?api_key={api_key}")
    Call<Movie> getPopular(@Path("api_key") String api_key);

    @GET("/movie/top_rated?api_key={api_key}")
    Call<Movie> getTopRated(@Path("api_key") String api_key);

}
