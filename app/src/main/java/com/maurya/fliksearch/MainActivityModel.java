package com.maurya.fliksearch;

import android.os.Parcel;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.maurya.fliksearch.pojo.Movie;
import com.maurya.fliksearch.pojo.MovieServiceResponse;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

class MainActivityModel implements MainActivityContract.Model {

    static final String PARCELABLE_MODEL = "main_activity_model";

    private List<Movie> movies;
    private int page = 1;

    @Nullable
    private MainActivityContract.ModelObserver modelObserver;
    private MovieServiceObserver movieServiceObserver;

    private MovieService movieService;

    MainActivityModel(@NonNull MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public void reloadDependency(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public void addModelObserver(MainActivityContract.ModelObserver modelObserver) {
        this.modelObserver = modelObserver;
    }

    @Override
    public void removeModelObserver() {
        this.modelObserver = null;
    }

    @Override
    public void fetchMovies(boolean nextPage) {
        assert modelObserver != null;

        if(movies != null && !nextPage) {
            modelObserver.onMoviesAvailable(movies);
            return;
        }

        assert movieService != null; // Use reloadDependency() before making API calls.
        Observable<MovieServiceResponse> moviesObservable = movieService.retrieveMovies(page);

        if(movieServiceObserver != null) {
            // movieService.unsubscribe();
        }

        MovieServiceObserver movieServiceObserver = new MovieServiceObserver(modelObserver);
        moviesObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieServiceObserver);
    }

    @Override
    public void unSubscribeSubscriptions() {
        if(movieServiceObserver != null) {
            //myListedItemsSubscriber.unsubscribe();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.movies);
        dest.writeInt(this.page);
    }

    private MainActivityModel(Parcel in) {
        this.movies = in.createTypedArrayList(Movie.CREATOR);
        this.page = in.readInt();
    }

    public static final Creator<MainActivityModel> CREATOR = new Creator<MainActivityModel>() {
        @Override
        public MainActivityModel createFromParcel(Parcel source) {
            return new MainActivityModel(source);
        }

        @Override
        public MainActivityModel[] newArray(int size) {
            return new MainActivityModel[size];
        }
    };

    private void setItems(@NonNull List<Movie> movies) {
        this.movies = movies;
    }

    private class MovieServiceObserver implements Observer<MovieServiceResponse> {

        @NonNull private MainActivityContract.ModelObserver modelObserver;

        MovieServiceObserver(@NonNull MainActivityContract.ModelObserver modelObserver) {
            this.modelObserver = modelObserver;
        }

        @Override
        public void onSubscribe(Disposable d) {
            // no-op
        }

        @Override
        public void onNext(MovieServiceResponse response) {
            if(response == null) {
                onError(new Exception("null response!"));
                return;
            }

            List<Movie> movies = processMovieResponse(response);//response.getResults();
            assert movies != null;

            if(MainActivityModel.this.movies == null) {
                MainActivityModel.this.setItems(movies);
            } else {
                MainActivityModel.this.movies.addAll(movies);
            }
            modelObserver.onMoviesAvailable(MainActivityModel.this.movies);
            page++;
        }

        @Override
        public void onError(Throwable e) {
            // show text or image on page asking user to refresh. May be provide a refresh button.
            // check if the failure is because of bad request
            Log.e("API response failure", Objects.requireNonNull(e.getLocalizedMessage()));
            modelObserver.onErrorFetchingMovies(e.getLocalizedMessage());
        }

        @Override
        public void onComplete() {
            // no-op
        }

        private List<Movie> processMovieResponse(MovieServiceResponse movieServiceResponse) {
            if (movieServiceResponse.getResults() == null) {
                return Collections.emptyList();
            }

            return movieServiceResponse.getResults();
        }
    }
}
