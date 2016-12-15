package com.pascaldierich.popularmoviesstage2.domain.interactors.impl;

import android.net.Uri;
import android.util.Log;

import com.pascaldierich.popularmoviesstage2.domain.executor.Executor;
import com.pascaldierich.popularmoviesstage2.domain.executor.MainThread;
import com.pascaldierich.popularmoviesstage2.domain.interactors.QuerySelectedMovieInteractor;
import com.pascaldierich.popularmoviesstage2.domain.interactors.base.AbstractInteractor;
import com.pascaldierich.popularmoviesstage2.domain.repository.FavoriteRepository;

/**
 * Created by Pascal Dierich on Dez, 2016.
 */
public class QuerySelectedMovieInteractorImpl extends AbstractInteractor implements QuerySelectedMovieInteractor {
	private static final String LOG_TAG = QuerySelectedMovieInteractorImpl.class.getSimpleName();

	QuerySelectedMovieInteractor.Callback mCallback;
	FavoriteRepository mRepository;
	Uri mContentUri;

	public QuerySelectedMovieInteractorImpl(Executor threadExecutor,
											MainThread mainThread,
											QuerySelectedMovieInteractor.Callback callback,
											FavoriteRepository repository,
											Uri contentUri) {
		super(threadExecutor, mainThread);

		this.mCallback = callback;
		this.mRepository = repository;
		this.mContentUri = contentUri;
	}

	@Override
	public void run() {
		
		Log.d(LOG_TAG, "run: hanging again?");

		final String[] movie = this.mRepository.getMovie(this.mContentUri);

		Log.d(LOG_TAG, "run: movie.title = " + movie[1] + ", length: " + movie.length);

		mMainThread.post(new Runnable() {
			@Override
			public void run() {
				mCallback.onLoadFinished(movie);
			}
		});
	}
}
