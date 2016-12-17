package com.pascaldierich.popularmoviesstage2.presentation.converters.model;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.pascaldierich.popularmoviesstage2.data.storage.model.MovieObject;
import com.pascaldierich.popularmoviesstage2.presentation.converters.Converter;

/**
 * Created by pascaldierich on 12.12.16.
 */

/**
 * Movie Object for View
 */
public class DetailMovieObject extends MovieObject implements Parcelable {
	private static final String LOG_TAG = DetailMovieObject.class.getSimpleName();

	private String[] mTrailers; // optional
	private String mPosterPath; // optional
	private Bitmap mThumbnail;  // optinal
	private String[] mReview; // optional

	public DetailMovieObject(int id,
							 @NonNull String title,
							 @NonNull String description,
							 @NonNull String release,
							 float rating,
							 @Nullable String[] trailers,
							 @NonNull String posterPath,
							 @Nullable Bitmap bitmap,
							 @Nullable String[] review) {

		super(id, title, description, release, rating);
		this.mTrailers = trailers;
		this.mPosterPath = posterPath;
		this.mThumbnail = bitmap;
		this.mReview = review;
	}

	/*
	Setter
	 */
	public boolean setTrailers(String[] trailers) {
		if (this.mTrailers == null) {
			return false;
		}
		this.mTrailers = trailers;
		return true;
	}

	public boolean setThumbnail(Bitmap bitmap) {
		if (this.mThumbnail == null) {
			return false;
		}
		this.mThumbnail = bitmap;
		return true;
	}

	public boolean setReview(String[] review) {
		if (this.mReview == null) {
			return false;
		}
		this.mReview = review;
		return true;
	}

	/*
	Getter
	 */
	public int getmId() {
		return mId;
	}

	public String getmTitle() {
		Log.d(LOG_TAG, "getmTitle: " + mTitle);
		return mTitle;
	}

	public String getmDescription() {
		return mDescription;
	}

	public String getmRelease() {
		return mRelease;
	}

	public float getmRating() {
		return mRating;
	}

	public String[] getmTrailers() {
		return mTrailers;
	}

	public String getmPosterPath() {
		return mPosterPath;
	}

	public Bitmap getmThumbnail() {
		return mThumbnail;
	}

	public String[] getmReview() {
		return mReview;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(super.mTitle);
		dest.writeString(super.mDescription);
		dest.writeString(super.mRelease);
		dest.writeInt(super.mId);
		dest.writeFloat(super.mRating);

		if (this.mTrailers != null) dest.writeStringArray(this.mTrailers);
		if (this.mReview != null) dest.writeStringArray(this.mReview);

		dest.writeString(this.mPosterPath);

		if (this.mThumbnail != null) {
			Bundle thumbnail = new Bundle();
			thumbnail.putByteArray("", Converter.bitmapToByteArray(this.mThumbnail)); // TODO: 17.12.16 Key

			dest.writeBundle(thumbnail);
		}

	}

	public static final Parcelable.Creator CREATOR
			= new Parcelable.Creator() {
		@Override
		public Object createFromParcel(Parcel source) {
			return new DetailMovieObject(source);
		}

		@Override
		public Object[] newArray(int size) {
			return new DetailMovieObject[size];
		}
	};

	public DetailMovieObject(Parcel in) {
		super(in.readInt(), in.readString(), in.readString(), in.readString(), in.readFloat());

		this.mPosterPath = in.readString();

		try {
			this.mTrailers = in.createStringArray();
		} catch (NullPointerException npe) {
			Log.e(LOG_TAG, "DetailMovieObject: Trailer" + "\n" +
					" --> " + npe.fillInStackTrace());
		}

		try {
			this.mReview = in.createStringArray();
		} catch (NullPointerException npe) {
			Log.e(LOG_TAG, "DetailMovieObject: Review" + "\n" +
					" --> " + npe.fillInStackTrace());
		}

		try {
			this.mThumbnail = Converter.byteArrayToBitmao(in.createByteArray());
		} catch (NullPointerException npe) {
			Log.e(LOG_TAG, "DetailMovieObject: Thumbnail" + "\n" +
					" --> " + npe.fillInStackTrace());
		}

		Log.d(LOG_TAG, "DetailMovieObject: mPosterPath = " + mPosterPath);

	}
}
