package com.example.abreak.movie.model.database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;


@Dao
public interface FavoriteDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE) void insertMovieToFavorite(Favorite fav);

  @Query("SELECT * FROM Favorite") List<Favorite> getFavoriteMovies();

  @Query("DELETE FROM Favorite WHERE movie_id=:id") void deleteSelectedItem(int id);
}
