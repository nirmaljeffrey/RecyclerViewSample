package com.nirmal.jeffrey.recyclerviewsample.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MovieListResponse implements Parcelable {


  @SerializedName("page")
  @Expose
  private int page;
  @SerializedName("total_pages")
  @Expose
  private int totalPages;
  @SerializedName("results")
  @Expose
  private List<Movie> movieList;

  public MovieListResponse() {
  }

  public MovieListResponse(int page, int totalPages,
      List<Movie> movieList) {
    this.page = page;
    this.totalPages = totalPages;
    this.movieList = movieList;
  }


  protected MovieListResponse(Parcel in) {
    page = in.readInt();
    totalPages = in.readInt();
  }

  public static final Creator<MovieListResponse> CREATOR = new Creator<MovieListResponse>() {
    @Override
    public MovieListResponse createFromParcel(Parcel in) {
      return new MovieListResponse(in);
    }

    @Override
    public MovieListResponse[] newArray(int size) {
      return new MovieListResponse[size];
    }
  };

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }

  public List<Movie> getMovieList() {
    return movieList;
  }

  public void setMovieList(List<Movie> movieList) {
    this.movieList = movieList;
  }


  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(page);
    dest.writeInt(totalPages);
  }
}
