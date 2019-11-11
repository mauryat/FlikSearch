package com.maurya.fliksearch;

import androidx.annotation.NonNull;

import com.maurya.fliksearch.pojo.Movie;

import java.util.List;

class MainActivityPresenter implements MainActivityContract.Presenter, MainActivityContract.ModelObserver {

    private MainActivityContract.Displayer displayer;
    private MainActivityContract.Model model;
    private MainActivity activity; // can be used to finish activity
    private boolean loading;

    MainActivityPresenter(MainActivityContract.Model model, MainActivity mainActivity) {
        this.model = model;
        this.activity = mainActivity;
    }

    @Override
    public void start(MainActivityContract.Displayer displayer) {
        this.displayer = displayer;

        model.addModelObserver(this);
        model.fetchMovies(false);
    }

    @Override
    public void stop() {
        model.removeModelObserver();
        model.unSubscribeSubscriptions();
    }

    @Override
    public void onPageScrolled(int visibleItemCount, int pastVisiblesItems, int totalItemCount) {
        // can be used while implementing pagination
        if(loading) {
            if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                loading = false;
                model.fetchMovies(true); // retrieveOffers(offset++, null);
            }
        }
    }

    /* ModelObserver implementation */
    @Override
    public void onMoviesAvailable(@NonNull List<Movie> movies) {
        displayer.showPosters(movies);
        loading = true;
    }

    @Override
    public void onErrorFetchingMovies(String errorMessage) {
        // TODO: 11/10/2019 convert to user readable message
        displayer.showErrorMessage(errorMessage);
        loading = true;
    }
}
