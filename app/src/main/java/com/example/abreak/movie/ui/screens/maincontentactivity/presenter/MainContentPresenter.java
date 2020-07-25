package com.example.abreak.movie.ui.screens.maincontentactivity.presenter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.abreak.R;
import com.example.abreak.movie.model.bus.IdBus;
import com.example.abreak.movie.model.database.Favorite;
import com.example.abreak.movie.model.database.FavoriteDatabase;
import com.example.abreak.movie.model.models.Hollywood;
import com.example.abreak.movie.model.models.PlayingMovies;
import com.example.abreak.movie.model.models.PouplarMovies;
import com.example.abreak.movie.model.models.RatedMovies;
import com.example.abreak.movie.model.models.StarMovie;
import com.example.abreak.movie.model.models.UpComingMovies;
import com.example.abreak.movie.model.network.Services;
import com.example.abreak.movie.ui.screens.detailsscreen.tablayout_items.cast.adapter.StarAdapter;
import com.example.abreak.movie.ui.screens.maincontentactivity.ViewSetup;
import com.example.abreak.movie.utility.Constants;
import com.example.abreak.movie.utility.Other;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.squareup.picasso.Picasso;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.example.abreak.movie.utility.Constants.API_KEY;
import static com.example.abreak.movie.utility.Constants.API_KEY_KEY;
import static com.example.abreak.movie.utility.Constants.BASE_IMG_URL;
import static com.example.abreak.movie.utility.Constants.PAGE;

@SuppressWarnings({ "LambdaParameterTypeCanBeSpecified", "unchecked" })
public class MainContentPresenter implements Services.TransformResponse2Java {
  private Context context;
  private CompositeDisposable disposables;
  private ViewSetup adapter;
  private Services services;

  private ProgressWheel wheel;

  public MainContentPresenter(Context context, CompositeDisposable disposable,
      ViewSetup adapter, ProgressWheel wheel) {
    this.context = context;
    this.disposables = disposable;
    this.adapter = adapter;
    this.wheel = wheel;
    services = new Services(context, disposables, this);
  }

  private Map getMap(int number) {
    Map map = new HashMap();
    map.put(API_KEY_KEY, API_KEY);
    map.put(PAGE, number);
    return map;
  }

  private Map getMap() {
    Map map = new HashMap();
    map.put(API_KEY_KEY, API_KEY);
    return map;
  }

  private Single removeItemObservable(int id) {
    FavoriteDatabase db = FavoriteDatabase.getFavoriteInstance(context);
    return Single.create((SingleOnSubscribe) emitter -> {
      if (!emitter.isDisposed()) {
        db.getAccessToDatabase().deleteSelectedItem(id);
        List<Favorite> movies = db.getAccessToDatabase().getFavoriteMovies();
        emitter.onSuccess(movies);
      }
    });
  }

  private SingleObserver removeObserver() {
    return new SingleObserver() {
      @Override public void onSubscribe(Disposable d) {
        disposables.add(d);
      }

      @Override public void onSuccess(Object o) {
        List<Favorite> favorites = (List<Favorite>) o;
        adapter.viewFavorites(favorites);
      }

      @Override public void onError(Throwable e) {

      }
    };
  }

