package com.pascaldierich.popularmoviesstage2.domain.repository;

/**
 * Created by pascaldierich on 08.12.16.
 */

import java.util.ArrayList;

/**
 * A sample repository with CRUD operations on a model.
 */
public interface FavoriteRepository {

	ArrayList<String[]> getFavoriteMovies();
	/*
    TODO: insert DB methods like insert, read etc
     */

}
