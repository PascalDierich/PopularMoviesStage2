package com.pascaldierich.popularmoviesstage2.data.network.services;

import com.pascaldierich.popularmoviesstage2.data.network.model.Movie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by pascaldierich on 10.12.16.
 */

public interface DownloadService {

    @GET("movie/popular")
    Call<ArrayList<Movie>> getPopular(@Query("api_key") String api_key);

    @GET("movie/top_rated")
    Call<ArrayList<Movie>> getTopRated(@Query("api_key") String api_key);

}
