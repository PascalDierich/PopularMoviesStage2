package com.pascaldierich.popularmoviesstage2.data.storage;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import com.pascaldierich.popularmoviesstage2.data.storage.db.MovieContract;
import com.pascaldierich.popularmoviesstage2.domain.repository.SaveMovieRepository;

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
    public boolean saveAsFavorite(String[] detailInfo, Bitmap bitmap) {
        ContentValues values = detailInfoToContentValues(detailInfo, bitmap);

        Uri uri = mContext.getContentResolver().insert(MovieContract.MovieEntry.CONTENT_URI, values);

        return uri != null; // check if success
    }

    private ContentValues detailInfoToContentValues(String[] detailInfo, Bitmap bitmap) {
        ContentValues values = new ContentValues();

        values.put(MovieContract.MovieEntry.COLUMN_TITLE, detailInfo[0]);
        values.put(MovieContract.MovieEntry.COLUMN_THUMBNAIL, getBitmapAsByteArray(bitmap));
        values.put(MovieContract.MovieEntry.COLUMN_DESCRIPTION, detailInfo[2]);
        values.put(MovieContract.MovieEntry.COLUMN_RATING, detailInfo[3]);
        values.put(MovieContract.MovieEntry.COLUMN_RELEASE, detailInfo[4]);
        values.put(MovieContract.MovieEntry.COLUMN_LENGTH, detailInfo[5]);
        values.put(MovieContract.MovieEntry.COLUMN_TRAILER, detailInfo[6]); // TODO: here trailer IS ALREADY STRING!!!

        return values;
    }

    private static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);

        return stream.toByteArray();
    }
}
