package com.example.abreak.movie.ui.screens.detailsscreen.tablayout_items.cast;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindColor;

import com.bumptech.glide.Glide;
import com.example.abreak.R;
import com.example.abreak.movie.model.bus.IdBus;
import com.example.abreak.movie.model.models.MovieCast;
import com.example.abreak.movie.model.models.StarMovie;
import com.example.abreak.movie.model.network.Services;
import com.example.abreak.movie.ui.screens.detailsscreen.tablayout_items.cast.adapter.StarAdapter;
import com.example.abreak.movie.utility.Constants;
import com.example.abreak.movie.utility.Other;
import com.squareup.picasso.Picasso;

import io.reactivex.disposables.CompositeDisposable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.example.abreak.movie.utility.Constants.API_KEY;
import static com.example.abreak.movie.utility.Constants.API_KEY_KEY;
import static com.example.abreak.movie.utility.Constants.BASE_IMG_URL;

@SuppressWarnings("unchecked") public class CastPresenter implements Services.TransformResponse2Java {
  private Context context;
  private Services services;
  private CastSetup setup;

  @BindColor(R.color.black) int blackColor;

  CastPresenter(Context context, CompositeDisposable disposables, CastSetup setup, int id) {
    this.context = context;
    this.setup = setup;
    services = new Services(context, disposables, this);
    services.loadMovieCast(id, map());
  }

  private Map map() {
    Map map = new HashMap();
    map.put(API_KEY_KEY, API_KEY);
    return map;
  }

  public void loadCastMovies(int id) {
    if (map() != null) {
      services.loadStarMovies(id, map());
    }
  }

  private void dialogSetup(StarMovie movie) {
    Dialog d = Other.transparentDialog(context, R.layout.similar, R.style.wide_dialog);
    Objects.requireNonNull(d.getWindow())
        .setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    LinearLayout container = d.findViewById(R.id.similar_container);
    ConstraintLayout constraintLayout = d.findViewById(R.id.similar_full_cont);
    constraintLayout.setBackgroundColor(blackColor);
    ImageView backdrop = d.findViewById(R.id.similar_backdrop);
    RecyclerView list = d.findViewById(R.id.similar_list);
    list.setLayoutManager(new GridLayoutManager(context, 2));
    list.setHasFixedSize(true);
    Picasso.with(context).load(BASE_IMG_URL + IdBus.getPath()).into(backdrop);
    container.setVisibility(View.GONE);
    list.setAdapter(new StarAdapter(movie.getStartMovie()));
    d.show();
  }

  @Override public void transfrom(Object model) {
    if (model instanceof MovieCast) {
      MovieCast cast = (MovieCast) model;
      setup.getCastResult(cast);
    } else if (model instanceof StarMovie) {
      StarMovie movie = (StarMovie) model;
      dialogSetup(movie);
    }
  }
}
