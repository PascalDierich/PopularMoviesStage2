package com.pascaldierich.popularmoviesstage2.presentation.converters;

import android.graphics.Bitmap;
import android.util.Log;

import com.pascaldierich.popularmoviesstage2.data.network.model.Movie;
import com.pascaldierich.popularmoviesstage2.data.network.model.pages.PageMovies;
import com.pascaldierich.popularmoviesstage2.data.storage.model.DataMovieObject;
import com.pascaldierich.popularmoviesstage2.presentation.converters.model.DetailMovieObject;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by pascaldierich on 12.12.16.
 */

public class Converter {
    private static final String LOG_TAG = Converter.class.getSimpleName();

    public static ArrayList<DetailMovieObject> PageMovieToArrayListDetailMovieObject(PageMovies movies) {
        ArrayList<DetailMovieObject> result = new ArrayList<>();
        ArrayList<Movie> movieArrayList = movies.getResults();

        for (Movie movie: movieArrayList) {
            result.add(new DetailMovieObject(
                    Integer.parseInt(movie.getId()),
                    movie.getTitle(),
                    movie.getDescription(),
                    movie.getReleaseDate(),
                    Float.parseFloat(movie.getVoteAverage()),
                    null,   // String[] trailers
                    movie.getPosterPath(),
                    null,   // Bitmap bitmap
                    null    // String[] review
            ));
        }
        return result;
    }

    public static DataMovieObject DetailMovieObjectToDataMovieObject(DetailMovieObject object) {
        return new DataMovieObject(
                object.getmId(),
                object.getmTitle(),
                object.getmDescription(),
                object.getmRelease(),
                object.getmRating(),
                bitmapToByteArray(object.getmThumbnail())
        );
    }

    public static byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);

        return outputStream.toByteArray();
    }
}
