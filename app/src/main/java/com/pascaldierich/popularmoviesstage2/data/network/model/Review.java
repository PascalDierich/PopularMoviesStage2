package com.pascaldierich.popularmoviesstage2.data.network.model;

/**
 * Created by pascaldierich on 10.12.16.
 */

public class Review {

    private String mId;
    private String mAuthor;
    private String mContent;
    private String mMediaTitle;

    public Review(String id, String author, String content, String title) {
        mId = id;
        mAuthor = author;
        mContent = content;
        mMediaTitle = title;
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

    public String getMediaTitle() {
        return mMediaTitle;
    }
}

