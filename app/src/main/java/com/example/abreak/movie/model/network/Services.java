package com.example.abreak.movie.model.network;

import android.content.Context;
import android.view.View;
import com.pnikosis.materialishprogress.ProgressWheel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.Map;
import retrofit2.Response;

public class Services {
  Context context;
  CompositeDisposable disposables;
  Validator validator;
  ApplicationApi api = RetrofitBase.getApi();

  public Services(Context context, CompositeDisposable disposables,
      TransformResponse2Java transformResponse2Java) {
    this.context = context;
    this.disposables = disposables;
    validator = new Validator(context, transformResponse2Java);
  }

  public void loadTopRatedMovies(Map<String, String> params, ProgressWheel wheel) {
    Disposable disposable = api.getTopratedMovies(params)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe(dis -> wheel.setVisibility(View.VISIBLE))
        .doFinally(() -> wheel.setVisibility(View.GONE))
        .subscribe(this::checkResponse);
    disposables.add(disposable);
  }

  public void loadPopularMoviews(Map<String, String> params, ProgressWheel wheel) {
    Disposable disposable = api.getPopularMovies(params)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe(dis -> wheel.setVisibility(View.VISIBLE))
        .doFinally(() -> wheel.setVisibility(View.GONE))
        .subscribe(this::checkResponse);
    disposables.add(disposable);
  }

  public void loadNowPlaying(Map<String, String> params, ProgressWheel wheel) {
    Disposable disposable = api.getNowPlayingMovies(params)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe(dis -> wheel.setVisibility(View.VISIBLE))
        .doFinally(() -> wheel.setVisibility(View.GONE))
        .subscribe(this::checkResponse);
    disposables.add(disposable);
  }

  public void loadUpcoming(Map<String, String> params, ProgressWheel wheel) {
    Disposable disposable = api.getUpcomingMovies(params)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe(dis -> wheel.setVisibility(View.VISIBLE))
        .doFinally(() -> wheel.setVisibility(View.GONE))
        .subscribe(this::checkResponse);
    disposables.add(disposable);
  }

  public void loadHollywood(Map<String, String> params) {
    Disposable disposable = api.hollywoodStars(params)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(this::checkResponse);
    disposables.add(disposable);
  }

  public void loadMovieDetails(int id, Map<String, String> params, ProgressWheel wheel) {
    Disposable disposable = api.getMovieDetails(id, params)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe(dis -> wheel.setVisibility(View.VISIBLE))
        .doFinally(() -> wheel.setVisibility(View.GONE))
        .subscribe(this::checkResponse);
    disposables.add(disposable);
  }

  public void loadMovieCast(int id, Map<String, String> params) {
    Disposable disposable = api.getMovieCast(id, params)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(this::checkResponse);
    disposables.add(disposable);
  }

  public void loadMovieReview(int id, Map<String, String> params) {
    Disposable disposable = api.getMovieReview(id, params)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(this::checkResponse);
    disposables.add(disposable);
  }

  public void loadTrailers(int id, Map<String, String> params) {
    Disposable disposable = api.getTrailers(id, params)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(this::checkResponse);
    disposables.add(disposable);
  }

  public void loadSimilarMovies(int id, Map<String, String> params) {
    Disposable disposable = api.getSimilarMovies(id, params)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(this::checkResponse);
    disposables.add(disposable);
  }

  public void loadRecommendedMovies(int id, Map<String, String> params) {
    Disposable disposable = api.getRecommendedMovies(id, params)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(this::checkResponse);
    disposables.add(disposable);
  }

  public void loadStarMovies(int id, Map<String, String> params) {
    Disposable disposable = api.getStarMovie(id, params)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(this::checkResponse);
    disposables.add(disposable);
  }

  public void loadExternalId(int id, Map<String, String> params) {
    Disposable disposable = api.getExteranl(id, params)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(this::checkResponse);
    disposables.add(disposable);
  }

  public void checkResponse(Response<?> response) {
    validator.transform2Java(response);
  }

  public interface TransformResponse2Java {
    void transfrom(Object model);
  }
}
