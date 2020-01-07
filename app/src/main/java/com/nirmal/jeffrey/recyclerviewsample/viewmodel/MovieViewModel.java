package com.nirmal.jeffrey.recyclerviewsample.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.paging.PagedList;
import com.nirmal.jeffrey.recyclerviewsample.model.Movie;
import com.nirmal.jeffrey.recyclerviewsample.repository.MovieRepository;
import java.util.List;

public class MovieViewModel extends AndroidViewModel {
private MovieRepository movieRepository;
  public MovieViewModel(@NonNull Application application) {
    super(application);
    movieRepository = MovieRepository.getInstance(application);
  }


public LiveData<PagedList<Movie>> getPagedMovieList(){
    return movieRepository.getPagedMoviesList();
}



}
