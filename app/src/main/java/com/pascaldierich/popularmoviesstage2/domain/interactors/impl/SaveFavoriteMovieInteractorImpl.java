package com.pascaldierich.popularmoviesstage2.domain.interactors.impl;

import android.util.Log;

import com.pascaldierich.popularmoviesstage2.data.storage.model.DataMovieObject;
import com.pascaldierich.popularmoviesstage2.domain.executor.Executor;
import com.pascaldierich.popularmoviesstage2.domain.executor.MainThread;
import com.pascaldierich.popularmoviesstage2.domain.interactors.SaveFavoriteMovieInteractor;
import com.pascaldierich.popularmoviesstage2.domain.interactors.base.AbstractInteractor;
import com.pascaldierich.popularmoviesstage2.domain.repository.SaveMovieRepository;

/**
 * Created by pascaldierich on 12.12.16.
 */

public class SaveFavoriteMovieInteractorImpl extends AbstractInteractor implements SaveFavoriteMovieInteractor {
	private static final String LOG_TAG = SaveFavoriteMovieInteractorImpl.class.getSimpleName();

	SaveFavoriteMovieInteractor.Callback mCallback;
	SaveMovieRepository mRepository;

	DataMovieObject mMovieObject;

	public SaveFavoriteMovieInteractorImpl(Executor threadExecutor, MainThread mainThread,
										   SaveFavoriteMovieInteractor.Callback callback,
										   SaveMovieRepository repository,
										   DataMovieObject movieObject) {
		super(threadExecutor, mainThread);

		if (repository == null || callback == null) {
			throw new IllegalArgumentException("Arguments can not be null");
		}

		this.mCallback = callback;
		this.mRepository = repository;
		this.mMovieObject = movieObject;
	}

	@Override
	public void run() {
		final boolean success = mRepository.saveAsFavorite(mMovieObject);
		
		Log.d(LOG_TAG, "run: success?? -> " + success);
		
		// TODO: 17.12.16 save does not work 
		
		mMainThread.post(new Runnable() {
			@Override
			public void run() {
				mCallback.onSaveFinish(success);
			}
		});
	}
}
