package com.example.abreak.movie.ui.screens.detailsscreen.tablayout_items.trailers;

import android.content.Intent;

import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;

import com.bumptech.glide.Glide;
import com.example.abreak.R;
import com.example.abreak.movie.model.bus.IdBus;
import com.example.abreak.movie.model.models.MovieTrailer;
import com.example.abreak.movie.ui.base.BaseFragment;
import com.example.abreak.movie.ui.screens.youtube.YoutubeScreen;
import com.example.abreak.movie.utility.ActivityUtil;
import com.example.abreak.movie.utility.Constants;
import com.squareup.picasso.Picasso;

import static com.example.abreak.movie.utility.Constants.BASE_IMG_URL;
import static com.example.abreak.movie.utility.Constants.YOUTUBE_VIDEO_KEY;

public class Trailers extends BaseFragment implements TrailerSetup, TrailerClick {

  @BindView(R.id.trailer_container) ImageView trailerContainer;
  @BindView(R.id.trailers_list)
  RecyclerView trailersList;

  TrailerPresenter presenter;

  public static Trailers newInstance() {
    return new Trailers();
  }

  @Override public int fragmentLayout() {
    return R.layout.trailers;
  }

  @Override public void init() {
    Picasso.with(getContext()).load(BASE_IMG_URL + IdBus.getPath()).into(trailerContainer);
    int id = IdBus.getId();
    setupRecycler(trailersList);
    presenter = new TrailerPresenter(getContext(), disposables, id, this);
  }

  private void setupRecycler(RecyclerView recyclerView) {
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    recyclerView.setHasFixedSize(true);
  }

  @Override public void setTrailerData(MovieTrailer trailers) {
    if (trailers != null) {
      trailersList.setAdapter(new TrailerAdapter(trailers.getTrailerList(), this));
    }
  }

  @Override public void trailerId(String videoId) {
    Intent in = new Intent(getContext(), YoutubeScreen.class);
    in.putExtra(YOUTUBE_VIDEO_KEY, videoId);
    assert getContext() != null;
    ActivityUtil.intentWithData(getContext(), in);
  }
}
