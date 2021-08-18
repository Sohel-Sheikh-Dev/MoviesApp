package com.example.moviesapp.repositories;

import androidx.lifecycle.LiveData;

import com.example.moviesapp.model.MovieModel;
import com.example.moviesapp.request.MovieApiClient;

import java.util.List;

public class MovieRepository {

    private MovieApiClient movieApiClient;

    private static MovieRepository instance;

    private String mQuery;
    private int mPageNumber;

    public static MovieRepository getInstance() {
        if (instance == null) {
            instance = new MovieRepository();
        }
        return instance;
    }

    private MovieRepository() {
        movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies() {
        return movieApiClient.getMovies();

    }

    public LiveData<List<MovieModel>> getPop() {
        return movieApiClient.getMoviesPop();

    }


    public void searchMovieApi(String query, int pageNumber) {
        mQuery = query;
        mPageNumber = pageNumber;
        movieApiClient.searchMovieApi(query,pageNumber);
    }

    public void searchMoviePop(int pageNumber) {
        mPageNumber = pageNumber;
        movieApiClient.searchMoviePop(pageNumber);
    }


    public void searchNextPage(){
        searchMovieApi(mQuery,mPageNumber+1);
    }


}
