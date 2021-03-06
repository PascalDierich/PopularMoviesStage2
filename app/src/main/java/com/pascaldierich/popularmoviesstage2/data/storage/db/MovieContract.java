package com.pascaldierich.popularmoviesstage2.data.storage.db;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Pascal Dierich on Jan, 2017.
 */

public abstract class MovieContract {
	private static final String LOG_TAG = MovieContract.class.getSimpleName();

	public static final String DB_NAME = "popularMovies.db";

	static final String CONTENT_AUTHORITY = "com.pascaldierich.popularmoviesstage2";
	private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
	static final String MOVIE_PATH = "movies";


	public static final class MovieEntry implements BaseColumns {
		public static final Uri CONTENT_URI =
				BASE_CONTENT_URI.buildUpon().appendPath(MOVIE_PATH).build();

		public static final String TABLE_NAME = "favoriteMovies";

		// Columns in movies -> names
		public static final String COLUMN_ID = "id";
		public static final String COLUMN_TITLE = "title";
		public static final String COLUMN_RELEASE = "release";
		public static final String COLUMN_DESCRIPTION = "description";
		public static final String COLUMN_RATING = "rating";
		public static final String COLUMN_THUMBNAIL = "thumbnail"; // saves the whole image

		// Columns in movies -> ID
		public static final int COLUMN_ID_ID = 0;
		public static final int COLUMN_TITLE_ID = 1;
		public static final int COLUMN_RELEASE_ID = 2;
		public static final int COLUMN_DESCRIPTION_ID = 3;
		public static final int COLUMN_RATING_ID = 4;
		public static final int COLUMN_THUMBNAIL_ID = 5;

		public static Uri buildMovieUriWithId(long id) {
			return ContentUris.withAppendedId(CONTENT_URI, id);
		}
	}

}
