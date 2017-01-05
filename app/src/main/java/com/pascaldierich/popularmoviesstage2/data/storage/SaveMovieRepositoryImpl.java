package com.pascaldierich.popularmoviesstage2.data.storage;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.pascaldierich.popularmoviesstage2.data.storage.db.MovieContract;
import com.pascaldierich.popularmoviesstage2.data.storage.model.DataMovieObject;
import com.pascaldierich.popularmoviesstage2.domain.repository.SaveMovieRepository;
import com.pascaldierich.popularmoviesstage2.presentation.converters.Converter;

/**
 * Created by pascaldierich on 12.12.16.
 */

public class SaveMovieRepositoryImpl implements SaveMovieRepository {
	private static final String LOG_TAG = SaveMovieRepositoryImpl.class.getSimpleName();

	private Context mContext;

	public SaveMovieRepositoryImpl(Context context) {
		this.mContext = context;
	}

	@Override
	public boolean saveAsFavorite(DataMovieObject movieObject) {
		ContentValues values = detailInfoToContentValues(movieObject);

		ContentValues[] valuesArray = {
				values
		};
		int numberOfRowAdded = mContext.getContentResolver().bulkInsert(MovieContract.MovieEntry.CONTENT_URI, valuesArray);

		Log.d(LOG_TAG, "saveAsFavorite: number of rows added: " + numberOfRowAdded);

		return numberOfRowAdded != -1;
	}

	private ContentValues detailInfoToContentValues(DataMovieObject movieObject) {
		ContentValues values = new ContentValues();

		values.put(MovieContract.MovieEntry.COLUMN_TITLE, movieObject.getmTitle());
		values.put(MovieContract.MovieEntry.COLUMN_RELEASE, movieObject.getmRelease());
		values.put(MovieContract.MovieEntry.COLUMN_DESCRIPTION, movieObject.getmDescription());
		values.put(MovieContract.MovieEntry.COLUMN_RATING, movieObject.getmRating());
		values.put(MovieContract.MovieEntry.COLUMN_THUMBNAIL, movieObject.getmThumbnail());
		values.put(MovieContract.MovieEntry.COLUMN_TRAILER, Converter.convertArrayToString(movieObject.getTrailers()));

		Log.d(LOG_TAG, "detailInfoToContentValues: going to return values with size == " + values.size());
		Log.d(LOG_TAG, "detailInfoToContentValues: Values ALWAYS have to be 6");

		return values;
	}
}
