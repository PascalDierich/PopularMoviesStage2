package com.pascaldierich.popularmoviesstage2.domain.repository;

/**
 * Created by pascaldierich on 08.12.16.
 */

import android.net.Uri;

import com.pascaldierich.popularmoviesstage2.data.storage.model.DataMovieObject;

import java.util.ArrayList;

/**
 * A sample repository with CRUD operations on a model.
 */
public interface FavoriteRepository {

	ArrayList<DataMovieObject> getFavoriteMovies();

	String[] getMovie(Uri contentUri); // TODO: 17.12.16 deprecated -> used Parcelable Object
	/*
	TODO: insert DB methods like insert, read etc
     */

}
