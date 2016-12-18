package com.pascaldierich.popularmoviesstage2.data.storage.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by pascaldierich on 12.12.16.
 */

public class MovieProvider extends ContentProvider {
	private static final String LOG_TAG = MovieProvider.class.getSimpleName();
	
	MovieDbHelper mDbHelper;
	static UriMatcher sUriMatcher = buildUriMatcher();

	static final int MOVIE = 200;

	static UriMatcher buildUriMatcher() {
		final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

		matcher.addURI(MovieContract.CONTENT_AUTHORITY, MovieContract.MOVIE_PATH, MOVIE);

		return matcher;
	}

	@Override
	public boolean onCreate() {
		mDbHelper = new MovieDbHelper(getContext(), 1);
		return true;
	}

	@Override
	public int bulkInsert(Uri uri, ContentValues[] values) {
		final SQLiteDatabase db = mDbHelper.getWritableDatabase();
		int returnCount = 0;
		switch (sUriMatcher.match(uri)) {
			case MOVIE: {
				db.beginTransaction();
				try {
					for (ContentValues value : values) {
						long _id = db.insert(MovieContract.MovieEntry.TABLE_NAME, null, value);
						if (_id != -1) {
							returnCount++;
						}
					}
					db.setTransactionSuccessful();
				} finally {
					db.endTransaction();
				}
				return returnCount;
			}
			default: {
				return -1;
			}
		}

	}

	@Nullable
	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		switch (sUriMatcher.match(uri)) {
			case MOVIE: {
				return mDbHelper.getReadableDatabase().query(
						MovieContract.MovieEntry.TABLE_NAME,
						projection,
						selection,
						selectionArgs,
						null,
						null,
						sortOrder
				);
			}
			default:
				return null;
		}
	}

	@Nullable
	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Nullable
	@Override
	public Uri insert(Uri uri, ContentValues contentValues) {
		Log.d(LOG_TAG, "insert: provider going to insert data");
		final SQLiteDatabase db = mDbHelper.getWritableDatabase();
		
		Log.d(LOG_TAG, "insert: provider get writableDatabase");
		
		switch (sUriMatcher.match(uri)) {
			case MOVIE: {
				Log.d(LOG_TAG, "insert: going to insert into table MOVIE");
				db.beginTransaction();
				try {
					// TODO: 18.12.16 db.insertOrThrow does not response (!) 
					Log.d(LOG_TAG, "insert: db path = " + db.getPath());
					Log.d(LOG_TAG, "insert: going to call insertOrThrow");
					Log.d(LOG_TAG, "insert: contentValues.size == " + contentValues.size());
					long _id = db.insertOrThrow(MovieContract.MovieEntry.TABLE_NAME, null, contentValues);
					if (_id > 0) {
						Log.d(LOG_TAG, "insert: id = " + _id);
						return MovieContract.MovieEntry.buildMovieUriWithId(_id);
					} else {
						Log.d(LOG_TAG, "insert: EXCEPTION");
						throw new android.database.SQLException("Failed to insert row into: " + uri);
					}
				} finally {
					db.endTransaction();
				}
			}
			default:
				throw new UnsupportedOperationException("Unknown Uri: " + uri);
		}
	}

	@Override
	public int delete(Uri uri, String s, String[] strings) {
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
		return 0;
	}
}
