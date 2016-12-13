package com.pascaldierich.popularmoviesstage2.data.network;

import android.util.Log;

import com.pascaldierich.popularmoviesstage2.data.network.model.pages.PageMovies;
import com.pascaldierich.popularmoviesstage2.data.network.services.DownloadService;
import com.pascaldierich.popularmoviesstage2.domain.repository.MoviesRepository;
import com.pascaldierich.popularmoviesstage2.utils.ConstantsHolder;

import java.io.IOException;

import retrofit2.Call;

/**
 * Created by pascaldierich on 10.12.16.
 */

public class MoviesRepositoryImpl implements MoviesRepository {

    private DownloadService mClient;

    public MoviesRepositoryImpl() {
        this.mClient = RestClient.getService(DownloadService.class);
    }

    @Override
    public PageMovies downloadPopularMovies() {
        PageMovies movieList;

        try {
            movieList = mClient.getPopular(ConstantsHolder.getApiKey()).execute().body();

            return movieList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // TODO
    }

    @Override
    public PageMovies downloadTopRatedMovies() {
        Call<PageMovies> call = mClient.getTopRated(ConstantsHolder.getApiKey());
        PageMovies movieList = null;

        try {
            movieList = call.execute().body();
            return movieList;
        } catch (IOException e) {
            e.fillInStackTrace();
        }

        return null;
    }
}