  public void removeItem(int id) {
    removeItemObservable(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(removeObserver());
  }

  private SingleObserver addToFavoriteObserver() {
    return new SingleObserver() {
      @Override public void onSubscribe(Disposable d) {
        disposables.add(d);
      }

      @Override public void onSuccess(Object o) {
        List<Favorite> favorites = (List<Favorite>) o;
        adapter.viewFavorites(favorites);
      }

      @Override public void onError(Throwable e) {

      }
    };
  }

  private Single addToFavoriteObservable() {
    FavoriteDatabase db = FavoriteDatabase.getFavoriteInstance(context);

    return Single.create((SingleOnSubscribe) emitter -> {
      if (!emitter.isDisposed()) {
        List<Favorite> favorite = db.getAccessToDatabase().getFavoriteMovies();
        emitter.onSuccess(favorite);
      }
    });
  }

  public void getFavoriteList() {
    addToFavoriteObservable()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(addToFavoriteObserver());
  }

  public void loadHollywoodMovies() {
    services.loadHollywood(getMap(1));
  }

  public void retryConnection(Button retry, View rated, View upcoming, View popular,
      View superstars,
      View playing, TextView noConnect,
      String tryTxt) {
    if (Other.isOnline(context)) {
      retry.setOnClickListener(v -> {
        if (Other.isOnline(context)) {
          rated.setVisibility(View.VISIBLE);
          upcoming.setVisibility(View.VISIBLE);
          popular.setVisibility(View.VISIBLE);
          superstars.setVisibility(View.VISIBLE);
          playing.setVisibility(View.VISIBLE);
          retry.setVisibility(View.GONE);
          noConnect.setVisibility(View.GONE);
          loadRated();
          loadPopular();
          loadUpcoming();
          loadHollywoodMovies();
          loadPlaying();
        } else {
          Toast.makeText(context, tryTxt, Toast.LENGTH_LONG).show();
        }
      });
    }
  }

  public boolean checkConnection(View rated, View upcoming, View popular, View superstars,
      View playing, Button retry, TextView noConnect) {
    if (Other.isOnline(context)) {
      rated.setVisibility(View.VISIBLE);
      upcoming.setVisibility(View.VISIBLE);
      popular.setVisibility(View.VISIBLE);
      superstars.setVisibility(View.VISIBLE);
      playing.setVisibility(View.VISIBLE);
      retry.setVisibility(View.GONE);
      noConnect.setVisibility(View.GONE);
      return true;
    } else {
      rated.setVisibility(View.GONE);
      upcoming.setVisibility(View.GONE);
      popular.setVisibility(View.GONE);
      superstars.setVisibility(View.GONE);
      playing.setVisibility(View.GONE);
      retry.setVisibility(View.VISIBLE);
      noConnect.setVisibility(View.VISIBLE);
      return false;
    }
  }

  public void loadUpcoming() {
    services.loadUpcoming(getMap(1), wheel);
  }

  public void loadPlaying() {
    services.loadNowPlaying(getMap(1), wheel);
  }

  public void loadPopular() {
    services.loadPopularMoviews(getMap(1), wheel);
  }

  public void loadRated() {
    services.loadTopRatedMovies(getMap(1), wheel);
  }

  @SuppressLint("SetTextI18n")
  public void loadMoreRated(int current, int total, TextView counter, ImageView next,
      ImageView prev) {

    if (current < total) {
      next.setOnClickListener(v -> services.loadTopRatedMovies(getMap(current + 1), wheel));
      prev.setOnClickListener(v -> services.loadTopRatedMovies(getMap(current - 1), wheel));
      next.setVisibility(View.VISIBLE);
      prev.setVisibility(View.VISIBLE);
    } else if (current == total) {
      next.setVisibility(View.INVISIBLE);
      prev.setVisibility(View.INVISIBLE);
    }
    if (current == 1) {
      prev.setVisibility(View.INVISIBLE);
    }
    counter.setText(current + " |" + total);
  }

  @SuppressLint("SetTextI18n")
  public void loadMoreHollywood(int current, int total, TextView counter, ImageView next,
      ImageView prev) {
    if (current < total) {
      next.setOnClickListener(v -> services.loadHollywood(getMap(current + 1)));
      prev.setOnClickListener(v -> services.loadHollywood(getMap(current - 1)));
      next.setVisibility(View.VISIBLE);
      prev.setVisibility(View.VISIBLE);
    } else if (current == total) {
      next.setVisibility(View.INVISIBLE);
      prev.setVisibility(View.INVISIBLE);
    }
    if (current == 1) {
      prev.setVisibility(View.INVISIBLE);
    }
    counter.setText(current + " |" + total);
  }

  @SuppressLint("SetTextI18n")
  public void loadMorePlaying(int current, int total, TextView counter, ImageView next,
      ImageView prev) {
    if (current < total) {
      next.setOnClickListener(v -> services.loadNowPlaying(getMap(current + 1), wheel));
      prev.setOnClickListener(v -> services.loadNowPlaying(getMap(current - 1), wheel));
      next.setVisibility(View.VISIBLE);
      prev.setVisibility(View.VISIBLE);
    } else if (current == total) {
      next.setVisibility(View.INVISIBLE);
      prev.setVisibility(View.INVISIBLE);
    }
    if (current == 1) {
      prev.setVisibility(View.INVISIBLE);
    }
    counter.setText(current + " |" + total);
  }

  @SuppressLint("SetTextI18n")
  public void loadPopularMovies(int current, int total, TextView counter, ImageView next,
      ImageView prev) {
    if (current < total) {
      next.setOnClickListener(v -> services.loadPopularMoviews(getMap(current + 1), wheel));
      prev.setOnClickListener(v -> services.loadPopularMoviews(getMap(current - 1), wheel));
      next.setVisibility(View.VISIBLE);
      prev.setVisibility(View.VISIBLE);
    } else if (current == total) {
      next.setVisibility(View.INVISIBLE);
      prev.setVisibility(View.INVISIBLE);
    }
    if (current == 1) {
      prev.setVisibility(View.INVISIBLE);
    }
    counter.setText(current + " |" + total);
  }

  @SuppressLint("SetTextI18n")
  public void loadMoreUpcomingMovies(int current, int total, TextView counter, ImageView next,
      ImageView prev) {
    if (current < total) {
      next.setOnClickListener(v -> services.loadUpcoming(getMap(current + 1), wheel));
      prev.setOnClickListener(v -> services.loadUpcoming(getMap(current - 1), wheel));
      next.setVisibility(View.VISIBLE);
      prev.setVisibility(View.VISIBLE);
    } else if (current == total) {
      next.setVisibility(View.INVISIBLE);
      prev.setVisibility(View.INVISIBLE);
    }
    if (current == 1) {
      prev.setVisibility(View.INVISIBLE);
    }
    counter.setText(current + " |" + total);
  }

  private void dialogSetup(StarMovie movie) {
    Dialog d = Other.transparentDialog(context, R.layout.similar, R.style.wide_dialog);
    Objects.requireNonNull(d.getWindow())
        .setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    LinearLayout container = d.findViewById(R.id.similar_container);
    ImageView titleContainer = d.findViewById(R.id.title_holder);
    TextView title = d.findViewById(R.id.dialog_title);
    ImageView closeDialog = d.findViewById(R.id.close_dialog);
    titleContainer.setVisibility(View.VISIBLE);
    title.setVisibility(View.VISIBLE);
    closeDialog.setVisibility(View.VISIBLE);

    ImageView backdrop = d.findViewById(R.id.similar_backdrop);
    RecyclerView list = d.findViewById(R.id.similar_list);
    title.setText(IdBus.getName());
    list.setLayoutManager(new GridLayoutManager(context, 2));
    list.setHasFixedSize(true);
    Picasso.with(context).load(BASE_IMG_URL + IdBus.getPath()).into(backdrop);
    container.setVisibility(View.GONE);
    list.setAdapter(new StarAdapter(movie.getStartMovie()));
    titleContainer.setOnClickListener(
        v -> Toast.makeText(context, IdBus.getName(), Toast.LENGTH_LONG).show());
    closeDialog.setOnClickListener(v -> d.dismiss());
    d.show();
  }

  public void loadStarMovies(int id) {
    services.loadStarMovies(id
        , getMap());
  }

  @Override public void transfrom(Object model) {
    if (model instanceof PouplarMovies) {
      PouplarMovies pouplarMovies = (PouplarMovies) model;
      adapter.popularMovies(pouplarMovies);
    } else if (model instanceof UpComingMovies) {
      UpComingMovies movies = (UpComingMovies) model;
      adapter.upcomingMovies(movies);
    } else if (model instanceof PlayingMovies) {
      PlayingMovies movies = (PlayingMovies) model;
      adapter.playingMovies(movies);
    } else if (model instanceof RatedMovies) {
      RatedMovies movies = (RatedMovies) model;
      adapter.ratedMovies(movies);
    } else if (model instanceof Hollywood) {
      Hollywood stars = (Hollywood) model;
      adapter.hollywoodStars(stars);
    } else if (model instanceof StarMovie) {
      StarMovie movie = (StarMovie) model;
      dialogSetup(movie);
    }
  }
}

