package com.maurya.fliksearch;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

import static com.maurya.fliksearch.MainActivityModel.PARCELABLE_MODEL;

public class MainActivity extends AppCompatActivity {

    private MainActivityContract.Displayer displayer;
    private MainActivityContract.Presenter presenter;
    private MainActivityContract.Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieService movieService = ((FlikSearchApplication) getApplication()).getMovieService();
        if(savedInstanceState != null && savedInstanceState.containsKey(PARCELABLE_MODEL)) {
            model = savedInstanceState.getParcelable(PARCELABLE_MODEL);
            assert model != null;
            model.reloadDependency(movieService);
        }

        if(model == null) {
            model = new MainActivityModel(movieService);
        }

        presenter = new MainActivityPresenter(model, this);
        displayer = new MainActivityDisplayImpl(this, presenter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start(displayer);
    }

    @Override
    protected void onStop() {
        presenter.stop();
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(PARCELABLE_MODEL, model);
        super.onSaveInstanceState(outState);
    }
}
