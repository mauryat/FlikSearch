package com.maurya.fliksearch.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Movie implements Parcelable {
    private float popularity;
    private float vote_count;
    private boolean video;
    private String poster_path;
    private float id;
    private boolean adult;
    private String backdrop_path;
    private String original_language;
    private String original_title;
    List<Integer> genre_ids = new ArrayList<>();
    private String title;
    private float vote_average;
    private String overview;
    private String release_date;


    // Getter Methods

    public float getPopularity() {
        return popularity;
    }

    public float getVote_count() {
        return vote_count;
    }

    public boolean getVideo() {
        return video;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public float getId() {
        return id;
    }

    public boolean getAdult() {
        return adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getTitle() {
        return title;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    // Setter Methods

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public void setVote_count(float vote_count) {
        this.vote_count = vote_count;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setId(float id) {
        this.id = id;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.popularity);
        dest.writeFloat(this.vote_count);
        dest.writeByte(this.video ? (byte) 1 : (byte) 0);
        dest.writeString(this.poster_path);
        dest.writeFloat(this.id);
        dest.writeByte(this.adult ? (byte) 1 : (byte) 0);
        dest.writeString(this.backdrop_path);
        dest.writeString(this.original_language);
        dest.writeString(this.original_title);
        dest.writeList(this.genre_ids);
        dest.writeString(this.title);
        dest.writeFloat(this.vote_average);
        dest.writeString(this.overview);
        dest.writeString(this.release_date);
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.popularity = in.readFloat();
        this.vote_count = in.readFloat();
        this.video = in.readByte() != 0;
        this.poster_path = in.readString();
        this.id = in.readFloat();
        this.adult = in.readByte() != 0;
        this.backdrop_path = in.readString();
        this.original_language = in.readString();
        this.original_title = in.readString();
        this.genre_ids = new ArrayList<Integer>();
        in.readList(this.genre_ids, Integer.class.getClassLoader());
        this.title = in.readString();
        this.vote_average = in.readFloat();
        this.overview = in.readString();
        this.release_date = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}