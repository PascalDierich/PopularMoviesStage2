package com.pascaldierich.popularmoviesstage2.data.network;

import android.os.Debug;
import android.os.Looper;
import android.util.Log;

import com.pascaldierich.popularmoviesstage2.data.network.model.Movie;
import com.pascaldierich.popularmoviesstage2.data.network.services.DownloadService;
import com.pascaldierich.popularmoviesstage2.domain.repository.MoviesRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Created by pascaldierich on 10.12.16.
 */

public class MoviesRepositoryImpl implements MoviesRepository {
    private static final String LOG_TAG = "MoviesRepositoryImpl";

    private DownloadService mClient;

    public MoviesRepositoryImpl() {
        this.mClient = RestClient.getService(DownloadService.class);
        Log.d(LOG_TAG, "MoviesRepositoryImpl: init()");
    }

    @Override
    public ArrayList<Movie> downloadPopularMovies() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.d(LOG_TAG, "downloadPopularMovies: Looper on Main");
        } else {
            Log.d(LOG_TAG, "downloadPopularMovies: Looper NOT on Main");
        }
        Log.d(LOG_TAG, "downloadPopularMovies: is executing");
//        Call<List<Movie>> call = mClient.getPopular("5c359398433009bb5d168d4cfb3e5cf3");
        ArrayList<Movie> movieList = null;

        Log.d(LOG_TAG, "downloadPopularMovies: Looper is on MainThread == " + (Looper.getMainLooper() == Looper.myLooper()));

        try {
            movieList = mClient.getPopular("5c359398433009bb5d168d4cfb3e5cf3").execute().body();


            Log.d(LOG_TAG, "downloadPopularMovies: is going to call download");
//            movieList = call.execute().body();
            Log.d(LOG_TAG, "downloadPopularMovies: finished without Exception");
        } catch (IOException e) {
            Log.d(LOG_TAG, "downloadPopularMovies: Exception while call.execute.body() --> \n" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d(LOG_TAG, "downloadPopularMovies: is returning movieList now");
        return movieList;
    }

    @Override
    public ArrayList<Movie> downloadTopRatedMovies() {
        Call<ArrayList<Movie>> call = mClient.getTopRated(""); // TODO: Api_key ???
        ArrayList<Movie> movieList = null;

        try {
            movieList = call.execute().body();
        } catch (IOException e) {
            e.fillInStackTrace();
        }

        return movieList;
    }
}
