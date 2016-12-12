package com.pascaldierich.popularmoviesstage2.data.storage;

import android.content.Context;
import android.database.Cursor;

import com.pascaldierich.popularmoviesstage2.data.storage.db.MovieContract;
import com.pascaldierich.popularmoviesstage2.domain.repository.FavoriteRepository;

import java.util.ArrayList;

/**
 * Created by pascaldierich on 12.12.16.
 */

public class FavoriteRepositoryImpl implements FavoriteRepository {

    private Context mContext;

    public FavoriteRepositoryImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public ArrayList<String[]> getFavoriteMovies() {
        Cursor cursor = mContext.getContentResolver().query(MovieContract.MovieEntry.CONTENT_URI, null, null, null, null);

        return cursorToArrayList(cursor);
    }

    private ArrayList<String[]> cursorToArrayList(Cursor cursor) {
        cursor.moveToFirst();
        ArrayList<String[]> movies = new ArrayList<>();

        while (cursor.moveToNext()) {
            movies.add(new String[]{
                    cursor.getString(MovieContract.MovieEntry.COLUMN_TITLE_ID),
                    cursor.getString(MovieContract.MovieEntry.COLUMN_THUMBNAIL_ID), // TODO: remove: kein String
                    cursor.getString(MovieContract.MovieEntry.COLUMN_DESCRIPTION_ID),
                    cursor.getString(MovieContract.MovieEntry.COLUMN_RATING_ID),
                    cursor.getString(MovieContract.MovieEntry.COLUMN_RELEASE_ID),
                    cursor.getString(MovieContract.MovieEntry.COLUMN_LENGTH_ID),
                    cursor.getString(MovieContract.MovieEntry.COLUMN_TRAILER_ID)
            });
        }
        return movies;
    }

}
