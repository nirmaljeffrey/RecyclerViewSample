package com.nirmal.jeffrey.recyclerviewsample.network;

import com.nirmal.jeffrey.recyclerviewsample.model.MovieListResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

  @GET("movie/popular")
  Observable<MovieListResponse> getMovieListByType(@Query("page") int page);

}
