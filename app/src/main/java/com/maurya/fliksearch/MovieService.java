package com.maurya.fliksearch;

import com.maurya.fliksearch.pojo.MovieServiceResponse;

import dagger.Provides;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {
    @GET("3/discover/movie?api_key=46fe8dd7da7c4f30545bf0e189597639") // move to string for better maintainability
    Observable<MovieServiceResponse> retrieveMovies(@Query("page") int page);

    @dagger.Module
    class Module {

        @Provides
        MovieService provideMovieService() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            return retrofit.create(MovieService.class);
        }
    }
}
