package com.pascaldierich.popularmoviesstage2.utils;

/**
 * Created by pascaldierich on 13.12.16.
 */

public abstract class ErrorCodes {

    /*
    Network Codes
        -> -1xx
     */
    public static class Network {
        public static final int NO_INTERNET = -100;
        public static final int DOWNLOAD_NULL = -101;
    }

    /*
    Intern Communication
        -> -2xx
     */
    public static class internCommunication {
        public static final int OBJECT_NULL = -200;
        public static final int NO_SELECTED_MOVIE = -201;
        public static final int NOT_ENOUGH_INFO = -202;
    }

    /*
    Data Storage
        -> -3xx
     */
    public static class dataStorage {

        public static final int SAVE_FAILED = -301;
    }

}
