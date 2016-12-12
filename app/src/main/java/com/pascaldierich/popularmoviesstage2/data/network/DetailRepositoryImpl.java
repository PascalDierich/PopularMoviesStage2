package com.pascaldierich.popularmoviesstage2.data.network;

import android.util.Log;

import com.pascaldierich.popularmoviesstage2.data.network.model.pages.PageReviews;
import com.pascaldierich.popularmoviesstage2.data.network.model.pages.PageTrailers;
import com.pascaldierich.popularmoviesstage2.data.network.services.DetailService;
import com.pascaldierich.popularmoviesstage2.domain.repository.DetailInfoMoviesRepository;

import java.io.IOException;

/**
 * Created by pascaldierich on 12.12.16.
 */

public class DetailRepositoryImpl implements DetailInfoMoviesRepository {
    private static final String LOG_TAG = DetailRepositoryImpl.class.getSimpleName();

    private DetailService mClient;

    public DetailRepositoryImpl() {
        this.mClient = RestClient.getService(DetailService.class);
    }

    @Override
    public PageTrailers downloadTrailer(int id) {
        PageTrailers page;

        try {
            page = mClient.getTrailers(id, "5c359398433009bb5d168d4cfb3e5cf3").execute().body(); // TODO: get id

            return page;
        } catch (IOException e) {
            Log.d(LOG_TAG, "downloadPopularMovies: Exception while call.execute.body() --> \n" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            Log.e(LOG_TAG, "downloadPopularMovies: Exception while retrofite.execute " + "\n" +
                    " --> " + e.fillInStackTrace());
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public PageReviews downloadReviews(int id) {
        PageReviews page;
        try {
            page = mClient.getReviews(id, "5c359398433009bb5d168d4cfb3e5cf3").execute().body(); // TODO: get id

            return page;
        } catch (IOException e) {
            Log.d(LOG_TAG, "downloadPopularMovies: Exception while call.execute.body() --> \n" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            Log.e(LOG_TAG, "downloadPopularMovies: Exception while retrofit.execute " + "\n" +
                    " --> " + e.fillInStackTrace());
            e.printStackTrace();
        }
        return null;
    }

}
