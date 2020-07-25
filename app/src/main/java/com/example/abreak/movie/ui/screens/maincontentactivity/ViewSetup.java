package com.example.abreak.movie.ui.screens.maincontentactivity;

import com.example.abreak.movie.model.database.Favorite;
import com.example.abreak.movie.model.models.Hollywood;
import com.example.abreak.movie.model.models.PlayingMovies;
import com.example.abreak.movie.model.models.PouplarMovies;
import com.example.abreak.movie.model.models.RatedMovies;
import com.example.abreak.movie.model.models.UpComingMovies;
import java.util.List;

public interface ViewSetup {
  void upcomingMovies(UpComingMovies movies);

  void popularMovies(PouplarMovies movies);

  void playingMovies(PlayingMovies movies);

  void ratedMovies(RatedMovies movies);

  void hollywoodStars(Hollywood stars);

  void viewFavorites(List<Favorite> favorites);
}
