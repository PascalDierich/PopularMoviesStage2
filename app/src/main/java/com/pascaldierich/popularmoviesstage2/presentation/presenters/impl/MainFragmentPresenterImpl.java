package com.pascaldierich.popularmoviesstage2.presentation.presenters.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.pascaldierich.popularmoviesstage2.R;
import com.pascaldierich.popularmoviesstage2.data.network.model.pages.PageMovies;
import com.pascaldierich.popularmoviesstage2.data.storage.model.DataMovieObject;
import com.pascaldierich.popularmoviesstage2.domain.executor.Executor;
import com.pascaldierich.popularmoviesstage2.domain.executor.MainThread;
import com.pascaldierich.popularmoviesstage2.domain.interactors.DownloadMoviesInteractor;
import com.pascaldierich.popularmoviesstage2.domain.interactors.QueryFavoriteMoviesInteractor;
import com.pascaldierich.popularmoviesstage2.domain.interactors.impl.DownloadPopularMoviesInteractorImpl;
import com.pascaldierich.popularmoviesstage2.domain.interactors.impl.DownloadTopRatedMoviesInteractorImpl;
import com.pascaldierich.popularmoviesstage2.domain.interactors.impl.QueryFavoriteInteractorImpl;
import com.pascaldierich.popularmoviesstage2.domain.repository.FavoriteRepository;
import com.pascaldierich.popularmoviesstage2.domain.repository.MoviesRepository;
import com.pascaldierich.popularmoviesstage2.presentation.converters.Converter;
import com.pascaldierich.popularmoviesstage2.presentation.converters.model.DetailMovieObject;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.MainFragmentPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.base.AbstractPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.ui.callback.MovieSelectedCallback;
import com.pascaldierich.popularmoviesstage2.utils.Utility;

import java.util.ArrayList;

/**
 * Created by pascaldierich on 12.12.16.
 */

