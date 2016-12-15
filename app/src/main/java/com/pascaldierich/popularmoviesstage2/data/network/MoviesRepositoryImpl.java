package com.pascaldierich.popularmoviesstage2.data.network;

import android.util.Log;

import com.pascaldierich.popularmoviesstage2.data.network.model.pages.PageMovies;
import com.pascaldierich.popularmoviesstage2.data.network.services.DownloadService;
import com.pascaldierich.popularmoviesstage2.domain.repository.MoviesRepository;
import com.pascaldierich.popularmoviesstage2.utils.ConstantsHolder;

import java.io.IOException;

/**
 * Created by pascaldierich on 10.12.16.
 */

public class MoviesRepositoryImpl implements MoviesRepository {
	private static final String LOG_TAG = MoviesRepositoryImpl.class.getSimpleName();

	private DownloadService mClient;

	public MoviesRepositoryImpl() {
		this.mClient = RestClient.getService(DownloadService.class);
	}

	@Override
	public PageMovies downloadPopularMovies() {
		PageMovies movieList;

		try {
//            movieList = mClient.getPopular(ConstantsHolder.getApiKey()).execute().body();
			movieList = mClient.getPopular("5c359398433009bb5d168d4cfb3e5cf3").execute().body(); // TODO: 14.12.16 change api_key to Constant
			Log.d(LOG_TAG, "downloadPopularMovies: api_key = " + ConstantsHolder.getApiKey());
			Log.d(LOG_TAG, "downloadPopularMovies: movieList.results.size() : " + movieList.getResults().size());
			return movieList;
		} catch (IOException e) {
			Log.e(LOG_TAG, "downloadPopularMovies: " + "\n" +
					" --> " + e.fillInStackTrace());
		}
		return null;
	}

	@Override
	public PageMovies downloadTopRatedMovies() {
		PageMovies movieList;

		try {
			movieList = mClient.getPopular(ConstantsHolder.getApiKey()).execute().body();
			return movieList;
		} catch (IOException e) {
			e.fillInStackTrace();
		}
		return null;
	}
}
