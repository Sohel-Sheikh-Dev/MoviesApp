package com.example.moviesapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.moviesapp.databinding.ActivityMovieDetailsBinding;
import com.example.moviesapp.model.MovieModel;

public class MovieDetails extends AppCompatActivity {

    private ActivityMovieDetailsBinding binding;
    private ImageView ivDetails;
    private TextView titleDetails, descDetails;
    private RatingBar rbDetails;

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
//            Log.v("Tag","incoming intent: "+movieModel.getTitle());

            titleDetails.setText(movieModel.getTitle());
            descDetails.setText(movieModel.getMovie_overview());
            rbDetails.setRating((movieModel.getVote_average())/2);//HEre also I have set,but its not showing,so whats the solution??

            Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + movieModel.getPoster_path()).into(ivDetails);

        }
    }

}