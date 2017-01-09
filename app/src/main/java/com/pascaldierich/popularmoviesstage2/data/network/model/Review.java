package com.pascaldierich.popularmoviesstage2.data.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pascal Dierich on Jan, 2017.
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

