package com.pascaldierich.popularmoviesstage2.utils;

import com.pascaldierich.popularmoviesstage2.presentation.converters.model.DetailMovieObject;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.impl.DetailPresenterImpl;
import com.pascaldierich.popularmoviesstage2.presentation.presenters.impl.MainFragmentPresenterImpl;

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


	private static boolean sTwoPaneMode;

	public static void setTwoPaneMode(boolean mode) {
		sTwoPaneMode = mode;
	}

	public static boolean getTwoPaneMode() {
		return sTwoPaneMode;
	}


	private static DetailPresenterImpl sDetailPresenter;

	public static void setDetailPresenterImpl(DetailPresenterImpl presenter) {
		sDetailPresenter = presenter;
	}

	public static DetailPresenterImpl getDetailPresenterImpl() {
		return sDetailPresenter;
	}


	private static MainFragmentPresenterImpl sMainFragmentPresenter;

	public static void setMainFragmentPresenterImpl(MainFragmentPresenterImpl presenter) {
		sMainFragmentPresenter = presenter;
	}

	public static MainFragmentPresenterImpl getMainFragmentPresenterImpl() {
		return sMainFragmentPresenter;
	}
}
