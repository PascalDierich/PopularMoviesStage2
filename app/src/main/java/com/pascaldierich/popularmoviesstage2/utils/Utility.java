package com.pascaldierich.popularmoviesstage2.utils;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by pascaldierich on 13.12.16.
 */

public class Utility {

	public static boolean checkConnection(ConnectivityManager cm) {
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		return netInfo != null && netInfo.isConnectedOrConnecting();
	}
}
