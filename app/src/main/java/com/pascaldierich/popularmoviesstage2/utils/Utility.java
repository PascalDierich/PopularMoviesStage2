package com.pascaldierich.popularmoviesstage2.utils;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Pascal Dierich on Jan, 2017.
 */

public class Utility {

	public static boolean checkConnection(ConnectivityManager cm) {
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		return netInfo != null && netInfo.isConnectedOrConnecting();
	}
}
