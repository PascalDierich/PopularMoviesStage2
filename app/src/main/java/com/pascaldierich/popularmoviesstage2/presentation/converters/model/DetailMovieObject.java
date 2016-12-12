package com.pascaldierich.popularmoviesstage2.presentation.converters.model;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pascaldierich.popularmoviesstage2.data.storage.model.MovieObject;

/**
 * Created by pascaldierich on 12.12.16.
 */

/**
 * Movie Object for View
 */
public class DetailMovieObject extends MovieObject {

    private String[] mTrailers; // optional
    private String mPosterPath; // optional
    private Bitmap mThumbnail;  // optinal
    private String[] mReview; // optional

    public DetailMovieObject(int id,
                             @NonNull String title,
                             @NonNull String description,
                             @NonNull String release,
                             float rating,
                             @Nullable String[] trailers,
                             @NonNull String posterPath,
                             @Nullable Bitmap bitmap,
                             @Nullable String[] review) {

        super(id, title, description, release, rating);
        this.mTrailers = trailers;
        this.mPosterPath = posterPath;
        this.mThumbnail = bitmap;
        this.mReview = review;
    }

    /*
    Setter
     */
    public boolean setTrailers(String[] trailers) {
        if (this.mTrailers == null) {
            return false;
        }
        this.mTrailers = trailers;
        return true;
    }

    public boolean setThumbnail(Bitmap bitmap) {
        if (this.mThumbnail == null) {
            return false;
        }
        this.mThumbnail = bitmap;
        return true;
    }

    public boolean setReview(String[] review) {
        if (this.mReview == null) {
            return false;
        }
        this.mReview = review;
        return true;
    }

    /*
    Getter
     */
    public int getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmRelease() {
        return mRelease;
    }

    public float getmRating() {
        return mRating;
    }

    public String[] getmTrailers() {
        return mTrailers;
    }

    public String getmPosterPath() {
        return mPosterPath;
    }

    public Bitmap getmThumbnail() {
        return mThumbnail;
    }

    public String[] getmReview() {
        return mReview;
    }


}
