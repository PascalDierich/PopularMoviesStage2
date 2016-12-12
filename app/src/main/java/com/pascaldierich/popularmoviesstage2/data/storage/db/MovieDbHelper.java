package com.pascaldierich.popularmoviesstage2.data.storage.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pascaldierich on 12.12.16.
 */

public class MovieDbHelper extends SQLiteOpenHelper {

    public MovieDbHelper(Context context, int version) {
        super(context, MovieContract.DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + MovieContract.MovieEntry.TABLE_NAME + " ("
                + MovieContract.MovieEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MovieContract.MovieEntry.COLUMN_TITLE + " TEXT NOT NULL,"
                + MovieContract.MovieEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL,"
                + MovieContract.MovieEntry.COLUMN_LENGTH + " INTEGER NOT NULL,"
                + MovieContract.MovieEntry.COLUMN_RELEASE + " INTEGER NOT NULL,"
                + MovieContract.MovieEntry.COLUMN_RATING + " REAL NOT NULL,"
                + MovieContract.MovieEntry.COLUMN_TRAILER + " TEXT," // saves Trailer String[] with convertArrayToString()
                + MovieContract.MovieEntry.COLUMN_THUMBNAIL + " BLOB" // save image as Blob
                + ")";

        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}