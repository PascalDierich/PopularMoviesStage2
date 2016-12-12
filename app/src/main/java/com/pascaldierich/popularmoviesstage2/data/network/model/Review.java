package com.pascaldierich.popularmoviesstage2.data.network.model;

import com.google.gson.annotations.SerializedName;

import javax.inject.Singleton;

/**
 * Created by pascaldierich on 10.12.16.
 */

public class Review {

    @SerializedName("id")
    private String mId;

    @SerializedName("author")
    private String mAuthor;

    @SerializedName("content")
    private String mContent;

    public Review(String id, String author, String content, String title) {
        mId = id;
        mAuthor = author;
        mContent = content;
    }

    public String getId() {
        return mId;
    }

    public String getAuthror() {
        return mAuthor;
    }

    public String getContent() {
        return mContent;
    }
}

