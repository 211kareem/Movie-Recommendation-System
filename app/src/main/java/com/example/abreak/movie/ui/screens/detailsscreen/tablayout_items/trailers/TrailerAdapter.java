package com.example.abreak.movie.ui.screens.detailsscreen.tablayout_items.trailers;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import butterknife.BindView;
import butterknife.OnClick;

import com.bumptech.glide.Glide;
import com.example.abreak.R;
import com.example.abreak.movie.model.models.MovieTrailer;
import com.example.abreak.movie.ui.base.BaseRecyclerAdapter;
import com.example.abreak.movie.ui.base.BaseViewHolder;
import com.example.abreak.movie.utility.Constants;
import com.example.abreak.movie.utility.Other;
import com.squareup.picasso.Picasso;
import java.util.List;

import static com.example.abreak.movie.utility.Constants.BASE_YOUTUBE_THUMBNAIL_LINK;
import static com.example.abreak.movie.utility.Constants.END_YOUTUBE_THUMBNAIL_LINK;

public class TrailerAdapter extends BaseRecyclerAdapter<MovieTrailer.Results, BaseViewHolder> {
  private TrailerClick click;

  TrailerAdapter(
      List<MovieTrailer.Results> recyclerItems, TrailerClick click) {
    super(recyclerItems);
    this.click = click;
  }

  @NonNull
  @Override
  public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v =
        getLayoutInflater(parent.getContext()).inflate(R.layout.single_trailer_item, parent, false);
    return new TrailerHolder(v);
  }

  @Override public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
    TrailerHolder holde = (TrailerHolder) holder;
    Picasso.with(holde.itemView.getContext())
            .load(BASE_YOUTUBE_THUMBNAIL_LINK
                    + getItematPosition(position).getKey()
                    + END_YOUTUBE_THUMBNAIL_LINK).into(holde.trailers);
    holde.name.setText(getItematPosition(position).getTitle());
    Other.adapterAnimation(holde.itemView.getContext(),holde.itemView,position,-1);
  }

  class TrailerHolder extends BaseViewHolder {
    @BindView(R.id.youtube_thumbnail) ImageView trailers;
    @BindView(R.id.trailer_name) TextView name;

    TrailerHolder(View itemView) {
      super(itemView);
    }

    @OnClick()
    public void onClick() {
      click.trailerId(getItematPosition(getLayoutPosition()).getKey());
    }

    @OnClick({ R.id.youtube_thumbnail })
    public void onImageClick(View v) {
      switch (v.getId()) {
        case R.id.youtube_thumbnail:
          click.trailerId(getItematPosition(getLayoutPosition()).getKey());
          break;
      }
    }
  }
}
