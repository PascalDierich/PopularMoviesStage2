package com.pascaldierich.popularmoviesstage2.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.pascaldierich.popularmoviesstage2.data.storage.db.MovieContract;
import com.pascaldierich.popularmoviesstage2.data.storage.db.MovieDbHelper;

import java.util.HashSet;

/**
 * Created by Pascal Dierich on Jan, 2017.
 */
public class TestDb extends AndroidTestCase {
	private static final String LOG_TAG = TestDb.class.getSimpleName();

	void deleteTheDatabase() {
		mContext.deleteDatabase(MovieContract.DB_NAME);
	}

	public void setUp() {
		deleteTheDatabase();
	}

	public void testCreateDb() {
		final HashSet<String> tableNameHashSet = new HashSet<String>();
		tableNameHashSet.add(MovieContract.MovieEntry.TABLE_NAME);

		mContext.deleteDatabase(MovieContract.DB_NAME);
		SQLiteDatabase db = new MovieDbHelper(
				this.mContext, 1).getWritableDatabase();
		assertEquals(true, db.isOpen());

		Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

		assertTrue("Error: This means that the database has not been created correctly",
				c.moveToFirst());

		do {
			tableNameHashSet.remove(c.getString(0));
		} while( c.moveToNext() );

		c = db.rawQuery("PRAGMA table_info(" + MovieContract.MovieEntry.TABLE_NAME + ")",
				null);

		assertTrue("Error: This means that we were unable to query the database for table information.",
				c.moveToFirst());

		final HashSet<String> locationColumnHashSet = new HashSet<String>();
		locationColumnHashSet.add(MovieContract.MovieEntry.COLUMN_ID);
		locationColumnHashSet.add(MovieContract.MovieEntry.COLUMN_TITLE);
		locationColumnHashSet.add(MovieContract.MovieEntry.COLUMN_RELEASE);
		locationColumnHashSet.add(MovieContract.MovieEntry.COLUMN_DESCRIPTION);
		locationColumnHashSet.add(MovieContract.MovieEntry.COLUMN_RATING);
		locationColumnHashSet.add(MovieContract.MovieEntry.COLUMN_THUMBNAIL);
		locationColumnHashSet.add(MovieContract.MovieEntry.COLUMN_TRAILER);

		int columnNameIndex = c.getColumnIndex("name");
		do {
			String columnName = c.getString(columnNameIndex);
			locationColumnHashSet.remove(columnName);
		} while(c.moveToNext());

		assertTrue("Error: The database doesn't contain all of the required location entry columns",
				locationColumnHashSet.isEmpty());
		db.close();
	}


}
