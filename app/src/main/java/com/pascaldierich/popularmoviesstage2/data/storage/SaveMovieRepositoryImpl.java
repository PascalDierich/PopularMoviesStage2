package com.pascaldierich.popularmoviesstage2.data.storage;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.pascaldierich.popularmoviesstage2.R;
import com.pascaldierich.popularmoviesstage2.data.storage.db.MovieContract;
import com.pascaldierich.popularmoviesstage2.data.storage.model.DataMovieObject;
import com.pascaldierich.popularmoviesstage2.domain.repository.SaveMovieRepository;
import com.pascaldierich.popularmoviesstage2.presentation.converters.Converter;

import static com.pascaldierich.popularmoviesstage2.data.storage.db.MovieContract.MovieEntry.COLUMN_DESCRIPTION;
import static com.pascaldierich.popularmoviesstage2.data.storage.db.MovieContract.MovieEntry.COLUMN_RATING;
import static com.pascaldierich.popularmoviesstage2.data.storage.db.MovieContract.MovieEntry.COLUMN_RELEASE;
import static com.pascaldierich.popularmoviesstage2.data.storage.db.MovieContract.MovieEntry.COLUMN_THUMBNAIL;
import static com.pascaldierich.popularmoviesstage2.data.storage.db.MovieContract.MovieEntry.COLUMN_TITLE;
import static com.pascaldierich.popularmoviesstage2.data.storage.db.MovieContract.MovieEntry.COLUMN_TRAILER;


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

		log(values);

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

		String trailers = Converter.convertArrayToString(movieObject.getTrailers());
		if (trailers == null) trailers = R.integer.error_noTrailers + "";
		values.put(COLUMN_TRAILER, trailers);

		Log.d(LOG_TAG, "detailInfoToContentValues: going to return values with size == " + values.size());
		Log.d(LOG_TAG, "detailInfoToContentValues: Values ALWAYS have to be 6");

		return values;
	}

	private void log(ContentValues data) {
		Log.d(LOG_TAG, "");
		Log.d(LOG_TAG, "SAVE TO DATABASE :");
		Log.d(LOG_TAG, "######################################################");
		Log.d(LOG_TAG, "DataMovieObject.getTitle = " + data.get(COLUMN_TITLE));
		Log.d(LOG_TAG, "DataMovieObject.getRelease = " + data.get(COLUMN_RELEASE));
		Log.d(LOG_TAG, "DataMovieObject.getDescription = " + data.get(COLUMN_DESCRIPTION));
		Log.d(LOG_TAG, "DataMovieObject.getRating = " + data.get(COLUMN_RATING));
		Log.d(LOG_TAG, "DataMovieObject.getThumbnail.length = " + data.get(COLUMN_THUMBNAIL));
		Log.d(LOG_TAG, "DataMovieObject.getTrailers = " + data.get(COLUMN_TRAILER).toString());
		Log.d(LOG_TAG, "######################################################");
	}
}
