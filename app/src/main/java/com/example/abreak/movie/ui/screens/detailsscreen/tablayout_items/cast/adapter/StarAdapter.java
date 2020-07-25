package com.example.abreak.movie.ui.screens.detailsscreen.tablayout_items.cast.adapter;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import butterknife.BindView;
import butterknife.OnClick;

import com.bumptech.glide.Glide;
import com.example.abreak.R;
import com.example.abreak.movie.model.models.StarMovie;
import com.example.abreak.movie.ui.base.BaseRecyclerAdapter;
import com.example.abreak.movie.ui.base.BaseViewHolder;
import com.example.abreak.movie.ui.screens.detailsscreen.MovieDetailsScreen;
import com.example.abreak.movie.utility.ActivityUtil;
import com.example.abreak.movie.utility.Constants;
import com.example.abreak.movie.utility.Other;
import com.squareup.picasso.Picasso;
import java.util.List;

import static com.example.abreak.movie.utility.Constants.BACKDROP_PATH;
import static com.example.abreak.movie.utility.Constants.BASE_IMG_URL;
import static com.example.abreak.movie.utility.Constants.MOVIE_ID;

public class StarAdapter extends BaseRecyclerAdapter<StarMovie.Cast, BaseViewHolder> {
  public StarAdapter(
      List<StarMovie.Cast> recyclerItems) {
    super(recyclerItems);
  }

  @NonNull
  @Override
  public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = getLayoutInflater(parent.getContext()).inflate(R.layout.single_details_movie, parent,
        false);
    return new StarHolder(v);
  }

  @Override public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
    StarHolder hold = (StarHolder) holder;
    Picasso.with(hold.itemView.getContext())
            .load(BASE_IMG_URL + getItematPosition(position).getPosterPath())
            .into(hold.image);
    hold.name.setText(getItematPosition(position).getTitle());
    Other.adapterAnimation(hold.itemView.getContext(), hold.itemView, position, -1);
  }

  class StarHolder extends BaseViewHolder {
    @BindView(R.id.single_details_movie_image) ImageView image;
    @BindView(R.id.single_details_movie_name) TextView name;

    StarHolder(View itemView) {
      super(itemView);
    }

    @OnClick()
    public void onClick() {
      Intent in = new Intent(itemView.getContext(), MovieDetailsScreen.class);
      int id = getItematPosition(getLayoutPosition()).getId();
      String path = getItematPosition(getLayoutPosition()).getBackdropPath();
      in.putExtra(MOVIE_ID, id);
      in.putExtra(BACKDROP_PATH, path);
      in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
      ActivityUtil.intentWithData(itemView.getContext(), in);
    }
  }
}
