package com.pascaldierich.popularmoviesstage2.presentation.presenters.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListView;

import com.pascaldierich.popularmoviesstage2.R;
import com.pascaldierich.popularmoviesstage2.data.network.model.pages.PageMovies;
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
import com.pascaldierich.popularmoviesstage2.presentation.ui.adapter.ImageAdapter;
import com.pascaldierich.popularmoviesstage2.presentation.ui.callback.MovieSelectedCallback;
import com.pascaldierich.popularmoviesstage2.utils.ConstantsHolder;
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

	private boolean mTwoPaneMode;

	private ImageAdapter mImageAdapter;

	private ListView mListView;
	private GridView mGridView;

	private DetailFragmentCallback mDetailFragmentCallback;

	private SharedPreferences mSharedPreferences;

	private ArrayList<DetailMovieObject> mDetailMovieObjectArrayList;

	public interface DetailFragmentCallback {
		void onItemSelected(int id);
	}
	
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

		getInitialData();
	}

	public void setView(MainFragmentPresenter.View view) {
		this.mView = view;
	}

	private void getInitialData() {
		if (!Utility.checkConnection((ConnectivityManager) mView.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE))) {
			onError(R.integer.error_network_noInternet);
			return;
		}

		int initialPreference = this.mSharedPreferences.getInt(mView.getApplicationContext().getString(R.string.preferences_initial_sort), -1);
		
		switch (initialPreference) {
			case R.integer.preferences_initial_sort_popularity: {
				Log.d(LOG_TAG, "getInitialData: getPopular");
				getPopularMovies();
				break;
			}
			case R.integer.preferences_initial_sort_rating: {
				Log.d(LOG_TAG, "getInitialData: getRating");
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
		// TODO: 14.12.16 checkConnection at the end
	}

	@Override
	public void onDownloadFinish(PageMovies movies) {
		mView.hideProgress();

		if (movies == null || movies.getResults().size() == 0) {
			onError(R.integer.error_network_failedDownload);
			return;
		}

		ArrayList<DetailMovieObject> movieObjectArrayList = Converter.PageMovieToArrayListDetailMovieObject(movies);

//		ConstantsHolder.setDownloadedData(movieObjectArrayList); // TODO: 15.12.16 FUCK ME !!

		this.mDetailMovieObjectArrayList = movieObjectArrayList;

		Log.d(LOG_TAG, "onDownloadFinish: DetailMovieObject.size() = " + movieObjectArrayList.size());

		mView.showMovies(Converter.ArrayListWithDetailMovieObjectToArrayListWithGridItem(movieObjectArrayList));
	}

	@Override
	public void movieSelected(int position) {
		// TODO: 15.12.16 or mabe give whole DetailMovieObject through Bundle with Arguments -> I think this is better XD
		if (ConstantsHolder.getTwoPaneMode()) { // inflate Fragment

			Bundle selectedMovie = new Bundle();
			selectedMovie.putParcelable("", this.mDetailMovieObjectArrayList.get(position));

			// Calls MainActivity with Callback and DetailMovieObject as Argument
			((MovieSelectedCallback) mView.getApplicationContext())
					.onMovieSelected(selectedMovie);

//			((MovieSelectedCallback) mView.getApplicationContext())
//					.onMovieSelected(MovieContract.MovieEntry.buildMovieUriWithId(position));

			
//			mDetailFragmentCallback = ConstantsHolder.getDetailPresenterImpl(); // TODO: 15.12.16 fuck static Objects !!!!!!
//			mDetailFragmentCallback.onItemSelected(position);

		} else { // start new Activity
			mView.startDetailActivity(position); // TODO: 15.12.16 give DetailMovieObject through Bundle
		}
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
	public void onQueryFinished(ArrayList<String[]> faveMovies) { // TODO: convert
		Log.d(LOG_TAG, "onQueryFinished: GOT IT!");
	}

	@Override
	public boolean onMenuItemSelected(int id) {
		Log.d(LOG_TAG, "onMenuItemSelected: with id = " + id);
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

}
