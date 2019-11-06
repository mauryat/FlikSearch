package com.maurya.fliksearch;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maurya.fliksearch.pojo.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostersAdapter extends RecyclerView.Adapter<PostersAdapter.PosterViewHolder> {

    private final List<Movie> movies;

    PostersAdapter(@NonNull List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageView posterImageView = (ImageView) LayoutInflater.from(parent.getContext()).inflate(R.layout.poster, parent, false);
        return new PosterViewHolder(posterImageView);
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        String posterPath = movies.get(position).getPoster_path();
        String fullPosterPath = "https://image.tmdb.org/t/p/w500" + posterPath;
        Picasso.with(holder.posterImageView.getContext()).load(fullPosterPath).into(holder.posterImageView);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class PosterViewHolder extends RecyclerView.ViewHolder {
        ImageView posterImageView;

        PosterViewHolder(@NonNull ImageView imageView) {
            super(imageView);
            posterImageView = imageView;
        }
    }
}
