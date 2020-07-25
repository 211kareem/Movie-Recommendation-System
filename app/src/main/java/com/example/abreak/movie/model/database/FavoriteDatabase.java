package com.example.abreak.movie.model.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Favorite.class, version = 1, exportSchema = false)

public abstract class FavoriteDatabase extends RoomDatabase {
  private static FavoriteDatabase favoriteInstance;

  public static FavoriteDatabase getFavoriteInstance(Context context) {
    if (favoriteInstance == null) {
      favoriteInstance =
          Room.databaseBuilder(context, FavoriteDatabase.class, "favorite.db").build();
    }
    return favoriteInstance;
  }
  public abstract FavoriteDao getAccessToDatabase();
}
