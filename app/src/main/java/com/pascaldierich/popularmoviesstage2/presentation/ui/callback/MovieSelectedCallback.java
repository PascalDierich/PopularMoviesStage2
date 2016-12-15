package com.pascaldierich.popularmoviesstage2.presentation.ui.callback;

import android.net.Uri;

/**
 * Created by Pascal Dierich on Dez, 2016.
 */

public interface MovieSelectedCallback {
	
	void onMovieSelected(Uri contentUri); // TODO: 15.12.16 fuck static content ! no id -> Uri
	
}
