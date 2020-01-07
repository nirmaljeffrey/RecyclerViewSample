package com.nirmal.jeffrey.recyclerviewsample.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "movie_table")
public class Movie implements Parcelable {
  @PrimaryKey
  @SerializedName("id")
  @Expose
  private int id;
  @SerializedName("popularity")
  @Expose
  private double popularity;
  @ColumnInfo(name = "vote_count")
  @SerializedName("vote_count")
  @Expose
  private int voteCount;
  @ColumnInfo(name = "poster_path")
  @SerializedName("poster_path")
  @Expose
  private String posterPath;
  @ColumnInfo(name = "backdrop_path")
  @SerializedName("backdrop_path")
  @Expose
  private String backdropPath;
  @SerializedName("title")
  @Expose
  private String title;
  @ColumnInfo(name = "vote_average")
  @SerializedName("vote_average")
  @Expose
  private float voteAverage;
  @SerializedName("overview")
  @Expose
  private String overview;
  @ColumnInfo(name = "release_date")
  @SerializedName("release_date")
  @Expose
  private String releaseDate;
@Ignore
  public Movie() {
  }

  public Movie(double popularity, int voteCount, String posterPath, int id,
      String backdropPath, String title, float voteAverage, String overview,
      String releaseDate) {
    this.popularity = popularity;
    this.voteCount = voteCount;
    this.posterPath = posterPath;
    this.id = id;
    this.backdropPath = backdropPath;
    this.title = title;
    this.voteAverage = voteAverage;
    this.overview = overview;
    this.releaseDate = releaseDate;
  }
  @Ignore
  public Movie(Movie movie){
    this.popularity = movie.popularity;
    this.voteCount = movie.voteCount;
    this.posterPath = movie.posterPath;
    this.id = movie.id;
    this.backdropPath = movie.backdropPath;
    this.title = movie.title;
    this.voteAverage = movie.voteAverage;
    this.overview = movie.overview;
    this.releaseDate = movie.releaseDate;
  }


  protected Movie(Parcel in) {
    id = in.readInt();
    popularity = in.readDouble();
    voteCount = in.readInt();
    posterPath = in.readString();
    backdropPath = in.readString();
    title = in.readString();
    voteAverage = in.readFloat();
    overview = in.readString();
    releaseDate = in.readString();
  }

  public static final Creator<Movie> CREATOR = new Creator<Movie>() {
    @Override
    public Movie createFromParcel(Parcel in) {
      return new Movie(in);
    }

    @Override
    public Movie[] newArray(int size) {
      return new Movie[size];
    }
  };

  public double getPopularity() {
    return popularity;
  }

  public void setPopularity(double popularity) {
    this.popularity = popularity;
  }

  public int getVoteCount() {
    return voteCount;
  }

  public void setVoteCount(int voteCount) {
    this.voteCount = voteCount;
  }

  public String getPosterPath() {
    return posterPath;
  }

  public void setPosterPath(String posterPath) {
    this.posterPath = posterPath;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getBackdropPath() {
    return backdropPath;
  }

  public void setBackdropPath(String backdropPath) {
    this.backdropPath = backdropPath;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public float getVoteAverage() {
    return voteAverage;
  }

  public void setVoteAverage(float voteAverage) {
    this.voteAverage = voteAverage;
  }

  public String getOverview() {
    return overview;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }


  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeInt(id);
    parcel.writeDouble(popularity);
    parcel.writeInt(voteCount);
    parcel.writeString(posterPath);
    parcel.writeString(backdropPath);
    parcel.writeString(title);
    parcel.writeFloat(voteAverage);
    parcel.writeString(overview);
    parcel.writeString(releaseDate);
  }

  @NonNull
  @Override
  public String toString() {
    return "Movie{" +
        "id=" + id +
        ", popularity=" + popularity +
        ", voteCount=" + voteCount +
        ", posterPath='" + posterPath + '\'' +
        ", backdropPath='" + backdropPath + '\'' +
        ", title='" + title + '\'' +
        ", voteAverage=" + voteAverage +
        ", overview='" + overview + '\'' +
        ", releaseDate='" + releaseDate + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Movie)) {
      return false;
    }
    Movie movie = (Movie) obj;
    return getId() == movie.getId() &&
        Double.compare(movie.getPopularity(), getPopularity()) == 0 &&
        getVoteCount() == movie.getVoteCount() &&
        Float.compare(movie.getVoteAverage(), getVoteAverage()) == 0 &&
        getPosterPath().equals(movie.getPosterPath()) &&
        getBackdropPath().equals(movie.getBackdropPath()) &&
        getTitle().equals(movie.getTitle()) &&
        getOverview().equals(movie.getOverview()) &&
        getReleaseDate().equals(movie.getReleaseDate());
  }

}
