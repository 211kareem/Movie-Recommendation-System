package com.example.abreak.movie.ui.screens.detailsscreen.tablayout_items.similars;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.example.abreak.movie.model.models.SimilarMovies;
import com.example.abreak.movie.model.network.Services;
import io.reactivex.disposables.CompositeDisposable;
import java.util.HashMap;
import java.util.Map;

import static com.example.abreak.movie.utility.Constants.API_KEY;
import static com.example.abreak.movie.utility.Constants.API_KEY_KEY;
import static com.example.abreak.movie.utility.Constants.PAGE;

@SuppressWarnings("unchecked") public class SimilarPresenter implements Services.TransformResponse2Java {
  private SimilarSetup setup;
 private int id;
  private Services service;

  SimilarPresenter(Context context, CompositeDisposable disposables, SimilarSetup setup,
      int id) {
    this.setup = setup;
    this.id = id;
    service = new Services(context, disposables, this);
    service.loadSimilarMovies(id, getMap(1));
  }

  private Map getMap(int number) {
    Map map = new HashMap();
    map.put(API_KEY_KEY, API_KEY);
    map.put(PAGE, number);
    return map;
  }

  public void setSimilarData(LinearLayout container,ImageView next, ImageView prev, int current,
      int total) {
    if (current == total) {
      container.setVisibility(View.GONE);
    } else if (current == 1) {
      prev.setVisibility(View.INVISIBLE);
    } else {
      container.setVisibility(View.VISIBLE);
      prev.setVisibility(View.VISIBLE);
    }
    next.setOnClickListener(v -> service.loadSimilarMovies(id, getMap(current + 1)));
    prev.setOnClickListener(v -> service.loadSimilarMovies(id, getMap(current - 1)));

  }

  @Override public void transfrom(Object model) {
    SimilarMovies movies = (SimilarMovies) model;
    setup.setSimilarData(movies);
  }
}
