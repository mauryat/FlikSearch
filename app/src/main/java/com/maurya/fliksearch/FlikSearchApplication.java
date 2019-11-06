package com.maurya.fliksearch;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FlikSearchApplication extends Application {

    private MovieService movieService;

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        movieService = retrofit.create(MovieService.class);
    }

    public MovieService getMovieService() {
        return movieService;
    }
}
