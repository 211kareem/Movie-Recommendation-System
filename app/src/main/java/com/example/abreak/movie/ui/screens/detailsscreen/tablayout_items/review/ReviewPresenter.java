package com.example.abreak.movie.ui.screens.detailsscreen.tablayout_items.review;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.abreak.movie.model.models.MovieReview;
import com.example.abreak.movie.model.network.Services;
import io.reactivex.disposables.CompositeDisposable;
import java.util.HashMap;
import java.util.Map;

import static com.example.abreak.movie.utility.Constants.API_KEY;
import static com.example.abreak.movie.utility.Constants.API_KEY_KEY;
import static com.example.abreak.movie.utility.Constants.PAGE;

@SuppressWarnings("unchecked") public class ReviewPresenter implements Services.TransformResponse2Java {
  private ReviewSetup setup;
  private Services services;
  private int id;

  ReviewPresenter(Context context, CompositeDisposable disposables, ReviewSetup setup,
      int id) {
    this.setup = setup;
    this.id = id;
    services = new Services(context, disposables, this);
    services.loadMovieReview(id, getMap(1));
  }

  private Map getMap(int number) {
    Map map = new HashMap();
    map.put(API_KEY_KEY, API_KEY);
    map.put(PAGE, number);
    return map;
  }

  private void loadMovieReview(int number) {
    services.loadMovieReview(id, getMap(number));
  }

  public void checkCurrentPageVsPreviousPage(int current, int total, int reviewsCount,
                                             ImageView next,
                                             ImageView prev, TextView count, TextView reviews, RecyclerView list, TextView warning) {
    if (current < total) {
      next.setOnClickListener(v -> loadMovieReview(current + 1));
    } else if (reviewsCount == 0) {
      next.setVisibility(View.GONE);
      prev.setVisibility(View.GONE);
      reviews.setVisibility(View.GONE);
      count.setVisibility(View.GONE);
      list.setVisibility(View.GONE);
      warning.setVisibility(View.VISIBLE);
    } else if (current == total) {
      next.setVisibility(View.INVISIBLE);
      prev.setVisibility(View.INVISIBLE);
    } else {
      next.setVisibility(View.VISIBLE);
      prev.setVisibility(View.VISIBLE);
      next.setOnClickListener(v -> loadMovieReview(current + 1));
      prev.setOnClickListener(v -> loadMovieReview(current - 1));
    }
  }

  @Override public void transfrom(Object model) {
    if (model instanceof MovieReview) {
      MovieReview result = (MovieReview) model;
      setup.setReviewData(result);
    }
  }
}
