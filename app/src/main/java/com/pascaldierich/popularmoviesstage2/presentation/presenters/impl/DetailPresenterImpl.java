package com.pascaldierich.popularmoviesstage2.presentation.presenters.impl;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.pascaldierich.popularmoviesstage2.R;
import com.pascaldierich.popularmoviesstage2.data.network.model.pages.PageReviews;
import com.pascaldierich.popularmoviesstage2.data.network.model.pages.PageTrailers;
import com.pascaldierich.popularmoviesstage2.domain.executor.Executor;
import com.pascaldierich.popularmoviesstage2.domain.executor.MainThread;
import com.pascaldierich.popularmoviesstage2.domain.interactors.DownloadInfoForMovieInteractor;
import com.pascaldierich.popularmoviesstage2.domain.interactors.QuerySelectedMovieInteractor;
import com.pascaldierich.popularmoviesstage2.domain.interactors.SaveFavoriteMovieInteractor;
import com.pascaldierich.popularmoviesstage2.domain.interactors.impl.DownloadInfoReviewsInteractorImpl;
import com.pascaldierich.popularmoviesstage2.domain.interactors.impl.DownloadInfoTrailersInteractorImpl;
import com.pascaldierich.popularmoviesstage2.domain.interactors.impl.SaveFavoriteMovieInteractorImpl;
import com.pascaldierich.popularmoviesstage2.domain.repository.DetailInfoMoviesRepository;
import com.pascaldierich.popularmoviesstage2.domain.repository.FavoriteRepository;
import com.pascaldierich.popularmoviesstage2.domain.repository.SaveMovieRepository;
import com.pascaldierich.popularmoviesstage2.presentation.converters.Converter;
import com.pascaldierich.popularmoviesstage2.presentation.converters.model.DetailMovieObject;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.DetailPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.base.AbstractPresenter;

/**
 * Created by pascaldierich on 10.12.16.
 */

public class DetailPresenterImpl extends AbstractPresenter implements DetailPresenter,
		DownloadInfoForMovieInteractor.Callback,
		SaveFavoriteMovieInteractor.Callback,
		QuerySelectedMovieInteractor.Callback {
	private static final String LOG_TAG = DetailPresenterImpl.class.getSimpleName();

	private DetailPresenter.View mView;
	private DetailInfoMoviesRepository mDetailRepository;
	private SaveMovieRepository mSaveRepository;
	private FavoriteRepository mFavoriteRepository;

	private int mMovieInternId;
	private DetailMovieObject mDetailMovieObject;

	private Uri mMovieUri;

	public DetailPresenterImpl(Executor executor,
							   MainThread mainThread,
							   Bundle savedInstanceState,
							   DetailPresenter.View view,
							   DetailInfoMoviesRepository detailRepository,
							   SaveMovieRepository saveRepository,
							   FavoriteRepository faveRepository,
							   DetailMovieObject movie) {
		super(executor, mainThread, savedInstanceState);
		this.mView = view;
		this.mDetailRepository = detailRepository;
		this.mSaveRepository = saveRepository;
		this.mFavoriteRepository = faveRepository;

		// TODO: 18.12.16 if app starts and no movie selected -> get 1 movie -> do in MainActivity / MainPresenter
		try {
			showGivenData(movie);

			if (this.mView.checkConnection()) {
				getTrailer(movie.getmId());
				getReviews(movie.getmId());
			}
		} catch (NullPointerException npe) {
			Log.e(LOG_TAG, "DetailPresenterImpl: NullPointerException: " + npe.fillInStackTrace());
			// TODO: 18.12.16 call mainActivity to try download again -> checkForConnection 
		}
	}

	@Override
	public void onLoadFinished(String[] movie) {
		Log.d(LOG_TAG, "onLoadFinished: REALLY? GOT IT :D " + (movie != null));
	}

	@Override
	public void showGivenData(@Nullable DetailMovieObject movie) {
		if (movie == null) {
			Log.d(LOG_TAG, "showGivenData: movie == null");
			return;
		}
		Log.d(LOG_TAG, "showGivenData: movie should NOT be null -> " + (movie == null));

		Log.d(LOG_TAG, "showGivenData: " + movie.getmTitle());
		Log.d(LOG_TAG, "showGivenData: " + movie.getmDescription());

		mView.showGivenData(movie);
		// TODO: 14.12.16 mView.mTextViewTitle.setText(this.mDetailMovieObject) ... etc
	}

	@Override
	public void getTrailer(int id) {
		mView.showTrailerProgress();
		if (id == R.integer.error_internCommunication_missingInfo) {
			onError(R.integer.error_internCommunication_missingInfo);
		}
		DownloadInfoForMovieInteractor interactor = new DownloadInfoTrailersInteractorImpl(
				mExecutor,
				mMainThread,
				this,
				mDetailRepository,
				id
		);
		interactor.execute();
	}

	@Override
	public void onPlayPressed(String base_url, String key) {
		Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(base_url + key));
		mView.startNewActivity(i);
	}

	@Override
	public void getReviews(int id) {
		mView.showReviewProgress();
		if (id == R.integer.error_internCommunication_missingInfo) {
			onError(R.integer.error_internCommunication_missingInfo);
		}
		DownloadInfoForMovieInteractor interactor = new DownloadInfoReviewsInteractorImpl(
				mExecutor,
				mMainThread,
				this,
				mDetailRepository,
				id // TODO: get id
		);
		interactor.execute();
	}

	@Override
	public void saveAsFavorite(DetailMovieObject object) {
		SaveFavoriteMovieInteractor interactor = new SaveFavoriteMovieInteractorImpl(
				mExecutor,
				mMainThread,
				this,
				mSaveRepository,
				Converter.DetailMovieObjectToDataMovieObject(object)
		);
		interactor.execute();
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
		Log.d(LOG_TAG, "onError: Error Code: " + code);
		// TODO: 14.12.16 handle Errors
	}

	@Override
	public void onDownloadTrailerFinish(PageTrailers page) {
		Log.d(LOG_TAG, "onDownloadTrailerFinish: Got It!");
		// TODO: mView.showTrailer()
		if (page != null) mView.showTrailer(page);
		else onError(R.integer.error_network_failedDownload);
	}

	@Override
	public void onDownloadReviewFinish(PageReviews page) {
		Log.d(LOG_TAG, "onDownloadReviewFinish: Got It!");
		if (page != null) mView.showReview(page);
		else onError(R.integer.error_network_failedDownload);
	}

	@Override
	public void onSaveFinish(boolean success) {
		if (success) {
			Log.d(LOG_TAG, "onSaveFinish: Got It!");
		} else {
			Log.d(LOG_TAG, "onSaveFinish: Bad Luck :(");
		}
	}
}
