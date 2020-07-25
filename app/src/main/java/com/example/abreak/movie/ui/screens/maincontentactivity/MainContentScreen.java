package com.example.abreak.movie.ui.screens.maincontentactivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Parcelable;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.abreak.R;
import com.example.abreak.movie.model.database.Favorite;
import com.example.abreak.movie.model.models.Hollywood;
import com.example.abreak.movie.model.models.PlayingMovies;
import com.example.abreak.movie.model.models.PouplarMovies;
import com.example.abreak.movie.model.models.RatedMovies;
import com.example.abreak.movie.model.models.UpComingMovies;
import com.example.abreak.movie.ui.base.BaseActivity;
import com.example.abreak.movie.ui.screens.detailsscreen.MovieDetailsScreen;
import com.example.abreak.movie.ui.screens.maincontentactivity.adapter.FavoriteAdapter;
import com.example.abreak.movie.ui.screens.maincontentactivity.adapter.FavoriteClick;
import com.example.abreak.movie.ui.screens.maincontentactivity.adapter.HollywoodAdapter;
import com.example.abreak.movie.ui.screens.maincontentactivity.adapter.OnSingleMovieClick;
import com.example.abreak.movie.ui.screens.maincontentactivity.adapter.OnStarClick;
import com.example.abreak.movie.ui.screens.maincontentactivity.adapter.PlayingAdapter;
import com.example.abreak.movie.ui.screens.maincontentactivity.adapter.PopularAdapter;
import com.example.abreak.movie.ui.screens.maincontentactivity.adapter.RatedAdapter;
import com.example.abreak.movie.ui.screens.maincontentactivity.adapter.UpcomingAdapter;
import com.example.abreak.movie.ui.screens.nightview.ShowlistInDarkMode;
import com.example.abreak.movie.ui.screens.maincontentactivity.presenter.MainContentPresenter;
import com.example.abreak.movie.utility.ActivityUtil;
import com.example.abreak.movie.utility.Constants;
import com.example.abreak.movie.utility.Other;
import com.pnikosis.materialishprogress.ProgressWheel;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ConstantConditions") public class MainContentScreen extends BaseActivity
    implements ViewSetup, OnSingleMovieClick, OnStarClick, FavoriteClick {

  @BindView(R.id.upcoming_section) View upComing;
  @BindView(R.id.playing_section) View playing;
  @BindView(R.id.popular_section) View popular;
  @BindView(R.id.rated_section) View rated;
  @BindView(R.id.hollywood_section) View hollywood;
  @BindView(R.id.favorite_section) View favorite;
  @BindView(R.id.wheel) ProgressWheel wheel;
  @BindView(R.id.main_data_container) ScrollView container;
  @BindView(R.id.no_connection_txt) TextView noConnectionTxt;
  @BindView(R.id.retry_btn) Button retryBtn;

  @BindString(R.string.upcoming) String upcomingTxt;
  @BindString(R.string.now_playing) String playingTxt;
  @BindString(R.string.popular) String popularTxt;
  @BindString(R.string.latest) String latestTxt;
  @BindString(R.string.top_rated) String ratedTxt;
  @BindString(R.string.hollywood) String hollywoodTxt;
  @BindString(R.string.favorite) String favorites;
  @BindString(R.string.noconnection) String noConnection;
  @BindString(R.string.favorite_count) String favoriteCount;
  @BindString(R.string.delete_msg) String deleteMsg;
  @BindString(R.string.delete_move) String deleteTitle;
  @BindString(R.string.yes) String yes;
  @BindString(R.string.no) String no;

  IncludedLayouts upcomingLayout, playingLayout, popularLayout, latestLayout, ratedLayout,
      hollywoodLayout, favoriteLayout;

  MainContentPresenter presenter;

  //instanceState Data
  int upcomingCurrent, upcomingTotal, upcomingCount, playingCurrent, playingTotal, playingCount,
      popularCurrent, popularTotal,
      popularCount, ratedCurrent, ratedTotal, ratedCount, hollywoodCurrent, hollywoodTotal,
      hollywoodCount;
  List<UpComingMovies.Results> upcomingMovies;
  List<PlayingMovies.Results> playingMovies;
  List<PouplarMovies.Results> popularMovies;
  List<RatedMovies.Results> ratedMovies;
  List<Hollywood.Result> hollywoodStarsMovies;
  List<Favorite> favoriteMovies;

  Bundle b;

  @Override public int setLayout() {
    return R.layout.main_content_activity;
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    b = outState;
    saveDatatoBundle(outState);
  }

  private void saveDatatoBundle(Bundle outState) {
    b = outState;
    b.putParcelableArrayList(Constants.UPCOMING_LIST,
        (ArrayList<? extends Parcelable>) upcomingMovies);
    b.putParcelableArrayList(Constants.MOVIES_LIST,
        (ArrayList<? extends Parcelable>) playingMovies);
    b.putParcelableArrayList(Constants.POPULAR_LIST,
        (ArrayList<? extends Parcelable>) popularMovies);
    b.putParcelableArrayList(Constants.RATED_LIST,
        (ArrayList<? extends Parcelable>) ratedMovies);
    b.putParcelableArrayList(Constants.FAVORITE_LIST,
        (ArrayList<? extends Parcelable>) favoriteMovies);
    b.putParcelableArrayList(Constants.HOLLYWOOD_LIST,
        (ArrayList<? extends Parcelable>) hollywoodStarsMovies);
    b.putInt(Constants.UPCOMING_PAGE, upcomingCurrent);
    b.putInt(Constants.UPCOMING_TOTAL, upcomingTotal);
    b.putInt(Constants.UPCOMING_TOTAL_MOVIES, upcomingCount);
    b.putInt(Constants.PLAYING_PAGE, playingCurrent);
    b.putInt(Constants.PLAYING_TOTAL, playingTotal);
    b.putInt(Constants.PLAYING_TOTAL_MOVIES, playingCount);
    b.putInt(Constants.POPULAR_PAGE, popularCurrent);
    b.putInt(Constants.POPULAR_TOTAL, popularTotal);
    b.putInt(Constants.POPULAR_TOTAL_MOVIES, popularCount);
    b.putInt(Constants.RATED_PAGE, ratedCurrent);
    b.putInt(Constants.RATED_TOTAAL, ratedTotal);
    b.putInt(Constants.RATED_TOTAL_MOVIES, ratedCount);
    b.putInt(Constants.HOLLYWOOD_CURRENT, hollywoodCurrent);
    b.putInt(Constants.HOLLYWOOD_TOTAL, hollywoodTotal);
    b.putInt(Constants.HOLLYWOOD_TOTAL_MOVIES, hollywoodCount);
  }

  @Override protected void onResume() {
    super.onResume();
    setupIncludedLayouts();
    if (b != null) {
      // WHEN SCREEN ROTATE APP GET DATA FROM BUNDLE !
      handleConfigurationChangesData();
    } else {
      if (presenter.checkConnection(rated, upComing, popular, hollywood, playing, retryBtn,
          noConnectionTxt)) {
        presenter.loadPopular();
        presenter.loadPlaying();
        presenter.loadRated();
        presenter.loadUpcoming();
        presenter.loadHollywoodMovies();
        presenter.getFavoriteList();
      } else {
        presenter.retryConnection(retryBtn, rated, upComing, popular, hollywood, playing,
            noConnectionTxt, noConnection);
        presenter.getFavoriteList();
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
      }
    }
  }

  @Override protected void onStop() {
    super.onStop();

    if (b != null) {
      // WHEN SCREEN ROTATE APP GET DATA FROM BUNDLE !
      handleConfigurationChangesData();
    }
  }

  private void handleConfigurationChangesData() {

    upcomingMovies = b.getParcelableArrayList(Constants.UPCOMING_LIST);
    upcomingCurrent = b.getInt(Constants.UPCOMING_PAGE);
    upcomingTotal = b.getInt(Constants.UPCOMING_TOTAL);
    upcomingCount = b.getInt(Constants.UPCOMING_TOTAL_MOVIES);
    upcomingLayout.counter.setText(String.valueOf(upcomingCount));
    upcomingLayout.sectionList.setAdapter(new UpcomingAdapter(upcomingMovies, this));
    presenter.loadMoreUpcomingMovies(upcomingCurrent, upcomingTotal,
        upcomingLayout.pageIndicator, upcomingLayout.nextPage, upcomingLayout.prevPage);
    upcomingLayout.darkViewBtn.setOnClickListener(v -> {
      Intent intent = new Intent(getContext(), ShowlistInDarkMode.class);
      intent.putParcelableArrayListExtra(Constants.UPCOMING_LIST,
          (ArrayList<? extends Parcelable>) upcomingMovies);
      ActivityUtil.intentWithData(getContext(), intent);
    });

    playingMovies = b.getParcelableArrayList(Constants.MOVIES_LIST);
    playingCurrent = b.getInt(Constants.PLAYING_PAGE);
    playingTotal = b.getInt(Constants.PLAYING_TOTAL);
    playingCount = b.getInt(Constants.PLAYING_TOTAL_MOVIES);
    playingLayout.counter.setText(String.valueOf(playingCount));
    playingLayout.sectionList.setAdapter(new PlayingAdapter(playingMovies, this));
    presenter.loadMorePlaying(playingCurrent, playingTotal, playingLayout.pageIndicator,
        playingLayout.nextPage, playingLayout.prevPage);
    playingLayout.darkViewBtn.setOnClickListener(v -> {
      Intent intent = new Intent(getContext(), ShowlistInDarkMode.class);
      intent.putParcelableArrayListExtra(Constants.MOVIES_LIST,
          (ArrayList<? extends Parcelable>) playingMovies);
      ActivityUtil.intentWithData(getContext(), intent);
    });

    popularMovies = b.getParcelableArrayList(Constants.POPULAR_LIST);
    popularCurrent = b.getInt(Constants.POPULAR_PAGE);
    popularTotal = b.getInt(Constants.POPULAR_TOTAL);
    popularCount = b.getInt(Constants.POPULAR_TOTAL_MOVIES);
    popularLayout.counter.setText(String.valueOf(popularCount));
    popularLayout.sectionList.setAdapter(new PopularAdapter(popularMovies, this));
    presenter.loadPopularMovies(popularCurrent, popularTotal, popularLayout.pageIndicator,
        popularLayout.nextPage, popularLayout.prevPage);
    popularLayout.darkViewBtn.setOnClickListener(v -> {
      Intent intent = new Intent(getContext(), ShowlistInDarkMode.class);
      intent.putParcelableArrayListExtra(Constants.POPULAR_LIST,
          (ArrayList<? extends Parcelable>) popularMovies);
      ActivityUtil.intentWithData(getContext(), intent);
    });

    ratedMovies = b.getParcelableArrayList(Constants.RATED_LIST);
    ratedCurrent = b.getInt(Constants.RATED_PAGE);
    ratedTotal = b.getInt(Constants.RATED_TOTAAL);
    ratedCount = b.getInt(Constants.RATED_TOTAL_MOVIES);
    ratedLayout.counter.setText(String.valueOf(ratedCount));
    ratedLayout.sectionList.setAdapter(new RatedAdapter(ratedMovies, this));
    presenter.loadMoreRated(ratedCurrent, ratedTotal, ratedLayout
        .pageIndicator, ratedLayout.nextPage, ratedLayout.prevPage);
    ratedLayout.darkViewBtn.setOnClickListener(v -> {
      Intent intent = new Intent(getContext(), ShowlistInDarkMode.class);
      intent.putParcelableArrayListExtra(Constants.RATED_LIST,
          (ArrayList<? extends Parcelable>) ratedMovies);
      ActivityUtil.intentWithData(getContext(), intent);
    });

    hollywoodStarsMovies = b.getParcelableArrayList(Constants.HOLLYWOOD_LIST);
    hollywoodCurrent = b.getInt(Constants.HOLLYWOOD_CURRENT);
    hollywoodTotal = b.getInt(Constants.HOLLYWOOD_TOTAL);
    hollywoodCount = b.getInt(Constants.HOLLYWOOD_TOTAL_MOVIES);
    hollywoodLayout.counter.setText(String.valueOf(hollywoodCount));
    hollywoodLayout.sectionList.setAdapter(new HollywoodAdapter(hollywoodStarsMovies, this));
    presenter.loadMoreHollywood(hollywoodCurrent, hollywoodTotal, hollywoodLayout
        .pageIndicator, hollywoodLayout.nextPage, hollywoodLayout.prevPage);

    favoriteMovies = b.getParcelableArrayList(Constants.FAVORITE_LIST);
    favoriteLayout.sectionList.setAdapter(new FavoriteAdapter(favoriteMovies, this));
    presenter.getFavoriteList();
  }

  @Override public void init(Bundle savedInstanceState) {
    b = savedInstanceState;
    presenter = new MainContentPresenter(getContext(), disposables, this, wheel);
  }

  @OnClick({ R.id.retry_btn })
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.retry_btn:
        presenter.retryConnection(retryBtn, rated, upComing, popular, hollywood, playing,
            noConnectionTxt, noConnection);
        break;
    }
  }

  @Override public void setupToolbar() {

  }

  private void setupIncludedLayouts() {
    upcomingLayout = new IncludedLayouts();
    playingLayout = new IncludedLayouts();
    popularLayout = new IncludedLayouts();
    latestLayout = new IncludedLayouts();
    ratedLayout = new IncludedLayouts();
    hollywoodLayout = new IncludedLayouts();
    favoriteLayout = new IncludedLayouts();

    ButterKnife.bind(upcomingLayout, upComing);
    ButterKnife.bind(playingLayout, playing);
    ButterKnife.bind(popularLayout, popular);
    ButterKnife.bind(ratedLayout, rated);
    ButterKnife.bind(hollywoodLayout, hollywood);
    ButterKnife.bind(favoriteLayout, favorite);

    //Setup Texts
    upcomingLayout.sectionName.setText(upcomingTxt);
    playingLayout.sectionName.setText(playingTxt);
    popularLayout.sectionName.setText(popularTxt);
    ratedLayout.sectionName.setText(ratedTxt);
    hollywoodLayout.sectionName.setText(hollywoodTxt);
    favoriteLayout.sectionName.setText(favorites);

    //Setup Lists
    setupRecycler(upcomingLayout.sectionList);
    setupRecycler(playingLayout.sectionList);
    setupRecycler(popularLayout.sectionList);
    setupRecycler(ratedLayout.sectionList);
    setupRecycler(hollywoodLayout.sectionList);
    setupRecycler(favoriteLayout.sectionList);
  }

  private void setupRecycler(RecyclerView view) {
    view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    view.setHasFixedSize(true);
  }

  @Override
  public void upcomingMovies(UpComingMovies movies) {
    upcomingMovies = movies.getUpcomingList();
    upcomingCurrent = movies.getPage();
    upcomingTotal = movies.getTotalPage();
    upcomingCount = movies.getMovieCount();
    upcomingLayout.counter.setText(String.valueOf(upcomingCount));
    upcomingLayout.sectionList.setAdapter(
        new UpcomingAdapter(upcomingMovies, this));
    presenter.loadMoreUpcomingMovies(upcomingCurrent, upcomingTotal,
        upcomingLayout.pageIndicator, upcomingLayout.nextPage, upcomingLayout.prevPage);
    upcomingLayout.sectionList.setAdapter(new UpcomingAdapter(upcomingMovies, this));
    upcomingLayout.darkViewBtn.setOnClickListener(v -> {
      Intent intent = new Intent(getContext(), ShowlistInDarkMode.class);
      intent.putParcelableArrayListExtra(Constants.UPCOMING_LIST,
          (ArrayList<? extends Parcelable>) movies.getUpcomingList());
      ActivityUtil.intentWithData(getContext(), intent);
    });
  }

  @Override
  public void popularMovies(PouplarMovies results) {
    popularMovies = results.getResults();
    popularCurrent = results.getPage();
    popularTotal = results.getTotalPage();
    popularCount = results.getMovieCount();
    popularLayout.counter.setText(String.valueOf(popularCount));
    popularLayout.sectionList.setAdapter(new PopularAdapter(popularMovies, this));
    presenter.loadPopularMovies(popularCurrent, popularTotal, popularLayout.pageIndicator,
        popularLayout.nextPage, popularLayout.prevPage);
    popularLayout.darkViewBtn.setOnClickListener(v -> {
      Intent intent = new Intent(getContext(), ShowlistInDarkMode.class);
      intent.putParcelableArrayListExtra(Constants.POPULAR_LIST,
          (ArrayList<? extends Parcelable>) results.getResults());
      ActivityUtil.intentWithData(getContext(), intent);
    });
  }

  @Override
  public void playingMovies(PlayingMovies movies) {
    playingMovies = movies.getPlayingList();
    playingCurrent = movies.getPage();
    playingTotal = movies.getTotalPage();
    playingCount = movies.getMovieCount();
    playingLayout.counter.setText(String.valueOf(playingCount));
    playingLayout.sectionList.setAdapter(new PlayingAdapter(playingMovies, this));
    presenter.loadMorePlaying(playingCurrent, playingTotal, playingLayout.pageIndicator,
        playingLayout.nextPage, playingLayout.prevPage);
    playingLayout.darkViewBtn.setOnClickListener(v -> {
      Intent intent = new Intent(getContext(), ShowlistInDarkMode.class);
      intent.putParcelableArrayListExtra(Constants.MOVIES_LIST,
          (ArrayList<? extends Parcelable>) movies.getPlayingList());
      ActivityUtil.intentWithData(getContext(), intent);
    });
  }

  @Override
  public void ratedMovies(RatedMovies results) {
    ratedMovies = results.getRatedList();
    ratedCurrent = results.getPage();
    ratedTotal = results.getTotalPage();
    ratedCount = results.getMovieCount();
    ratedLayout.counter.setText(String.valueOf(ratedCount));
    ratedLayout.sectionList.setAdapter(new RatedAdapter(ratedMovies, this));
    presenter.loadMoreRated(ratedCurrent, ratedTotal, ratedLayout
        .pageIndicator, ratedLayout.nextPage, ratedLayout.prevPage);
    ratedLayout.darkViewBtn.setOnClickListener(v -> {
      Intent intent = new Intent(getContext(), ShowlistInDarkMode.class);
      intent.putParcelableArrayListExtra(Constants.RATED_LIST,
          (ArrayList<? extends Parcelable>) ratedMovies);
      ActivityUtil.intentWithData(getContext(), intent);
    });
  }

  @Override public void hollywoodStars(Hollywood stars) {
    hollywoodCurrent = stars.getCurrentPage();
    hollywoodTotal = stars.getTotalPage();
    hollywoodStarsMovies = stars.getMovies();
    hollywoodCount = stars.getTotalResult();
    hollywoodLayout.counter.setText(String.valueOf(hollywoodCount));
    hollywoodLayout.darkViewBtn.setVisibility(View.INVISIBLE);
    hollywoodLayout.sectionList.setAdapter(new HollywoodAdapter(hollywoodStarsMovies, this));
    presenter.loadMoreHollywood(hollywoodCurrent, hollywoodTotal, hollywoodLayout
        .pageIndicator, hollywoodLayout.nextPage, hollywoodLayout.prevPage);
  }

  @Override public void viewFavorites(List<Favorite> favorites) {
    favoriteMovies = favorites;
    favoriteLayout.prevPage.setVisibility(View.INVISIBLE);
    favoriteLayout.nextPage.setVisibility(View.INVISIBLE);
    favoriteLayout.darkViewBtn.setVisibility(View.INVISIBLE);
    favoriteLayout.counter.setText(String.valueOf(favorites.size()));
    if (favorites.size() > 0) {
      favoriteLayout.sectionList.setAdapter(new FavoriteAdapter(favorites, this));
      favoriteLayout.noData.setVisibility(View.GONE);
    } else {
      favoriteLayout.sectionList.setVisibility(View.INVISIBLE);
      favoriteLayout.noData.setVisibility(View.VISIBLE);
    }
  }

  @Override public void getMovieId(int id, String path) {
    if (Other.isOnline(getContext())) {
      Intent in = new Intent(getContext(), MovieDetailsScreen.class);
      in.putExtra(Constants.MOVIE_ID, id);
      in.putExtra(Constants.BACKDROP_PATH, path);
      ActivityUtil.intentWithData(getContext(), in);
    } else {
      Toast.makeText(getContext(), noConnection, Toast.LENGTH_LONG).show();
    }
  }

  @Override public void starId(int id) {
    presenter.loadStarMovies(id);
  }

  @Override public void favoriteLongClick(int id, String name) {
    //longClick
    new AlertDialog.Builder(getContext()).setTitle(deleteTitle)
        .setMessage(deleteMsg + "\n" + name).setPositiveButton(yes,
        (dialog, which) -> presenter.removeItem(id)
    ).setNegativeButton(no, (dialog, which) -> dialog.dismiss()).show();
  }

  @Override public void favoriteNormalClcik(int id, String path) {
    if (Other.isOnline(getContext())) {
      //normalClick
      Intent in = new Intent(getContext(), MovieDetailsScreen.class);
      in.putExtra(Constants.MOVIE_ID, id);
      in.putExtra(Constants.BACKDROP_PATH, path);
      ActivityUtil.intentWithData(getContext(), in);
    }else {
      Toast.makeText(getContext(),noConnection,Toast.LENGTH_LONG).show();
    }
  }

  class IncludedLayouts {
    @BindView(R.id.mov_section_name) TextView sectionName;
    @BindView(R.id.movie_list) RecyclerView sectionList;
    @BindView(R.id.mov_next) ImageView nextPage;
    @BindView(R.id.mov_prev) ImageView prevPage;
    @BindView(R.id.current_total_tv) TextView pageIndicator;
    @BindView(R.id.mov_menu_img) ImageView darkViewBtn;
    @BindView(R.id.section_no_data) TextView noData;
    @BindView(R.id.count_tv) TextView counter;
  }
}
