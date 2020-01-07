package com.nirmal.jeffrey.recyclerviewsample.database;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.nirmal.jeffrey.recyclerviewsample.model.Movie;
import java.util.List;

@Dao
public interface MovieDao {
  @Query("SELECT * FROM movie_table")
  DataSource.Factory<Integer, Movie> getMovieList();

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertMovie(Movie movie);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertMovies(List<Movie> movies);

  @Query("DELETE FROM movie_table WHERE id = :id")
  void deleteMovie(int id);

  @Query("SELECT * FROM movie_table WHERE id = :id")
  LiveData<Movie> getMovie(int id);
}
