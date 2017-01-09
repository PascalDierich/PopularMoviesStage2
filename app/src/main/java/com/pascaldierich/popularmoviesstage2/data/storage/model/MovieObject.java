package com.pascaldierich.popularmoviesstage2.data.storage.model;

/**
 * Created by Pascal Dierich on Jan, 2017.
 */

public abstract class MovieObject {

	protected int mId;
	protected String mTitle;
	protected String mDescription;
	protected String mRelease;
	protected float mRating;

	protected MovieObject(int id, String title, String description, String release, float rating) {
		mId = id;
		mTitle = title;
		mDescription = description;
		mRelease = release;
		mRating = rating;
	}
}
