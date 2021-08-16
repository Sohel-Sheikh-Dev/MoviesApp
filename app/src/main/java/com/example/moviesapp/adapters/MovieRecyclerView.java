package com.example.moviesapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.R;
import com.example.moviesapp.model.MovieModel;

import java.util.List;

public class MovieRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieModel> mMovie;
    private OnMovieListener onMovieListener;

    public MovieRecyclerView(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new MovieViewHolder(view, onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MovieViewHolder) holder).title.setText(mMovie.get(position).getTitle());
        ((MovieViewHolder) holder).releaseDate.setText(mMovie.get(position).getRelease_date());
        ((MovieViewHolder) holder).duration.setText(mMovie.get(position).getOriginal_language());
        ((MovieViewHolder) holder).ratingBar.setRating((mMovie.get(position).getVote_average()) / 2);//HEre I have set the rating but
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500/"+mMovie.get(position).getPoster_path()).into(((MovieViewHolder) holder).imageView);

    }

    @Override
    public int getItemCount() {
        if (mMovie != null) {
            return mMovie.size();
        }
        return 0;

    }

    public void setmMovie(List<MovieModel> mMovie) {
        this.mMovie = mMovie;
        notifyDataSetChanged();
    }

    public MovieModel getSelectedMovie(int position) {
        if(mMovie != null){
            if(mMovie.size() > 0){
                return mMovie.get(position);
            }
        }
        return null;
    }

}
