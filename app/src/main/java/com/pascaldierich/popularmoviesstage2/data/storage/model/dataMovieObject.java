package com.pascaldierich.popularmoviesstage2.data.storage.model;

/**
 * Created by Pascal Dierich on Jan, 2017.
 */

/**
 * Movie Object for DB
 */
public class DataMovieObject extends MovieObject {
	private static final String LOG_TAG = DataMovieObject.class.getSimpleName();

	private byte[] mThumbnail;
	private String[] mTrailers = {
			""
	};

	public DataMovieObject(int id,
						   String title,
						   String description,
						   String release,
						   float rating,
						   byte[] thumbnail,
						   String[] trailers) {
		super(id, title, description, release, rating);
		mThumbnail = thumbnail;
		mTrailers = trailers;
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

	public String[] getTrailers() {
		return mTrailers;
	}
}
