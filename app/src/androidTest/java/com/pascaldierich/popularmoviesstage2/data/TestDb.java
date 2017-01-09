package com.pascaldierich.popularmoviesstage2.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.pascaldierich.popularmoviesstage2.data.storage.db.MovieContract;
import com.pascaldierich.popularmoviesstage2.data.storage.db.MovieDbHelper;
import com.pascaldierich.popularmoviesstage2.utils.DataUtility;

import java.util.HashSet;

/**
 * Created by Pascal Dierich on Jan, 2017.
 */
public class TestDb extends AndroidTestCase {
	private static final String LOG_TAG = TestDb.class.getSimpleName();

	private void deleteTheDatabase() {
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

		assertTrue("Error: Unable to query the database for table information.",
				c.moveToFirst());

		final HashSet<String> locationColumnHashSet = new HashSet<String>();
		locationColumnHashSet.add(MovieContract.MovieEntry.COLUMN_ID);
		locationColumnHashSet.add(MovieContract.MovieEntry.COLUMN_TITLE);
		locationColumnHashSet.add(MovieContract.MovieEntry.COLUMN_RELEASE);
		locationColumnHashSet.add(MovieContract.MovieEntry.COLUMN_DESCRIPTION);
		locationColumnHashSet.add(MovieContract.MovieEntry.COLUMN_RATING);
		locationColumnHashSet.add(MovieContract.MovieEntry.COLUMN_THUMBNAIL);

		int columnNameIndex = c.getColumnIndex("name");
		do {
			String columnName = c.getString(columnNameIndex);
			locationColumnHashSet.remove(columnName);
		} while(c.moveToNext());

		assertTrue("Error: The database doesn't contain all of the required location entry columns",
				locationColumnHashSet.isEmpty());
		db.close();
	}

	public void testInsert() {
		MovieDbHelper dbHelper = new MovieDbHelper(mContext, 1);
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		ContentValues values = DataUtility.createDummyData();

		long _id = db.insert(MovieContract.MovieEntry.TABLE_NAME, null, values);

		assertTrue(_id != -1);

		Cursor cursor = db.query(
				MovieContract.MovieEntry.TABLE_NAME,
				null,
				null,
				null,
				null,
				null,
				null
		);

		assertTrue("Error: No Records returned from location query", cursor.moveToFirst());

		DataUtility.validateCurrentRecord("Error: Location Query Validation Failed",
				cursor, values);

		assertFalse( "Error: More than one record returned from location query",
				cursor.moveToNext() );

		cursor.close();
		db.close();
	}


}
