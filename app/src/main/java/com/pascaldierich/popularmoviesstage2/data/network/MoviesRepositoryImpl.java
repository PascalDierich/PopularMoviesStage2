package com.pascaldierich.popularmoviesstage2.data.network;

import android.util.Log;

import com.pascaldierich.popularmoviesstage2.data.network.model.PageMovies;
import com.pascaldierich.popularmoviesstage2.data.network.services.DownloadService;
import com.pascaldierich.popularmoviesstage2.domain.repository.MoviesRepository;

import java.io.IOException;

import retrofit2.Call;

/**
 * Created by pascaldierich on 10.12.16.
 */

public class MoviesRepositoryImpl implements MoviesRepository {
    private static final String LOG_TAG = "MoviesRepositoryImpl";

    private DownloadService mClient;

    public MoviesRepositoryImpl() {
        this.mClient = RestClient.getService(DownloadService.class);
    }

    @Override
    public PageMovies downloadPopularMovies() {
        PageMovies movieList;

        try {
            movieList = mClient.getPopular("5c359398433009bb5d168d4cfb3e5cf3").execute().body();

            return movieList;
        } catch (IOException e) {
            Log.d(LOG_TAG, "downloadPopularMovies: Exception while call.execute.body() --> \n" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            Log.e(LOG_TAG, "downloadPopularMovies: Exception while retrofite.execute " + "\n" +
                    " --> " + e.fillInStackTrace());
            e.printStackTrace();
        }
        return null; // TODO
    }

    @Override
    public PageMovies downloadTopRatedMovies() {
        Call<PageMovies> call = mClient.getTopRated("5c359398433009bb5d168d4cfb3e5cf3"); // TODO: Api_key in strings.xml
        PageMovies movieList = null;

        try {
            movieList = call.execute().body();
        } catch (IOException e) {
            e.fillInStackTrace();
        }

        return movieList;
    }
}
