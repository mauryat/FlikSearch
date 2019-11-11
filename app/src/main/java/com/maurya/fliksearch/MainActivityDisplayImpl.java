package com.maurya.fliksearch;

import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.maurya.fliksearch.pojo.Movie;

import java.util.List;

class MainActivityDisplayImpl implements MainActivityContract.Displayer {

    private final MainActivity activity;
    private final MainActivityContract.Presenter presenter; // can be used when there is a refresh button to requery

    private RecyclerView postersRecyclerView;

    MainActivityDisplayImpl(MainActivity mainActivity, MainActivityContract.Presenter presenter) {
        this.activity = mainActivity;
        this.presenter = presenter;

        setupRecyclerView();
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(activity, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showPosters(List<Movie> movies) {
        postersRecyclerView.setAdapter(new PostersAdapter(movies));
    }

    private void setupRecyclerView() {
        postersRecyclerView = activity.findViewById(R.id.posters_recycler_view);
        postersRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        postersRecyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(postersRecyclerView.getContext(), linearLayoutManager
                .getOrientation());
        postersRecyclerView.addItemDecoration(dividerItemDecoration);
    }
}
