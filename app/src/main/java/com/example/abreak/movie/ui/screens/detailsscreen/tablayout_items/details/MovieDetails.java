package com.example.abreak.movie.ui.screens.detailsscreen.tablayout_items.details;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindString;
import butterknife.BindView;

import com.bumptech.glide.Glide;
import com.example.abreak.R;
import com.example.abreak.movie.model.bus.IdBus;
import com.example.abreak.movie.model.models.Details;
import com.example.abreak.movie.model.models.ExternalIds;
import com.example.abreak.movie.ui.base.BaseFragment;
import com.example.abreak.movie.ui.screens.detailsscreen.tablayout_items.details.adapters.ProductionAdapter;
import com.example.abreak.movie.ui.screens.detailsscreen.tablayout_items.details.adapters.TagAdapter;
import com.example.abreak.movie.utility.ActivityUtil;
import com.example.abreak.movie.utility.Constants;
import com.example.abreak.movie.utility.Other;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.squareup.picasso.Picasso;
import java.util.Objects;

import static com.example.abreak.movie.utility.Constants.BASE_IMG_URL;
import static com.example.abreak.movie.utility.Constants.IMDB_BASE;

public class MovieDetails extends BaseFragment implements DetailsSetup {

  @BindView(R.id.movie_background) ImageView background;
  @BindView(R.id.movie_poster) ImageView poster;
  @BindView(R.id.movie_title) TextView title;
  @BindView(R.id.movie_rating) TextView rating;
  @BindView(R.id.movie_run_time) TextView runTime;
  @BindView(R.id.movie_date) TextView date;
  @BindView(R.id.movie_budget) TextView budget;
  @BindView(R.id.movie_revenue) TextView revenue;
  @BindView(R.id.movie_tag_list)
  RecyclerView list;
  @BindView(R.id.movie_overview) TextView overview;
  @BindView(R.id.facebook_icon) ImageView facebook;
  @BindView(R.id.instagram_icon) ImageView instagram;
  @BindView(R.id.imdb_icon) ImageView imdb;
  @BindView(R.id.production_comp_list) RecyclerView collectionList;
  @BindView(R.id.fav) ImageView favoirteBtn;
  @BindView(R.id.detail_wheel) ProgressWheel wheel;

  @BindString(R.string.budget) String budgetWord;
  @BindString(R.string.revenue) String revenueWord;

  DetailsPresenter presenter;

  public static MovieDetails newInstance() {
    return new MovieDetails();
  }

  @Override public int fragmentLayout() {
    return R.layout.details;
  }

  private void setupRecycler(RecyclerView recyclerView) {
    recyclerView.setLayoutManager(
        new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    recyclerView.setHasFixedSize(true);
  }

  @Override public void init() {
    int id = IdBus.getId();
    setupRecycler(list);
    setupRecycler(collectionList);
    presenter = new DetailsPresenter(getContext(), disposables, this, id, wheel);
  }

  @SuppressLint({ "SetTextI18n", "DefaultLocale" }) @Override
  public void getFullMovieDetails(Details details) {
    if (details != null) {
      long budgets = details.getBudget();
      String posterPath = BASE_IMG_URL + details.getPosterPath();
      long revenues = details.getRevenue();
      Picasso.with(getContext()).load(BASE_IMG_URL + details.getBackPath()).into(background);
      Picasso.with(getContext()).load(posterPath).into(poster);
      title.setText(details.getTitle());
      rating.setText("  " + details.getVoteAverage() + " |10");
      runTime.setText("  " + details.getRuntime() + " MIN");
      date.setText("  " + details.getDate());
      budget.setText(
          "  " + budgetWord + "\n" + "  " + String.format("%.2fM", budgets / 1000000.0) + " $");
      revenue.setText(
          "  " + revenueWord + "\n" + "  " + String.format("%.2fM", revenues / 1000000.0) + " $");
      overview.setText(details.getOverview());
      list.setAdapter(new TagAdapter(details.getGenres()));
      collectionList.setAdapter(new ProductionAdapter(details.getProductionCompanies()));
      favoirteBtn.setOnClickListener(
          v -> presenter.insertTheMovie(IdBus.getId(), details.getTitle(),
              details.getPosterPath()));
    }
  }

  @Override public void getExterinalIds(ExternalIds id) {
    if (id != null) {
      imdb.setOnClickListener(v -> dialogSetup(id.getImdbId()));
      facebook.setOnClickListener(v -> ActivityUtil.goToFacebook(getContext(), id.getFacebookId()));
      instagram.setOnClickListener(
          v -> {
            assert getContext() != null;
            startActivity(
                ActivityUtil.newInstagramProfileIntent(getContext(), id.getInstagramId()));
          });

    }
  }

  @SuppressLint("SetJavaScriptEnabled") private void dialogSetup(String imdb) {
    Dialog d = Other.transparentDialog(getContext(), R.layout.webview_dialog, R.style.wide_dialog);
    Objects.requireNonNull(d.getWindow())
        .setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    WebView view = d.findViewById(R.id.movie_site);
    view.setWebViewClient(new CustomClient());
    WebSettings settings = view.getSettings();
    settings.setJavaScriptEnabled(true);
    settings.setDisplayZoomControls(true);
    view.loadUrl(IMDB_BASE + imdb);
    d.show();
  }

  private class CustomClient extends WebViewClient {
    @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
      view.loadUrl(url);
      return true;
    }
  }
}
