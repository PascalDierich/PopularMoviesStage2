package com.pascaldierich.popularmoviesstage2.utils;

import com.pascaldierich.popularmoviesstage2.presentation.converters.model.DetailMovieObject;

import java.util.ArrayList;

/**
 * Created by pascaldierich on 13.12.16.
 */

public class ConstantsHolder {
    private static String sApiKey;

    public static void setApiKey(String a) {
        sApiKey = a;
    }

    public static String getApiKey() {
        return sApiKey;
    }

    private static ArrayList<DetailMovieObject> sDownloadedData;

    public static void setDownloadedData(ArrayList<DetailMovieObject> list) {
        sDownloadedData = list;
    }

    public static ArrayList<DetailMovieObject> getDownloadedData() {
        return sDownloadedData;
    }

    public static DetailMovieObject getDownloadedDataFromPosition(int position) {
        return sDownloadedData.get(position);
    }

}
