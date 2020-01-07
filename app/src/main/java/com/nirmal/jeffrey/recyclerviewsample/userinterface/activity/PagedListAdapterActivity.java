package com.nirmal.jeffrey.recyclerviewsample.userinterface.activity;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import com.nirmal.jeffrey.recyclerviewsample.R;
import com.nirmal.jeffrey.recyclerviewsample.adapter.MoviePagedListAdapter;
import com.nirmal.jeffrey.recyclerviewsample.adapter.MoviePagedListAdapter.OnMovieClickListener;
import com.nirmal.jeffrey.recyclerviewsample.databinding.ActivityPagedListAdapterBinding;
import com.nirmal.jeffrey.recyclerviewsample.model.Movie;
import com.nirmal.jeffrey.recyclerviewsample.viewmodel.MovieViewModel;

public class PagedListAdapterActivity extends AppCompatActivity implements OnMovieClickListener {

  private MovieViewModel movieViewModel;
  private MoviePagedListAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityPagedListAdapterBinding binding = DataBindingUtil
        .setContentView(this, R.layout.activity_paged_list_adapter);
    setTitle(PagedListAdapterActivity.class.getSimpleName());
    movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
    adapter = new MoviePagedListAdapter(this);
    binding.recyclerView.setAdapter(adapter);
    subscribeObservers();
  }

  private void subscribeObservers() {
    movieViewModel.getPagedMovieList().observe(this, movies -> adapter.submitList(movies));
  }

  @Override
  public void onMovieClick(Movie movie) {
    Toast.makeText(this, movie.getTitle(), Toast.LENGTH_SHORT).show();
  }
}
