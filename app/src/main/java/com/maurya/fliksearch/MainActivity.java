package com.maurya.fliksearch;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.maurya.fliksearch.pojo.Movie;
import com.maurya.fliksearch.pojo.MovieServiceResponse;

import java.util.List;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView postersRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postersRecyclerView = findViewById(R.id.posters_recycler_view);
        postersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        MovieService movieService = ((FlikSearchApplication) getApplication()).getMovieService();
        Observable<MovieServiceResponse> moviesObservable = movieService.retrieveMovies();

        MovieServiceObserver movieServiceObserver = new MovieServiceObserver();
        moviesObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieServiceObserver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // unsubscribe rxjava observable
    }

    private class MovieServiceObserver implements Observer<MovieServiceResponse> {

        @Override
        public void onSubscribe(Disposable d) {
            // no-op
        }

        @Override
        public void onNext(MovieServiceResponse response) {
            List<Movie> movies = response.getResults();
            assert movies != null;
            postersRecyclerView.setAdapter(new PostersAdapter(movies));
        }

        @Override
        public void onError(Throwable e) {
            // show text or image on page asking user to refresh. May be provide a refresh button.
            // check if the failure is because of bad request
            Toast.makeText(MainActivity.this, "Response failure!", Toast.LENGTH_LONG).show();
            Log.e("API response failure", Objects.requireNonNull(e.getLocalizedMessage()));
        }

        @Override
        public void onComplete() {
            // no-op
        }
    }
}
