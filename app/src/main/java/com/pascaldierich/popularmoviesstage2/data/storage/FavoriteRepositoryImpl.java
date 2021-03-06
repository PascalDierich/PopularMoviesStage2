package com.pascaldierich.popularmoviesstage2.data.storage;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.pascaldierich.popularmoviesstage2.data.storage.db.MovieContract;
import com.pascaldierich.popularmoviesstage2.data.storage.model.DataMovieObject;
import com.pascaldierich.popularmoviesstage2.domain.repository.FavoriteRepository;

import java.util.ArrayList;

/**
 * Created by Pascal Dierich on Jan, 2017.
 */

public class FavoriteRepositoryImpl implements FavoriteRepository {
	private static final String LOG_TAG = FavoriteRepositoryImpl.class.getSimpleName();

	private Context mContext;

	public FavoriteRepositoryImpl(Context context) {
		this.mContext = context;
	}

	@Override
	public ArrayList<DataMovieObject> getFavoriteMovies() {
		Cursor cursor = mContext.getContentResolver().query(MovieContract.MovieEntry.CONTENT_URI, null, null, null, null);

		Log.d(LOG_TAG, "getFavoriteMovies: Content Uri = " + MovieContract.MovieEntry.CONTENT_URI);
		Log.d(LOG_TAG, "getFavoriteMovies: cursor == null -> " + (cursor == null));

		return cursorToArrayList(cursor);
	}

	private ArrayList<DataMovieObject> cursorToArrayList(Cursor cursor) {
		cursor.moveToFirst();
		Log.d(LOG_TAG, "cursorToArrayList: cursor.getCount() " + cursor.getCount());
		ArrayList<DataMovieObject> movieList = new ArrayList<>();

		int i = 0;
		do {
			Log.d(LOG_TAG, "cursorToArrayList: i = " + ++i);
			movieList.add(new DataMovieObject(
					cursor.getInt(MovieContract.MovieEntry.COLUMN_ID_ID),
					cursor.getString(MovieContract.MovieEntry.COLUMN_TITLE_ID),
					cursor.getString(MovieContract.MovieEntry.COLUMN_DESCRIPTION_ID),
					cursor.getString(MovieContract.MovieEntry.COLUMN_RELEASE_ID),
					cursor.getFloat(MovieContract.MovieEntry.COLUMN_RATING_ID),
					cursor.getBlob(MovieContract.MovieEntry.COLUMN_THUMBNAIL_ID),
					new String[] {"trailer"}
			));
		} while (cursor.moveToNext());

		return movieList;
	}
}
