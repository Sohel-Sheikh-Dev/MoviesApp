package com.example.moviesapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.adapters.MovieRecyclerView;
import com.example.moviesapp.adapters.OnMovieListener;
import com.example.moviesapp.databinding.ActivityMainBinding;
import com.example.moviesapp.model.MovieModel;
import com.example.moviesapp.viewmodels.MovieListViewModel;

import java.util.List;

public class MovieListActivity extends AppCompatActivity implements OnMovieListener {

    ActivityMainBinding binding;
    private RecyclerView recyclerView;
    private MovieListViewModel movieListViewModel;
    private MovieRecyclerView movieRecyclerAdapter;
    boolean isPopular = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        SetUpSearchView();

        recyclerView = binding.recyclerView;
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        ConfigureRecyclerView();
        ObserveAnyChange();
        ObservePopularMovies();

        movieListViewModel.searchMoviePop(1);

    }

    private void SetUpSearchView() {
        final SearchView searchView = binding.searchView;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieListViewModel.searchMovieApi(query, 1);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnSearchClickListener(v -> {
            isPopular = false;
        });

    }

    private void ObserveAnyChange() {
        movieListViewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if (movieModels != null) {
                    for (MovieModel movieModel : movieModels) {
                        movieRecyclerAdapter.setmMovie(movieModels);
                    }
                }
            }
        });
    }

    private void ObservePopularMovies() {
        movieListViewModel.getPop().observe(this, movieModels -> {
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
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (!recyclerView.canScrollVertically(1)) {
                    movieListViewModel.searchNextPage();
                }
            }
        });

    }

    @Override
    public void onMovieClick(int position) {
        Intent intent = new Intent(this, MovieDetails.class);
        intent.putExtra("movie", movieRecyclerAdapter.getSelectedMovie(position));
        intent.putExtra("pos",position);
        startActivity(intent);
    }

    @Override
    public void onCategoryClick(String category) {

    }

}