package com.pascaldierich.popularmoviesstage2.data.network.model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pascaldierich on 10.12.16.
 */

public class Movie {
    private static final String LOG_TAG = "Movie";

    @SerializedName("id")
    private String mId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("posterPath")
    private String mPosterPath;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("voteAverage")
    private String mVoteAverage;

    @SerializedName("releaseDate")
    private String mReleaseDate;

    public Movie(String id, String title, String posterPath,
                 String description, String voteAverage, String releaseData) {
        Log.d(LOG_TAG, "Movie: got created");
        mId = id;
        mTitle = title;
        mPosterPath = posterPath;
        mDescription = description;
        mVoteAverage = voteAverage;
        mReleaseDate = releaseData;
    }

    public String getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getVoteAverage() {
        return mVoteAverage;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }


}
