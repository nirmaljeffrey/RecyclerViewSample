package com.nirmal.jeffrey.recyclerviewsample.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.nirmal.jeffrey.recyclerviewsample.model.Movie;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

private static MovieDatabase instance;
private static final String DATABASE_NAME = "movie_database";



  public static synchronized MovieDatabase getInstance(Context context) {
    if (instance == null) {
      instance = Room
          .databaseBuilder(context.getApplicationContext(), MovieDatabase.class, DATABASE_NAME)
          .build();
    }
    return instance;
  }
  public abstract MovieDao movieDao();
}
