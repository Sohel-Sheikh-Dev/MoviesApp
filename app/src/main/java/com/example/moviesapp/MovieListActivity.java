package com.example.moviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.adapters.MovieRecyclerView;
import com.example.moviesapp.adapters.OnMovieListener;
import com.example.moviesapp.databinding.ActivityMainBinding;
import com.example.moviesapp.model.MovieModel;
import com.example.moviesapp.viewmodels.MovieListViewModel;

public class MovieListActivity extends AppCompatActivity implements OnMovieListener {

    ActivityMainBinding binding;
    //    Button btn;
    private RecyclerView recyclerView;
    private MovieListViewModel movieListViewModel;
    private MovieRecyclerView movieRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);
        recyclerView = binding.recyclerView;

        SetUpSearchView();
        ConfigureRecyclerView();
        ObserveAnyChange();



//        searchMovieApi("fast",1);
//        btn = binding.button;
//        btn.setOnClickListener(v -> {
//            int pn = MovieApiClient.getInstance().page;
//            searchMovieApi("Avengers", 1);
//        });

    }

    private void SetUpSearchView() {
        final SearchView searchView = binding.searchView;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieListViewModel.searchMovieApi(query,1);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

//    public void searchMovieApi(String query, int pageNumber) {
//        movieListViewModel.searchMovieApi(query, pageNumber);
//    }

    private void ObserveAnyChange() {
        movieListViewModel.getMovies().observe(this, movieModels -> {
            if (movieModels != null) {
                for (MovieModel movieModel : movieModels) {
//                    Log.v("Tag", "onChanged: " + movieModel.getTitle());
                    movieRecyclerAdapter.setmMovie(movieModels);
                }
            }
        });
    }

    private void ConfigureRecyclerView() {
        movieRecyclerAdapter = new MovieRecyclerView(this);
        recyclerView.setAdapter(movieRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(!recyclerView.canScrollVertically(1)){
                    movieListViewModel.searchNextPage();
                }
            }
        });

    }

    @Override
    public void onMovieClick(int position) {
//        Toast.makeText(this,"The position: "+position,Toast.LENGTH_SHORT).show();
//This one
        Intent intent = new Intent(this,MovieDetails.class);
        intent.putExtra("movie",movieRecyclerAdapter.getSelectedMovie(position));
        startActivity(intent);
    }
    //Thats the problem

    @Override
    public void onCategoryClick(String category) {

    }

/*
    private void GetRetrofitResponse() {

        MovieApi movieApi = Servicey.getMovieApi();

        Call<MovieSearchResponse> responseCall = movieApi.searchMovie(Credentials.API_KEY, "Jack Reacher", 1);

        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if (response.code() == 200) {
//                    Log.v("Tag", "The Response: " + response.body().toString());
                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                    for (MovieModel movie : movies) {
                        Log.v("Tag", "The Release Date: " + movie.getRelease_date());
                    }
                } else {
                    try {
                        Log.v("Tag", "Error: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private void GetRetrofitResponseAccordingToID() {
        MovieApi movieApi = Servicey.getMovieApi();

        Call<MovieModel> responseCall = movieApi.getMovie(343611, Credentials.API_KEY);

        responseCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if (response.code() == 200) {
                    MovieModel movie = response.body();
                    Log.v("Tag", "The Response: " + movie.getTitle());
                } else {
                    try {
                        Log.v("Tag", "Error: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });
    }
*/
}