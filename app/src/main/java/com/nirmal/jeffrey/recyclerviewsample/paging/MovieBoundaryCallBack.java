package com.nirmal.jeffrey.recyclerviewsample.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;
import com.nirmal.jeffrey.recyclerviewsample.database.MovieDao;
import com.nirmal.jeffrey.recyclerviewsample.model.Movie;
import com.nirmal.jeffrey.recyclerviewsample.model.MovieListResponse;
import com.nirmal.jeffrey.recyclerviewsample.network.NetworkClient;
import com.nirmal.jeffrey.recyclerviewsample.util.NetworkStatus;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.concurrent.Executor;

public class MovieBoundaryCallBack extends PagedList.BoundaryCallback<Movie> {

private NetworkClient networkClient;
private MovieDao movieDao;
private Executor executor;
private boolean isRequestInProgress = false;
private int pageToRequest;
private MutableLiveData<NetworkStatus> networkState = new MutableLiveData<>();

  public MovieBoundaryCallBack(MovieDao movieDao,NetworkClient networkClient,Executor executor, int initialPage) {
    super();
    this.movieDao = movieDao;
    this.networkClient = networkClient;
    this.executor = executor;
    this.pageToRequest = initialPage;
  }

  public MutableLiveData<NetworkStatus> getNetworkState() {
    return networkState;
  }

  @Override
  public void onZeroItemsLoaded() {
    super.onZeroItemsLoaded();
    requestAndSaveData();
  }

  @Override
  public void onItemAtFrontLoaded(@NonNull Movie itemAtFront) {
    super.onItemAtFrontLoaded(itemAtFront);
  }

  @Override
  public void onItemAtEndLoaded(@NonNull Movie itemAtEnd) {
    super.onItemAtEndLoaded(itemAtEnd);
    requestAndSaveData();
  }


  private void requestAndSaveData(){
    if(isRequestInProgress){
      return;
    }

    networkClient.getMovieApi().getMovieListByType(pageToRequest).subscribeOn(Schedulers.io()).observeOn(
        AndroidSchedulers.mainThread()).subscribe(new Observer<MovieListResponse>() {
      @Override
      public void onSubscribe(Disposable d) {
        networkState.setValue(NetworkStatus.loading());
        isRequestInProgress = true;
        }

      @Override
      public void onNext(MovieListResponse movieListResponse) {
        pageToRequest++;
        isRequestInProgress = false;
        if (!movieListResponse.getMovieList().isEmpty()) {
          networkState.postValue(NetworkStatus.success());
          insertMoviesIntoDB(movieListResponse.getMovieList());
        }else {
          networkState.setValue(NetworkStatus.empty());
        }

      }

      @Override
      public void onError(Throwable throwable) {
        String errorMessage = throwable.getMessage();
        if(errorMessage == null){
          errorMessage = "Unknown Error";
        }
        isRequestInProgress = false;
        networkState.setValue(NetworkStatus.error(errorMessage));

      }

      @Override
      public void onComplete() {

      }
    });

  }




  private void insertMoviesIntoDB(List<Movie> movies) {
    executor.execute(() -> movieDao.insertMovies(movies));
  }




}
