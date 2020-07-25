package com.example.abreak.movie.ui.screens.maincontentactivity.adapter;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import butterknife.BindView;
import butterknife.OnClick;

import com.bumptech.glide.Glide;
import com.example.abreak.R;
import com.example.abreak.movie.model.models.PlayingMovies;
import com.example.abreak.movie.ui.base.BaseRecyclerAdapter;
import com.example.abreak.movie.ui.base.BaseViewHolder;
import com.example.abreak.movie.utility.Constants;
import com.example.abreak.movie.utility.Other;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.abreak.movie.utility.Constants.BASE_IMG_URL;

public class PlayingAdapter extends BaseRecyclerAdapter<PlayingMovies.Results, BaseViewHolder> {
private OnSingleMovieClick itemClick;
  public PlayingAdapter(
      List<PlayingMovies.Results> recyclerItems,OnSingleMovieClick itemClick) {
    super(recyclerItems);
    this.itemClick=itemClick;
  }

  @NonNull
  @Override
  public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = getLayoutInflater(parent.getContext()).inflate(R.layout.single_movie, parent, false);
    return new PlayingHolder(v);
  }

  @SuppressLint("SetTextI18n") @Override public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
    PlayingHolder movieHolder = (PlayingHolder) holder;
    Picasso.with(movieHolder.itemView.getContext())
            .load(BASE_IMG_URL + getItematPosition(position).getPosterPath())
            .into(movieHolder.moviePoster);
    movieHolder.movieName.setText(getItematPosition(position).getTitle());
    movieHolder.movieProduction.setText("  " + getItematPosition(position).getDate());
    movieHolder.movieRating.setText("  " + getItematPosition(position).getVoteAverage() + "/10");
    Other.fadeAdapterAnimation(movieHolder.itemView.getContext(),movieHolder.itemView,position,-1);

  }

  class PlayingHolder extends BaseViewHolder {
    @BindView(R.id.single_movie_img) ImageView moviePoster;
    @BindView(R.id.single_movie_name) TextView movieName;
    @BindView(R.id.singe_movie_production_year) TextView movieProduction;
    @BindView(R.id.singe_movie_rating) TextView movieRating;

    PlayingHolder(View itemView) {
      super(itemView);
    }
    @OnClick()
    public void onClick(){
      itemClick.getMovieId(getItematPosition(getLayoutPosition()).getId(),getItematPosition(getLayoutPosition()).getBackDropImage());
    }
  }
}
