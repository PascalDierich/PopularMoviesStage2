package com.pascaldierich.popularmoviesstage2.data.storage.model;

/**
 * Created by pascaldierich on 12.12.16.
 */

public abstract class MovieObject {

    protected int mId;
    protected String mTitle;
    protected String mDescription;
    protected String mRelease;
    protected float mRating;

    public MovieObject(int id, String title, String description, String release, float rating) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mRelease = release;
        mRating = rating;
    }
}
