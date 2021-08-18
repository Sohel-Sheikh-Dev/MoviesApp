package com.example.moviesapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.moviesapp.adapters.MovieRecyclerView;
import com.example.moviesapp.databinding.ActivityMovieDetailsBinding;
import com.example.moviesapp.model.MovieModel;

import java.util.List;

public class MovieDetails extends AppCompatActivity {

    private ActivityMovieDetailsBinding binding;
    private ImageView ivDetails;
    private TextView titleDetails, descDetails;
    private RatingBar rbDetails;
//    private List<MovieModel> mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ivDetails = binding.imageViewDetails;
        titleDetails = binding.textViewTitleDetails;
        descDetails = binding.textViewDescDetails;
        rbDetails = binding.ratingBarDetails;

        GetDataFromIntent();
    }

    private void GetDataFromIntent() {
        if (getIntent().hasExtra("movie")) {
            MovieModel movieModel = getIntent().getParcelableExtra("movie");
            titleDetails.setText(movieModel.getTitle());
            descDetails.setText(movieModel.getMovie_overview());
            rbDetails.setRating(MovieRecyclerView.mMovie.get(2).getVote_average()/2);

            Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + movieModel.getPoster_path()).into(ivDetails);

        }
    }

}