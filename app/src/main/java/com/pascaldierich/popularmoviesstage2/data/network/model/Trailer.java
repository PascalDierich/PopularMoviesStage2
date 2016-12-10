package com.pascaldierich.popularmoviesstage2.data.network.model;

/**
 * Created by pascaldierich on 10.12.16.
 */

public class Trailer {

    private String mId;
    private String mKey;
    private String mTitle;

    public Trailer(String id, String key, String title) {
        mId = id;
        mKey = key;
        mTitle = title;
    }

    public String getId() {
        return mId;
    }

    public String getKey() {
        return mKey;
    }

    public String getTitle() {
        return mTitle;
    }
}
