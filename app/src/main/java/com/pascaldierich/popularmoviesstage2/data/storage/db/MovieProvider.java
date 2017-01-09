package com.pascaldierich.popularmoviesstage2.data.storage.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Pascal Dierich on Jan, 2017.
 */

public class MovieProvider extends ContentProvider {
	private static final String LOG_TAG = MovieProvider.class.getSimpleName();
	
	private MovieDbHelper mDbHelper;
	private static UriMatcher sUriMatcher = buildUriMatcher();

	private static final int MOVIE = 200;

	private static UriMatcher buildUriMatcher() {
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
	public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
		final SQLiteDatabase db = mDbHelper.getWritableDatabase();
		int returnCount = 0;
		switch (sUriMatcher.match(uri)) {
			case MOVIE: {
				db.beginTransaction();
				try {
					for (ContentValues value : values) {
						long _id = db.insert(MovieContract.MovieEntry.TABLE_NAME, null, value);
						if (_id != -1) {
							Log.d(LOG_TAG, "bulkInsert: _id != 1");
							Log.d(LOG_TAG, "bulkInsert: _id == " + _id);
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
	public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
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

	@Override
	public String getType(@NonNull Uri uri) {
		return null;
	}

	@Override
	public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
		final SQLiteDatabase db = mDbHelper.getWritableDatabase();
		
		switch (sUriMatcher.match(uri)) {
			case MOVIE: {
				db.beginTransaction();
				try {
					long _id = db.insertOrThrow(MovieContract.MovieEntry.TABLE_NAME, null, contentValues);
					if (_id > 0) {
						Log.d(LOG_TAG, "insert: id = " + _id);
						return MovieContract.MovieEntry.buildMovieUriWithId(_id);
					} else {
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
