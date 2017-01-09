package com.pascaldierich.popularmoviesstage2.data.storage;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.pascaldierich.popularmoviesstage2.data.storage.db.MovieContract;
import com.pascaldierich.popularmoviesstage2.data.storage.model.DataMovieObject;
import com.pascaldierich.popularmoviesstage2.domain.repository.SaveMovieRepository;

import static com.pascaldierich.popularmoviesstage2.data.storage.db.MovieContract.MovieEntry.COLUMN_DESCRIPTION;
import static com.pascaldierich.popularmoviesstage2.data.storage.db.MovieContract.MovieEntry.COLUMN_RATING;
import static com.pascaldierich.popularmoviesstage2.data.storage.db.MovieContract.MovieEntry.COLUMN_RELEASE;
import static com.pascaldierich.popularmoviesstage2.data.storage.db.MovieContract.MovieEntry.COLUMN_THUMBNAIL;
import static com.pascaldierich.popularmoviesstage2.data.storage.db.MovieContract.MovieEntry.COLUMN_TITLE;


/**
 * Created by Pascal Dierich on Jan, 2017.
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

		return numberOfRowAdded > 0;
	}

	private ContentValues detailInfoToContentValues(DataMovieObject movieObject) {
		ContentValues values = new ContentValues();

		values.put(COLUMN_TITLE, movieObject.getmTitle());
		values.put(COLUMN_RELEASE, movieObject.getmRelease());
		values.put(COLUMN_DESCRIPTION, movieObject.getmDescription());
		values.put(COLUMN_RATING, movieObject.getmRating());
		values.put(COLUMN_THUMBNAIL, movieObject.getmThumbnail());

		return values;
	}
}
