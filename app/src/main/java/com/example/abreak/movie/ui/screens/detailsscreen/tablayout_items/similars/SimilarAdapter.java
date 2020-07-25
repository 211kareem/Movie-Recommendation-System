package com.example.abreak.movie.ui.screens.detailsscreen.tablayout_items.similars;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import butterknife.BindView;
import butterknife.OnClick;

import com.bumptech.glide.Glide;
import com.example.abreak.R;
import com.example.abreak.movie.model.models.SimilarMovies;
import com.example.abreak.movie.ui.base.BaseRecyclerAdapter;
import com.example.abreak.movie.ui.base.BaseViewHolder;
import com.example.abreak.movie.ui.screens.maincontentactivity.adapter.OnSingleMovieClick;
import com.example.abreak.movie.utility.Constants;
import com.example.abreak.movie.utility.Other;
import com.squareup.picasso.Picasso;
import java.util.List;

import static com.example.abreak.movie.utility.Constants.BASE_IMG_URL;

public class SimilarAdapter extends BaseRecyclerAdapter<SimilarMovies.Results, BaseViewHolder> {
private OnSingleMovieClick click;
  SimilarAdapter(
      List<SimilarMovies.Results> recyclerItems, OnSingleMovieClick click) {
    super(recyclerItems);
    this.click=click;
  }

  @NonNull
  @Override
  public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = getLayoutInflater(parent.getContext()).inflate(R.layout.single_details_movie, parent,
        false);
    return new SimilarHolder(v);
  }

  @Override public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
    SimilarHolder hold = (SimilarHolder) holder;
    Picasso.with(hold.itemView.getContext())
            .load(BASE_IMG_URL + getItematPosition(position).getPosterPath())
            .into(hold.image);
    hold.name.setText(getItematPosition(position).getTitle());
    Other.adapterAnimation(hold.itemView.getContext(),hold.itemView,position,-1);
  }

  class SimilarHolder extends BaseViewHolder {
    @BindView(R.id.single_details_movie_image) ImageView image;
    @BindView(R.id.single_details_movie_name) TextView name;

    SimilarHolder(View itemView) {
      super(itemView);
    }
    @OnClick()
    public void onClick() {
      click.getMovieId(getItematPosition(getLayoutPosition()).getId(),
          getItematPosition(getLayoutPosition()).getPosterPath());
    }
  }
}