public class MainFragmentPresenterImpl extends AbstractPresenter implements MainFragmentPresenter,
		DownloadMoviesInteractor.Callback,
		QueryFavoriteMoviesInteractor.Callback {
	private static final String LOG_TAG = MainFragmentPresenterImpl.class.getSimpleName();

	private MainFragmentPresenter.View mView;
	private MoviesRepository mMoviesRepository;
	private FavoriteRepository mFavoriteRepository;

	private SharedPreferences mSharedPreferences;

	private ArrayList<DetailMovieObject> mDetailMovieObjectArrayList;

	private Bundle mStateBundle = new Bundle();

	private boolean stateChanged = false;
	
	public MainFragmentPresenterImpl(Executor executor,
									 MainThread mainThread,
									 Bundle savedInstanceState,
									 MainFragmentPresenter.View view,
									 MoviesRepository moviesRepository,
									 FavoriteRepository favoriteRepository) {
		super(executor, mainThread, savedInstanceState);
		this.mView = view;
		this.mMoviesRepository = moviesRepository;
		this.mFavoriteRepository = favoriteRepository;

		this.mSharedPreferences = mView.getPreferences();
		
		if (super.mSavedInstanceState == null) {
			getInitialData();
		} else {
			Log.d(LOG_TAG, "MainFragmentPresenterImpl: going to show Movies from Bundle in saved State");
			mView.showMovies(
					Converter.ArrayListWithDetailMovieObjectToArrayListWithGridItem(
							super.mSavedInstanceState.<DetailMovieObject> getParcelableArrayList(
									mView.getApplicationContext().getString(R.string.main_fragment_downloaded_data)
							)
					)
			);
			Log.d(LOG_TAG, "MainFragmentPresenterImpl: whoo... worked");
		}
	}

	public void setView(MainFragmentPresenter.View view) {
		this.mView = view;
	}

	private void getInitialData() {

		int initialPreference = this.mSharedPreferences.getInt(mView.getApplicationContext().getString(R.string.preferences_initial_sort), 1);
		
		switch (initialPreference) {
			case R.integer.preferences_initial_sort_popularity: {
				Log.d(LOG_TAG, "getInitialData: getPopular");

				if (!Utility.checkConnection((ConnectivityManager) mView.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE))) {
					onError(R.integer.error_network_noInternet);
					return;
				}

				getPopularMovies();
				break;
			}
			case R.integer.preferences_initial_sort_rating: {
				Log.d(LOG_TAG, "getInitialData: getRating");

				if (!Utility.checkConnection((ConnectivityManager) mView.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE))) {
					onError(R.integer.error_network_noInternet);
					return;
				}

				getTopRatedMovies();
				break;
			}
			case R.integer.preferences_initial_sort_favorites: {
				Log.d(LOG_TAG, "getInitialData: getFavorites");

				getFavoriteMovies();
				break;
			}
			default: {
				Log.d(LOG_TAG, "getInitialData: initialPreference = " + initialPreference);
				getPopularMovies();
			}
		}
	}

	@Override
	public void resume() {
		// if state changed since creation
		if (stateChanged) {
			Log.d(LOG_TAG, "resume: state changed. Going to show saved Data");
			mView.showMovies(
					Converter.ArrayListWithDetailMovieObjectToArrayListWithGridItem(
							super.mSavedInstanceState.<DetailMovieObject> getParcelableArrayList(
									mView.getApplicationContext().getString(R.string.main_fragment_downloaded_data)
							)
					)
			);
		}
	}

	@Override
	public void pause() {
	}

	@Override
	public void stop() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void onError(int code) {
		switch (code) {
			case R.integer.error_network_failedDownload: {
				Log.d(LOG_TAG, "onError: DOWNLOAD_NULL");
				// TODO: tell User about Problem
				mView.showError("Download failed");
			}
			case R.integer.error_network_noInternet: {
				Log.d(LOG_TAG, "onError: NO_INTERNET");
				mView.showError("No Internet Connection");
			}
		}
	}

	@Override
	public void onDownloadFinish(PageMovies movies) {
		mView.hideProgress();

		if (movies == null || movies.getResults().size() == 0) {
			onError(R.integer.error_network_failedDownload);
			return;
		}

		ArrayList<DetailMovieObject> detailMovieObjectArrayList = Converter.PageMovieToArrayListDetailMovieObject(movies);
		this.mDetailMovieObjectArrayList = detailMovieObjectArrayList;

		saveState(this.mDetailMovieObjectArrayList);

		mView.showMovies(Converter.ArrayListWithDetailMovieObjectToArrayListWithGridItem(detailMovieObjectArrayList));
	}

	@Override
	public void movieSelected(int position) {
		Log.d(LOG_TAG, "movieSelected: on position: " + position);
		Bundle selectedMovie = new Bundle();
		DetailMovieObject selectedDetailMovieObject = this.mDetailMovieObjectArrayList.get(position);
		saveState(selectedDetailMovieObject);
		selectedMovie.putParcelable(mView.getApplicationContext().getString(R.string.parcelable_detail_movie_object_key),
				selectedDetailMovieObject);

		((MovieSelectedCallback) mView.getApplicationContext())
				.onMovieSelected(selectedMovie);
	}

	@Override
	public void getPopularMovies() {
		DownloadMoviesInteractor downloadInteractor = new DownloadPopularMoviesInteractorImpl(
				mExecutor,
				mMainThread,
				this,
				mMoviesRepository
		);
		downloadInteractor.execute();
	}

	@Override
	public void getTopRatedMovies() {
		DownloadMoviesInteractor downloadInteractor = new DownloadTopRatedMoviesInteractorImpl(
				mExecutor,
				mMainThread,
				this,
				mMoviesRepository
		);
		downloadInteractor.execute();
	}

	@Override
	public void getFavoriteMovies() {
		QueryFavoriteMoviesInteractor queryInteractor = new QueryFavoriteInteractorImpl(
				mExecutor,
				mMainThread,
				this,
				mFavoriteRepository
		);
		queryInteractor.execute();
	}

	@Override
	public void onQueryFinished(ArrayList<DataMovieObject> faveMovies) {
		mView.hideProgress();

		this.mDetailMovieObjectArrayList = Converter.convertDataMovieObjectToDetailMovieObject(faveMovies);

		Log.d(LOG_TAG, "onQueryFinished: faveMovies.size() = " + faveMovies.size());
		Log.d(LOG_TAG, "onQueryFinished: GOT IT!");

		for (DataMovieObject a : faveMovies) {
			Log.d(LOG_TAG, "");
			Log.d(LOG_TAG, "DataMovieObject from Database :");
			Log.d(LOG_TAG, "######################################################");
			Log.d(LOG_TAG, "DataMovieObject.getTitle = " + a.getmTitle());
			Log.d(LOG_TAG, "DataMovieObject.getRelease = " + a.getmRelease());
			Log.d(LOG_TAG, "DataMovieObject.getDescription = " + a.getmDescription());
			Log.d(LOG_TAG, "DataMovieObject.getRating = " + a.getmRating());
			Log.d(LOG_TAG, "DataMovieObject.getThumbnail.length = " + a.getmThumbnail().length);
			Log.d(LOG_TAG, "Trailers : ");
			for (String trailer : a.getTrailers()) {
				Log.d(LOG_TAG, "DataMovieObject.getTrailers = " + trailer);
			}
			Log.d(LOG_TAG, "######################################################");
		}

		mView.showMovies(
				Converter.ArrayListWithDetailMovieObjectToArrayListWithGridItem(
						this.mDetailMovieObjectArrayList
				)
		);
	}

	@Override
	public boolean onMenuItemSelected(int id) {
		switch (id) {
			case R.id.menu_popularity: {
				this.mSharedPreferences
						.edit()
						.putInt(mView.getApplicationContext().getString(R.string.preferences_initial_sort),
								R.integer.preferences_initial_sort_popularity)
						.apply();
				// TODO: 15.12.16
				break;
			}
			case R.id.menu_rating: {
				this.mSharedPreferences
						.edit()
						.putInt(mView.getApplicationContext().getString(R.string.preferences_initial_sort),
								R.integer.preferences_initial_sort_rating)
						.apply();
				break;
			}
			case R.id.menu_favorites: {
				this.mSharedPreferences
						.edit()
						.putInt(mView.getApplicationContext().getString(R.string.preferences_initial_sort),
								R.integer.preferences_initial_sort_favorites)
						.apply();
				break;
			}
			default: {
				return false;
			}
		}
		getInitialData();

		return true;
	}

	@Override
	public void saveState(@NonNull Object object) {
		stateChanged = true;
		
		Log.d(LOG_TAG, "saveState: going to save some states");

		// Check if Object represents downloaded Data
		try {
			mStateBundle.putParcelableArrayList(
					mView.getApplicationContext().getString(R.string.main_fragment_downloaded_data),
					(ArrayList<DetailMovieObject>) object
			);
			Log.d(LOG_TAG, "saveState: downloaded Data saved in StateBundle and is null = "
					+ (mStateBundle.getParcelableArrayList(mView.getApplicationContext().getString(R.string.main_fragment_downloaded_data)) == null));
			return;
		} catch (ClassCastException e) {
			Log.e(LOG_TAG, "saveState: ClassCastException" + "\n" +
					" --> " + e.fillInStackTrace());
		}

		// if not, check if it is the selectedMovie
		try {
			Log.d(LOG_TAG, "saveState: you shouldnt be here");
			mStateBundle.putParcelable(
					mView.getApplicationContext().getString(R.string.main_fragment_selected_movie),
					(DetailMovieObject) object);
			return;
		} catch (ClassCastException e) {
			Log.e(LOG_TAG, "saveState: ClassCastException" + "\n" +
					" --> " + e.fillInStackTrace());
		}

		Log.d(LOG_TAG, "saveState: Why Still here?!?!?!?!?!");
	}

	@Override
	public Bundle getState() {
		return this.mStateBundle;
	}
}
