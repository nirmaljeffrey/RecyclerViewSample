package com.nirmal.jeffrey.recyclerviewsample.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.nirmal.jeffrey.recyclerviewsample.database.MovieDao;
import com.nirmal.jeffrey.recyclerviewsample.database.MovieDatabase;
import com.nirmal.jeffrey.recyclerviewsample.model.Movie;
import com.nirmal.jeffrey.recyclerviewsample.network.NetworkClient;
import com.nirmal.jeffrey.recyclerviewsample.paging.MovieBoundaryCallBack;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MovieRepository {

  private static MovieRepository movieRepository;
  private MovieDao movieDao;
  private Executor executor;
  private NetworkClient networkClient;
  private static final int PAGE_NUMBER_ONE = 1;
  private static final int PAGE_LIST_SIZE=20;


  private MovieRepository(Application application){
    movieDao = MovieDatabase.getInstance(application).movieDao();
    executor = Executors.newSingleThreadExecutor();
    this.networkClient = NetworkClient.getInstance();
  }




  public static MovieRepository getInstance(Application application){
    if(movieRepository == null){
      movieRepository = new MovieRepository(application);
    }
    return movieRepository;
  }

  public LiveData<PagedList<Movie>> getPagedMoviesList() {
    MovieBoundaryCallBack boundaryCallBack = new MovieBoundaryCallBack(movieDao, networkClient,
        executor, PAGE_NUMBER_ONE);
    PagedList.Config pageListConfig = new PagedList.Config.Builder()
        .setPageSize(PAGE_LIST_SIZE)
        .setEnablePlaceholders(false)
        .build();
    return new LivePagedListBuilder<>(movieDao.getMovieList(), pageListConfig)
        .setBoundaryCallback(boundaryCallBack).build();
  }

  public void deleteMovieFromDB(int movieId) {
    executor.execute(() -> movieDao.deleteMovie(movieId));
  }

  public LiveData<Movie> getMovieFromDB(int movieId) {
    return movieDao.getMovie(movieId);
  }



}
