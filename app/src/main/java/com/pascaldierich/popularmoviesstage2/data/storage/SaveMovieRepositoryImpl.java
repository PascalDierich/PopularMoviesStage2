package com.pascaldierich.popularmoviesstage2.data.storage;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import com.pascaldierich.popularmoviesstage2.data.storage.db.MovieContract;
import com.pascaldierich.popularmoviesstage2.data.storage.model.DataMovieObject;
import com.pascaldierich.popularmoviesstage2.domain.repository.SaveMovieRepository;
import com.pascaldierich.popularmoviesstage2.presentation.converters.model.DetailMovieObject;

import java.io.ByteArrayOutputStream;

/**
 * Created by pascaldierich on 12.12.16.
 */

public class SaveMovieRepositoryImpl implements SaveMovieRepository {

    private Context mContext;

    public SaveMovieRepositoryImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public boolean saveAsFavorite(DataMovieObject movieObject) {

        ContentValues values = detailInfoToContentValues(movieObject);

        Uri uri = mContext.getContentResolver().insert(MovieContract.MovieEntry.CONTENT_URI, values);

        return uri != null; // check if success
    }

    private ContentValues detailInfoToContentValues(DataMovieObject movieObject) {
        ContentValues values = new ContentValues();

        values.put(MovieContract.MovieEntry.COLUMN_ID, movieObject.getmId());
        values.put(MovieContract.MovieEntry.COLUMN_TITLE, movieObject.getmTitle());
        values.put(MovieContract.MovieEntry.COLUMN_THUMBNAIL, movieObject.getmThumbnail());
        values.put(MovieContract.MovieEntry.COLUMN_DESCRIPTION, movieObject.getmDescription());
        values.put(MovieContract.MovieEntry.COLUMN_RATING, movieObject.getmRating());
        values.put(MovieContract.MovieEntry.COLUMN_RELEASE, movieObject.getmRelease());

        return values;
    }
}
