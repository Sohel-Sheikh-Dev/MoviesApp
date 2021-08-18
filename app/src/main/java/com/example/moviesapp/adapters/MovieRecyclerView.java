package com.example.moviesapp.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.R;
import com.example.moviesapp.databinding.PopularMoviesLayoutBinding;
import com.example.moviesapp.model.MovieModel;
import com.example.moviesapp.utilities.Credentials;

import java.util.List;

public class MovieRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static List<MovieModel> mMovie;
    private OnMovieListener onMovieListener;

    private static final int DISPLAY_POP = 1;
    private static final int DISPLAY_SEARCH = 1;

    public MovieRecyclerView(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if(viewType == DISPLAY_SEARCH){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
            return new MovieViewHolder(view,onMovieListener);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_movies_layout, parent, false);
            return new Popular_View_Holder(view,onMovieListener);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        int itemViewType = getItemViewType(position);
        if(itemViewType == DISPLAY_SEARCH){
            ((MovieViewHolder) holder).ratingBar.setRating((mMovie.get(position).getVote_average()) / 2);
            Log.v("Tag","Rating: "+mMovie.get(position).getVote_average() / 2);
            Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500/"+mMovie.get(position).getPoster_path()).into(((MovieViewHolder) holder).imageView);
        }else{
            ((Popular_View_Holder) holder).ratingBar22.setRating((mMovie.get(position).getVote_average()) / 2);
            Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500/"+mMovie.get(position).getPoster_path()).into(((Popular_View_Holder) holder).imageView22);
        }

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

    @Override
    public int getItemViewType(int position) {
        if(Credentials.POPULAR){
            return DISPLAY_POP;
        }else{
            return DISPLAY_SEARCH;
        }

    }
}
