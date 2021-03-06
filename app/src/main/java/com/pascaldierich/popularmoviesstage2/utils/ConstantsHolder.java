package com.pascaldierich.popularmoviesstage2.utils;

import android.graphics.Bitmap;

/**
 * Created by Pascal Dierich on Jan, 2017.
 */

public class ConstantsHolder {
	private static String sApiKey;

	public static void setApiKey(String a) {
		sApiKey = a;
	}

	public static String getApiKey() {
		return sApiKey;
	}


	private static boolean sTwoPaneMode;

	public static void setTwoPaneMode(boolean mode) {
		sTwoPaneMode = mode;
	}

	public static boolean getTwoPaneMode() {
		return sTwoPaneMode;
	}

	private static Bitmap sBitmap;

	public static Bitmap getBitmap() {
		try {
			return sBitmap;
		} finally {
			sBitmap = null;
		}
	}

	public static boolean bitmapIsNull() {
		return sBitmap == null;
	}

	public static void setBitmap(Bitmap bitmap) {
		sBitmap = bitmap;
	}


}
