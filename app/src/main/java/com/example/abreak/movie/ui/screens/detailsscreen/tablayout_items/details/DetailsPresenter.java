package com.example.abreak.movie.ui.screens.detailsscreen.tablayout_items.details;

import android.content.Context;
import android.widget.Toast;
import butterknife.BindString;
import com.example.abreak.R;
import com.example.abreak.movie.model.database.Favorite;
import com.example.abreak.movie.model.database.FavoriteDatabase;
import com.example.abreak.movie.model.models.Details;
import com.example.abreak.movie.model.models.ExternalIds;
import com.example.abreak.movie.model.network.Services;
import com.pnikosis.materialishprogress.ProgressWheel;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.Map;


import static com.example.abreak.movie.utility.Constants.API_KEY;
import static com.example.abreak.movie.utility.Constants.API_KEY_KEY;

@SuppressWarnings("unchecked") public class DetailsPresenter implements Services.TransformResponse2Java {
  private Context context;
  private DetailsSetup setup;
  private CompositeDisposable disposables;
  private Services services;
  private ProgressWheel wheel;

  @BindString(R.string.added_success) String success;

  DetailsPresenter(Context context, CompositeDisposable disposables, DetailsSetup setup,
      int id, ProgressWheel wheel) {
    this.context = context;
    this.disposables = disposables;
    this.setup = setup;
    this.wheel = wheel;
    services = new Services(context, disposables, this);
    loadMovieDetails(id);
    services.loadExternalId(id, map());
  }

  private void loadMovieDetails(int id) {
    services.loadMovieDetails(id, map(), wheel);
  }

  private Map map() {
    Map map = new HashMap();
    map.put(API_KEY_KEY, API_KEY);
    return map;
  }

  @Override public void transfrom(Object model) {
    if (model instanceof Details) {
      Details details = (Details) model;
      setup.getFullMovieDetails(details);
    } else if (model instanceof ExternalIds) {
      ExternalIds id = (ExternalIds) model;
      setup.getExterinalIds(id);
    }
  }

  @SuppressWarnings("unchecked") private Single addToFavoriteObservable(int id, String name, String path) {
    FavoriteDatabase db = FavoriteDatabase.getFavoriteInstance(context);

    return Single.create((SingleOnSubscribe) emitter -> {
      if (!emitter.isDisposed()) {
        Favorite favorite = new Favorite(id, name, path);
        db.getAccessToDatabase().insertMovieToFavorite(favorite);
        emitter.onSuccess(favorite);
      }
    });
  }

  private SingleObserver addToFavoriteObserver() {
    return new SingleObserver() {
      @Override public void onSubscribe(Disposable d) {
        disposables.add(d);
      }

      @Override public void onSuccess(Object o) {
        Toast.makeText(context, success, Toast.LENGTH_LONG).show();
      }

      @Override public void onError(Throwable e) {

      }
    };
  }

  @SuppressWarnings("unchecked") public void insertTheMovie(int id, String name, String path) {
    addToFavoriteObservable(id, name, path)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(addToFavoriteObserver());
  }
}
