package com.pascaldierich.popularmoviesstage2.presentation.converters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.pascaldierich.popularmoviesstage2.data.network.model.Movie;
import com.pascaldierich.popularmoviesstage2.data.network.model.pages.PageMovies;
import com.pascaldierich.popularmoviesstage2.data.storage.model.DataMovieObject;
import com.pascaldierich.popularmoviesstage2.presentation.converters.model.DetailMovieObject;
import com.pascaldierich.popularmoviesstage2.presentation.ui.model.GridItem;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by Pascal Dierich on Jan, 2017.
 */

public class Converter {
	private static final String LOG_TAG = Converter.class.getSimpleName();

	public static ArrayList<DetailMovieObject> PageMovieToArrayListDetailMovieObject(PageMovies movies) {
		ArrayList<DetailMovieObject> result = new ArrayList<>();
		ArrayList<Movie> movieArrayList = movies.getResults();

		for (Movie movie : movieArrayList) {
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
				bitmapToByteArray(object.getmThumbnail()),
				object.getmTrailers()
		);
	}

	public static ArrayList<GridItem> ArrayListWithDetailMovieObjectToArrayListWithGridItem(ArrayList<DetailMovieObject> pageMovies) {
		ArrayList<GridItem> result = new ArrayList<>();

		for (DetailMovieObject movie : pageMovies) {
			if (movie.getmThumbnail() == null) {
				result.add(new GridItem(
						movie.getmPosterPath(),
						null
				));
			} else {
				result.add(new GridItem(
						null,
						movie.getmThumbnail()
				));
			}
		}

		return result;
	}

	public static byte[] bitmapToByteArray(Bitmap bitmap) {
		if (bitmap == null) {
			Log.d(LOG_TAG, "bitmapToByteArray: bitmap == null");
			return null;
		}
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);

		return outputStream.toByteArray();
	}

	public static Bitmap byteArrayToBitmao(byte[] array) {
		Bitmap bitmap = BitmapFactory.decodeByteArray(array, 0, array.length);

		return bitmap;
	}

	public static ArrayList<DetailMovieObject> convertDataMovieObjectToDetailMovieObject(ArrayList<DataMovieObject> data) {
		ArrayList<DetailMovieObject> returnData = new ArrayList<>();
		for (DataMovieObject a : data) {
			returnData.add(
					new DetailMovieObject(
							a.getmId(),
							a.getmTitle(),
							a.getmDescription(),
							a.getmRelease(),
							a.getmRating(),
							null, //trailers
							"", // PosterPath
							Converter.byteArrayToBitmao(a.getmThumbnail()),
							null // reviews
					)
			);
		}
		return returnData;
	}
}
