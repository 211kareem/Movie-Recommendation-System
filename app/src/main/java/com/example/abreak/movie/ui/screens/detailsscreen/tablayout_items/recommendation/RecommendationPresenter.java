package com.example.abreak.movie.ui.screens.detailsscreen.tablayout_items.recommendation;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.example.abreak.movie.model.models.RecommendMovies;
import com.example.abreak.movie.model.network.Services;
import io.reactivex.disposables.CompositeDisposable;
import java.util.HashMap;
import java.util.Map;

import static com.example.abreak.movie.utility.Constants.API_KEY;
import static com.example.abreak.movie.utility.Constants.API_KEY_KEY;
import static com.example.abreak.movie.utility.Constants.PAGE;

@SuppressWarnings("unchecked") public class RecommendationPresenter
    implements Services.TransformResponse2Java {
  private Services services;
  private RecommendSetup setup;
  private int id;

  RecommendationPresenter(Context context, CompositeDisposable disposables, int id,
      RecommendSetup setup) {
    this.id = id;
    this.setup = setup;
    services = new Services(context, disposables, this);
    services.loadRecommendedMovies(id, getMap(1));
  }

  @SuppressWarnings("unchecked") private Map getMap(int number) {
    Map map = new HashMap();
    map.put(API_KEY_KEY, API_KEY);
    map.put(PAGE, number);
    return map;
  }

  @SuppressWarnings("unchecked")
  public void setSimilarData(LinearLayout container, ImageView next, ImageView prev,
      int current,
      int total) {
    if (current == total) {
      container.setVisibility(View.GONE);
    } else if (current == 1) {
      prev.setVisibility(View.INVISIBLE);
    } else {
      container.setVisibility(View.VISIBLE);
      prev.setVisibility(View.VISIBLE);
    }
    next.setOnClickListener(v -> services.loadRecommendedMovies(id, getMap(current + 1)));
    prev.setOnClickListener(v -> services.loadRecommendedMovies(id, getMap(current - 1)));
  }

  @Override public void transfrom(Object model) {
    RecommendMovies movies = (RecommendMovies) model;
    setup.setRecommendedMoviesData(movies);
  }
}
