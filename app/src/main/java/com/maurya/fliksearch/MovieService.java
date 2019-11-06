package com.maurya.fliksearch;

import com.maurya.fliksearch.pojo.MovieServiceResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MovieService {
    @GET("3/discover/movie?api_key=46fe8dd7da7c4f30545bf0e189597639&page=1")
    Observable<MovieServiceResponse> retrieveMovies();
}
