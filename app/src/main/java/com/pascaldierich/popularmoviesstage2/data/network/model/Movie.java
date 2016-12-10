package com.pascaldierich.popularmoviesstage2.data.network.model;

/**
 * Created by pascaldierich on 10.12.16.
 */

public class Movie {

    private String mId;
    private String mTitle;
    private String mPosterPath;
    private String mDescription;
    private String mVoteAverage;
    private String mReleaseDate;

    public Movie(String id, String title, String posterPath,
                 String description, String votes, String release) {
        mId = id;
        mTitle = title;
        mPosterPath = posterPath;
        mDescription = description;
        mVoteAverage = votes;
        mReleaseDate = release;
    }

    public String getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmPosterPath() {
        return mPosterPath;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmVoteAverage() {
        return mVoteAverage;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }


}
