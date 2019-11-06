package com.maurya.fliksearch;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maurya.fliksearch.pojo.Movie;

import java.util.List;

public class PostersAdapter extends RecyclerView.Adapter<PostersAdapter.PosterViewHolder> {

    private final List<Movie> movies;

    PostersAdapter(@NonNull List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView posterTextView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.poster, parent, false);
        return new PosterViewHolder(posterTextView);
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        holder.posterTextView.setText(movies.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class PosterViewHolder extends RecyclerView.ViewHolder {
        TextView posterTextView;

        PosterViewHolder(@NonNull TextView textView) {
            super(textView);
            posterTextView = textView;
        }
    }
}
