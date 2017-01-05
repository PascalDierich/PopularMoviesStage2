package com.pascaldierich.popularmoviesstage2.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import java.util.Map;
import java.util.Set;

import static com.pascaldierich.popularmoviesstage2.data.storage.db.MovieContract.MovieEntry.COLUMN_DESCRIPTION;
import static com.pascaldierich.popularmoviesstage2.data.storage.db.MovieContract.MovieEntry.COLUMN_RATING;
import static com.pascaldierich.popularmoviesstage2.data.storage.db.MovieContract.MovieEntry.COLUMN_RELEASE;
import static com.pascaldierich.popularmoviesstage2.data.storage.db.MovieContract.MovieEntry.COLUMN_THUMBNAIL;
import static com.pascaldierich.popularmoviesstage2.data.storage.db.MovieContract.MovieEntry.COLUMN_TITLE;
import static com.pascaldierich.popularmoviesstage2.data.storage.db.MovieContract.MovieEntry.COLUMN_TRAILER;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

/**
 * Created by Pascal Dierich on Jan, 2017.
 */
public class DataUtility {
	private static final String LOG_TAG = DataUtility.class.getSimpleName();

	public static ContentValues createDummyData() {
		ContentValues values = new ContentValues();
		values.put(COLUMN_TITLE, "movie");
		values.put(COLUMN_RELEASE, "1.1.1901");
		values.put(COLUMN_DESCRIPTION, "this is a movie");
		values.put(COLUMN_RATING, "4.5");
		values.put(COLUMN_THUMBNAIL, new byte[] {});
		values.put(COLUMN_TRAILER, "trailer1__,__trailer2__,__trailer3");

		return values;
	}

	public static void validateCurrentRecord(String error, Cursor valueCursor, ContentValues expectedValues) {
		Set<Map.Entry<String, Object>> valueSet = expectedValues.valueSet();
		for (Map.Entry<String, Object> entry : valueSet) {
			try {
				String columnName = entry.getKey();
				int idx = valueCursor.getColumnIndex(columnName);
				assertFalse("Column '" + columnName + "' not found. " + error, idx == -1);
				String expectedValue = entry.getValue().toString();
				assertEquals("Value '" + entry.getValue().toString() +
						"' did not match the expected value '" +
						expectedValue + "'. " + error, expectedValue, valueCursor.getString(idx));
			} catch (SQLException e) {
				Log.e(LOG_TAG, "validateCurrentRecord: SQLiteException" + "\n" +
						" --> " + e.fillInStackTrace());
			}
			
		}
	}
}
