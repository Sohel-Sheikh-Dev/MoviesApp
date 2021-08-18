package com.example.moviesapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MovieModel implements Parcelable {

    //Model class for movies
    private String title,poster_path,release_date;

    @SerializedName("overview")
    private String movie_overview;
//    @SerializedName("id")
    private int movie_id;

    @SerializedName("vote_average")
    private float vote_average;

    private String original_language;


    //Constructor
    public MovieModel(String title, String poster_path, String release_date, String movie_overview, int movie_id, float vote_average,String original_language) {
        this.title = title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.movie_overview = movie_overview;
        this.movie_id = movie_id;
        this.vote_average = vote_average;
        this.original_language = original_language;
    }


    protected MovieModel(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        movie_overview = in.readString();
        movie_id = in.readInt();
        vote_average = in.readFloat();
        original_language = in.readString();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    //Getters
    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getMovie_overview() {
        return movie_overview;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getOriginal_language() {
        return original_language;
    }

    //Parcelable methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "MovieModel{" +
                "title='" + title + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", release_date='" + release_date + '\'' +
                ", movie_overview='" + movie_overview + '\'' +
                ", movie_id=" + movie_id +
                ", vote_average=" + vote_average +
                ", original_language='" + original_language + '\'' +
                '}';
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(poster_path);
        dest.writeString(release_date);
        dest.writeString(movie_overview);
        dest.writeInt(movie_id);
        dest.writeString(original_language);
    }

}
