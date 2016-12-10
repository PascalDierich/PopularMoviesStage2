package com.pascaldierich.popularmoviesstage2.data.network;

import com.pascaldierich.popularmoviesstage2.data.network.model.Movie;
import com.pascaldierich.popularmoviesstage2.data.network.services.DownloadService;
import com.pascaldierich.popularmoviesstage2.domain.repository.MoviesRepository;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

/**
 * Created by pascaldierich on 10.12.16.
 */

public class MoviesRepositoryImplementation implements MoviesRepository {

    private DownloadService mClient;

    public MoviesRepositoryImplementation() {
        this.mClient = RestClient.getService(DownloadService.class);
    }

    @Override
    public List<Movie> downloadPopularMovies() {
        Call<List<Movie>> call = mClient.getPopular("");
        List<Movie> movieList = null;

        try {
            movieList = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movieList;
    }

    @Override
    public List<Movie> downloadTopRatedMovies() {
        Call<List<Movie>> call = mClient.getTopRated(""); // TODO: Api_key ???
        List<Movie> movieList = null;

        try {
            movieList = call.execute().body();
        } catch (IOException e) {
            e.fillInStackTrace();
        }

        return movieList;
    }
}
