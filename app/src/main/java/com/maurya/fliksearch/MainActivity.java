package com.maurya.fliksearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.maurya.fliksearch.pojo.Movie;
import com.maurya.fliksearch.pojo.MovieServiceResponse;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView postersRecyclerView = findViewById(R.id.posters_recycler_view);
        postersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        MovieService movieService = ((FlikSearchApplication) getApplication()).getMovieService();
        Call<MovieServiceResponse> moviesObservable = movieService.retrieveMovies();
        moviesObservable.enqueue(new Callback<MovieServiceResponse>() {
            @Override
            public void onResponse(Call<MovieServiceResponse> call, Response<MovieServiceResponse> response) {
                List<Movie> movies = response.body().getResults();
                assert movies != null;
                postersRecyclerView.setAdapter(new PostersAdapter(movies));
            }

            @Override
            public void onFailure(Call<MovieServiceResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Response failure!", Toast.LENGTH_LONG).show();
                Log.e("API response failure", Objects.requireNonNull(t.getLocalizedMessage()));
            }
        });
    }
}
