package com.maurya.fliksearch;

import android.os.Parcelable;

import com.maurya.fliksearch.pojo.Movie;

import java.util.List;

public interface MainActivityContract {

    interface Displayer {
        //        void displayNextPage();
        void showErrorMessage(String errorMessage);
        void showPosters(List<Movie> movies);
    }

    interface Presenter {
        void start(Displayer displayer);
        void stop();

        void fetchMovies();
        void onNextPageRequested();
//        void onRefreshButtonClicked();
    }

    interface Model extends Parcelable {
        void fetchMovies();
        void addObserver(MainActivityContract.ModelObserver modelObserver);
        void removeObserver();
        void unSubscribeSubscriptions();
        void reloadDependency(MovieService movieService);
    }

    interface ModelObserver {
        void onMoviesAvailable(List<Movie> movies);
        void onErrorFetchingMovies(String errorMessage);
    }
}
