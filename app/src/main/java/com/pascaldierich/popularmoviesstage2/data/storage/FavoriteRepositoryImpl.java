package com.pascaldierich.popularmoviesstage2.data.storage;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.pascaldierich.popularmoviesstage2.data.storage.db.MovieContract;
import com.pascaldierich.popularmoviesstage2.domain.repository.FavoriteRepository;

import java.util.ArrayList;

/**
 * Created by pascaldierich on 12.12.16.
 */

public class FavoriteRepositoryImpl implements FavoriteRepository {
	private static final String LOG_TAG = FavoriteRepositoryImpl.class.getSimpleName();

	private Context mContext;

	public FavoriteRepositoryImpl(Context context) {
		this.mContext = context;
	}

	@Override
	public ArrayList<String[]> getFavoriteMovies() {
		Cursor cursor = mContext.getContentResolver().query(MovieContract.MovieEntry.CONTENT_URI, null, null, null, null);

		Log.d(LOG_TAG, "getFavoriteMovies: Content Uri = " + MovieContract.MovieEntry.CONTENT_URI);
		Log.d(LOG_TAG, "getFavoriteMovies: cursor == null -> " + (cursor == null));

		return cursorToArrayList(cursor);
	}

	private ArrayList<String[]> cursorToArrayList(Cursor cursor) {
		cursor.moveToFirst();
		Log.d(LOG_TAG, "cursorToArrayList: cursor.getCount() " + cursor.getCount());
		ArrayList<String[]> movieList = new ArrayList<>();

		int i = 0;
		while (cursor.moveToNext()) {
			Log.d(LOG_TAG, "cursorToArrayList: i = " + ++i);
			movieList.add(new String[] {
					cursor.getString(MovieContract.MovieEntry.COLUMN_TITLE_ID),
					cursor.getString(MovieContract.MovieEntry.COLUMN_DESCRIPTION_ID),
					cursor.getString(MovieContract.MovieEntry.COLUMN_RATING_ID),
					cursor.getString(MovieContract.MovieEntry.COLUMN_RELEASE_ID)
					// TODO: 18.12.16 get Thumbnail as Byte[]
			});
		}

		/*
		only for production tests
		 */
		Log.d(LOG_TAG, "cursorToArrayList: movieList.size() == " + movieList.size());

		return movieList;
	}


	/**
	Deprecated
	 */
	@Override
	public String[] getMovie(Uri contentUri) {
		Log.d(LOG_TAG, "getMovie: going to lag again?");

		Cursor cursor = mContext.getContentResolver().query(contentUri, null, null, null, null); // TODO: 15.12.16 cursor == null

		Log.d(LOG_TAG, "getMovie: contentUri = " + contentUri);

		return cursorToStringArray(cursor);
	}

	private String[] cursorToStringArray(Cursor cursor) {
		cursor.moveToFirst();

		return new String[] {
				cursor.getString(MovieContract.MovieEntry.COLUMN_TITLE_ID),
				cursor.getString(MovieContract.MovieEntry.COLUMN_DESCRIPTION_ID),
				cursor.getString(MovieContract.MovieEntry.COLUMN_RATING_ID),
				cursor.getString(MovieContract.MovieEntry.COLUMN_RELEASE_ID)
		};
	}
}
