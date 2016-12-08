package com.pascaldierich.popularmoviesstage2.domain.repository;

/**
 * Created by pascaldierich on 08.12.16.
 */

/**
 * A sample repository with CRUD operations on a model.
 */
public interface Repository {

    boolean insert();

    boolean update();

    void get(Object id);

    boolean delete();
}
