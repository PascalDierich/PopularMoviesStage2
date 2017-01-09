package com.pascaldierich.popularmoviesstage2.domain.interactors.impl;

import com.pascaldierich.popularmoviesstage2.data.storage.model.DataMovieObject;
import com.pascaldierich.popularmoviesstage2.domain.executor.Executor;
import com.pascaldierich.popularmoviesstage2.domain.executor.MainThread;
import com.pascaldierich.popularmoviesstage2.domain.interactors.QueryFavoriteMoviesInteractor;
import com.pascaldierich.popularmoviesstage2.domain.interactors.base.AbstractInteractor;
import com.pascaldierich.popularmoviesstage2.domain.repository.FavoriteRepository;

import java.util.ArrayList;

/**
 * Created by Pascal Dierich on Jan, 2017.
 */

public class QueryFavoriteInteractorImpl extends AbstractInteractor implements QueryFavoriteMoviesInteractor {
	private static final String LOG_TAG = QueryFavoriteInteractorImpl.class.getSimpleName();

	private QueryFavoriteMoviesInteractor.Callback mCallback;
	private FavoriteRepository mRepository;

	public QueryFavoriteInteractorImpl(Executor threadExecutor,
									   MainThread mainThread,
									   QueryFavoriteMoviesInteractor.Callback callback,
									   FavoriteRepository repository) {
		super(threadExecutor, mainThread);

		if (repository == null || callback == null) {
			throw new IllegalArgumentException("Arguments can not be null");
		}

		this.mCallback = callback;
		this.mRepository = repository;
	}

	@Override
	public void run() {

		final ArrayList<DataMovieObject> faveMovies = this.mRepository.getFavoriteMovies();

		mMainThread.post(new Runnable() {
			@Override
			public void run() {
				mCallback.onQueryFinished(faveMovies);
			}
		});
	}
}
