package com.example.abreak.movie.ui.screens.detailsscreen.tablayout_items.cast;


import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;

import com.bumptech.glide.Glide;
import com.example.abreak.R;
import com.example.abreak.movie.model.bus.IdBus;
import com.example.abreak.movie.model.models.MovieCast;
import com.example.abreak.movie.ui.base.BaseFragment;
import com.example.abreak.movie.ui.screens.detailsscreen.tablayout_items.cast.adapter.CastAdapter;
import com.example.abreak.movie.ui.screens.maincontentactivity.adapter.OnSingleMovieClick;
import com.example.abreak.movie.utility.Constants;
import com.squareup.picasso.Picasso;

import static com.example.abreak.movie.utility.Constants.BASE_IMG_URL;

public class Cast extends BaseFragment implements CastSetup, OnSingleMovieClick {
  @BindView(R.id.cast_list)
  RecyclerView castList;
  @BindView(R.id.cast_container) ImageView layout;

  String backdropPath;
  CastPresenter presenter;

  public static Cast newInstance() {
    return new Cast();
  }

  @Override public int fragmentLayout() {
    return R.layout.cast;
  }

  @Override public void init() {
    recyclerConfigs(castList);
    backdropPath = IdBus.getPath();
    Picasso.with(getContext()).load(BASE_IMG_URL + backdropPath).into(layout);
    presenter = new CastPresenter(getContext(), disposables, this, IdBus.getId());
  }

  private void recyclerConfigs(RecyclerView view) {
    view.setLayoutManager(new GridLayoutManager(getContext(), 2));
    view.setHasFixedSize(true);
  }

  @Override public void getCastResult(MovieCast cast) {
    if (cast!=null) {
      castList.setAdapter(new CastAdapter(cast.getCast(), this));
    }
  }

  @Override public void getMovieId(int id, String path) {
    presenter.loadCastMovies(id);
  }
}
