package com.pascaldierich.popularmoviesstage2.utils;

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


    /*
     *****************
     * Error Messages
     *****************
     */

    private static String sErrorMessageInterDownloadNull;

    public static void setErrorMessageInterDownloadNull(String a) {
        sErrorMessageInterDownloadNull = a;
    }

    public static String getErrorMessageInterDownloadNull() {
        return sErrorMessageInterDownloadNull;
    }



}
