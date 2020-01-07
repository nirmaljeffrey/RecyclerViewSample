package com.nirmal.jeffrey.recyclerviewsample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.nirmal.jeffrey.recyclerviewsample.R;
import com.nirmal.jeffrey.recyclerviewsample.adapter.MoviePagedListAdapter.MovieViewHolder;
import com.nirmal.jeffrey.recyclerviewsample.databinding.MovieListItemBinding;
import com.nirmal.jeffrey.recyclerviewsample.model.Movie;

public class MoviePagedListAdapter extends PagedListAdapter<Movie, MovieViewHolder> {
private OnMovieClickListener movieClickListener;
  private static DiffUtil.ItemCallback<Movie> DIFF_CALLBACK = new ItemCallback<Movie>() {

    @Override
    public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
      return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Movie oldItem,
        @NonNull Movie newItem) {
      return oldItem.equals(newItem);
    }
  };

  public MoviePagedListAdapter(OnMovieClickListener movieClickListener) {
    super(DIFF_CALLBACK);
    this.movieClickListener = movieClickListener;
  }

  public interface OnMovieClickListener{
    void onMovieClick(Movie movie);
  }
  @NonNull
  @Override
  public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    MovieListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
        R.layout.movie_list_item, parent, false);
    return new MovieViewHolder(binding);
  }


  @Override
  public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
    holder.onBind(getItem(position));
  }


  public class MovieViewHolder extends ViewHolder {

    private MovieListItemBinding binding;

    public MovieViewHolder(@NonNull MovieListItemBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
      binding.posterImageView.setOnClickListener(view -> {
        int position = getAdapterPosition();
        if(position != RecyclerView.NO_POSITION){
          movieClickListener.onMovieClick(getItem(position));
        }
      });
    }

    public void onBind(Movie movie) {
      binding.setMovie(movie);
      binding.executePendingBindings();
    }
  }

}
