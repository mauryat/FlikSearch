package com.maurya.fliksearch;

import android.os.Parcelable;

import com.maurya.fliksearch.pojo.Movie;

import java.util.List;

public interface MainActivityContract {

    interface Displayer {
        void showErrorMessage(String errorMessage);
        void showPosters(List<Movie> movies);
    }

    interface Presenter {
        void start(Displayer displayer);
        void stop();

        void onPageScrolled(int visibleItemCount, int pastVisibleItems, int totalItemCount);
//        void onRefreshButtonClicked();
    }

    interface Model extends Parcelable {
        void fetchMovies(boolean nextPage);
        void unSubscribeSubscriptions();

        // if movieService is injected using dagger, reload dependency can be used
        void reloadDependency(MovieService movieService);

        // to prevent memory leaks when activity is destroyed
        void addModelObserver(MainActivityContract.ModelObserver modelObserver);
        void removeModelObserver();
    }

    // helps ensure model is oblivious to presenter
    interface ModelObserver {
        void onMoviesAvailable(List<Movie> movies);
        void onErrorFetchingMovies(String errorMessage);
    }
}
