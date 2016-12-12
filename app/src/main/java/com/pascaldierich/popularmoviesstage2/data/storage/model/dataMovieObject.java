package com.pascaldierich.popularmoviesstage2.data.storage.model;

/**
 * Created by pascaldierich on 12.12.16.
 */

/**
 * Movie Object for DB
 */
public class DataMovieObject extends MovieObject {

    private byte[] mThumbnail;

    public DataMovieObject(int id,
                           String title,
                           String description,
                           String release,
                           float rating,
                           byte[] thumbnail) {
        super(id, title, description, release, rating);
        mThumbnail = thumbnail;
    }

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
        return super.mRating;
    }

    public byte[] getmThumbnail() {
        return mThumbnail;
    }
}
