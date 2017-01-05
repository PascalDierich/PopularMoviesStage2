package com.pascaldierich.popularmoviesstage2.data.storage.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by pascaldierich on 12.12.16.
 */

public class MovieDbHelper extends SQLiteOpenHelper {
	private static final String LOG_TAG = MovieDbHelper.class.getSimpleName();

	public MovieDbHelper(Context context, int version) {
		super(context, MovieContract.DB_NAME, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		Log.d(LOG_TAG, "onCreate: going to Create Database");
		final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + MovieContract.MovieEntry.TABLE_NAME + " ("
				+ MovieContract.MovieEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ MovieContract.MovieEntry.COLUMN_TITLE + " TEXT NOT NULL,"
				+ MovieContract.MovieEntry.COLUMN_RELEASE + " TEXT NOT NULL,"
				+ MovieContract.MovieEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL,"
				+ MovieContract.MovieEntry.COLUMN_RATING + " REAL NOT NULL,"
				+ MovieContract.MovieEntry.COLUMN_THUMBNAIL + " BLOB," // save image as Blob
				+ MovieContract.MovieEntry.COLUMN_TRAILER + " TEXT NOT NULL"
				+ ");";
		sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

	}
}
