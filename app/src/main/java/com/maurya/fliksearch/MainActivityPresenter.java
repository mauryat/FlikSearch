package com.maurya.fliksearch;

import androidx.annotation.NonNull;

import com.maurya.fliksearch.pojo.Movie;

import java.util.List;

class MainActivityPresenter implements MainActivityContract.Presenter, MainActivityContract.ModelObserver {

    private MainActivityContract.Displayer displayer;
    private MainActivityContract.Model model;
    private MainActivity activity; // can be used to finish activity

    MainActivityPresenter(MainActivityContract.Model model, MainActivity mainActivity) {
        this.model = model;
        this.activity = mainActivity;
    }

    @Override
    public void start(MainActivityContract.Displayer displayer) {
        this.displayer = displayer;

        model.addObserver(this);
        fetchMovies();
    }

    @Override
    public void stop() {
        model.removeObserver();
        model.unSubscribeSubscriptions();
    }

    @Override
    public void fetchMovies() {
        model.fetchMovies();
    }

    @Override
    public void onNextPageRequested() {
        // can be used while implementing pagination
    }

    /* ModelObserver implementation */
    @Override
    public void onMoviesAvailable(@NonNull List<Movie> movies) {
        displayer.showPosters(movies);
    }

    @Override
    public void onErrorFetchingMovies(String errorMessage) {
        displayer.showErrorMessage(errorMessage);
    }
}
