package com.pascaldierich.popularmoviesstage2.presentation.presenters.impl;

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
import com.pascaldierich.popularmoviesstage2.domain.interactors.impl.QuerySelectedMovieInteractorImpl;
import com.pascaldierich.popularmoviesstage2.domain.interactors.impl.SaveFavoriteMovieInteractorImpl;
import com.pascaldierich.popularmoviesstage2.domain.repository.DetailInfoMoviesRepository;
import com.pascaldierich.popularmoviesstage2.domain.repository.FavoriteRepository;
import com.pascaldierich.popularmoviesstage2.domain.repository.SaveMovieRepository;
import com.pascaldierich.popularmoviesstage2.presentation.converters.Converter;
import com.pascaldierich.popularmoviesstage2.presentation.converters.model.DetailMovieObject;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.DetailPresenter;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.base.AbstractPresenter;
import com.pascaldierich.popularmoviesstage2.utils.ConstantsHolder;

/**
 * Created by pascaldierich on 10.12.16.
 */

public class DetailPresenterImpl extends AbstractPresenter implements DetailPresenter,
		DownloadInfoForMovieInteractor.Callback,
		SaveFavoriteMovieInteractor.Callback,
		MainFragmentPresenterImpl.DetailFragmentCallback,
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
							   Bundle arguments) {
		super(executor, mainThread, savedInstanceState);
		this.mView = view;
		this.mDetailRepository = detailRepository;
		this.mSaveRepository = saveRepository;
		this.mFavoriteRepository = faveRepository;

		Log.d(LOG_TAG, "DetailPresenterImpl: arguments == null -> " + (arguments == null));

		try {
			Log.d(LOG_TAG, "DetailPresenterImpl: arguments.getBundle == null -> " + (arguments.getBundle("") == null));
			Log.d(LOG_TAG, "DetailPresenterImpl: arguments.getBundle.get == null -> " + (arguments.getBundle("").get("") == null));

			showGivenData((DetailMovieObject) arguments.getBundle("").get(""));
		} catch (NullPointerException npe) {
			Log.e(LOG_TAG, "DetailPresenterImpl: NullPointerException: " + npe.fillInStackTrace());
		} catch (ClassCastException cce) {
			Log.d(LOG_TAG, "DetailPresenterImpl: ClassCastException");
		}


//		checkUri(arguments);

		// if twoPaneMode == false -> movie got selected in 1. Activity
		// otherwise wait for Callback ( onItemSelected(int id) )
//		if (!ConstantsHolder.getTwoPaneMode()) {
//			Log.d(LOG_TAG, "DetailPresenterImpl: twoPaneMode = false");
//			checkSelectedMovie();
//		}
//
//		ConstantsHolder.setDetailPresenterImpl(this);
	}

	/*
	Deprecated
	 */
	public void checkUri(Bundle selectedMovie) {
		if (selectedMovie != null) {
			this.mMovieUri = selectedMovie.getParcelable("");
		} else {
			onError(-5); // TODO: 15.12.16 define Code
			return;
		}
		getDetailMovieObject2();
	}

	public void getDetailMovieObject2() {
		Log.d(LOG_TAG, "getDetailMovieObject2: going to execute interactor");
		QuerySelectedMovieInteractor interactor = new QuerySelectedMovieInteractorImpl(
				mExecutor,
				mMainThread,
				this,
				mFavoriteRepository,
				this.mMovieUri
		);
		interactor.execute();
	}

	@Override
	public void onLoadFinished(String[] movie) {
		Log.d(LOG_TAG, "onLoadFinished: REALLY? GOT IT :D " + (movie != null));
	}

	/*
		Deprecated
		 */
	@Override
	public void checkSelectedMovie() {
		this.mMovieInternId = mView.getSelectedMovieId();
		Log.d(LOG_TAG, "checkSelectedMovie: intern Movie Id = " + mMovieInternId);
		if (this.mMovieInternId == R.integer.error_internCommunication_noSelectedMovie) {
			onError(R.integer.error_internCommunication_noSelectedMovie);
			return;
		}
		getDetailMovieObject();
	}

	/*
	Deprecated
	 */
	@Override
	public void getDetailMovieObject() {
		this.mDetailMovieObject = ConstantsHolder.getDownloadedDataFromPosition(this.mMovieInternId);
		int _id;
		showGivenData(this.mDetailMovieObject);
		try {
			_id = mDetailMovieObject.getmId();
		} catch (Exception e) {
			onError(R.integer.error_internCommunication_missingInfo);
			Log.e(LOG_TAG, "getDetailMovieObject: couldnt get MovieID" + "\n" +
					" --> " + e.fillInStackTrace());
			return;
		}
		getTrailer(_id);
		getReviews(_id);
	}

	@Override
	public void showGivenData(@Nullable DetailMovieObject movie) {
		if (movie == null) {
			Log.d(LOG_TAG, "showGivenData: movie == null");
			return;
		}
		Log.d(LOG_TAG, "showGivenData: movie should NOT be null -> " + (movie == null));
		mView.showGivenData(movie);
		// TODO: 14.12.16 mView.mTextViewTitle.setText(this.mDetailMovieObject) ... etc
	}

	@Override
	public void getTrailer(int id) {
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
	public void getReviews(int id) {
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
	public void saveAsFavorite() {
		SaveFavoriteMovieInteractor interactor = new SaveFavoriteMovieInteractorImpl(
				mExecutor,
				mMainThread,
				this,
				mSaveRepository,
				Converter.DetailMovieObjectToDataMovieObject(null)   // --> TODO: null = DetailMovieObject
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
	}

	@Override
	public void onDownloadReviewFinish(PageReviews page) {
		Log.d(LOG_TAG, "onDownloadReviewFinish: Got It!");
		// TODO: mView.showRevies()
	}

	@Override
	public void onSaveFinish(boolean success) {
		if (success) {
			Log.d(LOG_TAG, "onSaveFinish: Got It!");
		} else {
			Log.d(LOG_TAG, "onSaveFinish: Bad Luck :(");
		}
	}

	/*
	Deprecated
	 */
	// gets oly called when twoPaneMode == true
	@Override
	public void onItemSelected(int id) {
		Log.d(LOG_TAG, "onItemSelected: Callback called by MainFragment with id: " + id);
		this.mMovieInternId = id;
		getDetailMovieObject();
	}
}
