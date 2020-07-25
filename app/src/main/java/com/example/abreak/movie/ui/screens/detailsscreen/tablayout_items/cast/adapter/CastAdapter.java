package com.example.abreak.movie.ui.screens.detailsscreen.tablayout_items.cast.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import butterknife.BindView;
import butterknife.OnClick;

import com.bumptech.glide.Glide;
import com.example.abreak.R;
import com.example.abreak.movie.model.models.MovieCast;
import com.example.abreak.movie.ui.base.BaseRecyclerAdapter;
import com.example.abreak.movie.ui.base.BaseViewHolder;
import com.example.abreak.movie.ui.screens.maincontentactivity.adapter.OnSingleMovieClick;
import com.example.abreak.movie.utility.Constants;
import com.example.abreak.movie.utility.Other;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.abreak.movie.utility.Constants.BASE_IMG_URL;

public class CastAdapter extends BaseRecyclerAdapter<MovieCast.Cast, BaseViewHolder> {
 private OnSingleMovieClick click;

  public CastAdapter(
      List<MovieCast.Cast> recyclerItems, OnSingleMovieClick click) {
    super(recyclerItems);
    this.click = click;
  }

  @NonNull
  @Override
  public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v =
        getLayoutInflater(parent.getContext()).inflate(R.layout.single_cast_item, parent, false);
    return new CastHolder(v);
  }

  @Override public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
    CastHolder holder1 = (CastHolder) holder;
      Picasso.with(holder1.itemView.getContext())
              .load(BASE_IMG_URL + getItematPosition(position).getPath())
              .into(holder1.image);
    holder1.actorName.setText(getItematPosition(position).getName());
    holder1.charName.setText(getItematPosition(position).getCharacterName());
    Other.adapterAnimation(holder1.itemView.getContext(), holder1.itemView, position, -1);
  }

  class CastHolder extends BaseViewHolder {
    @BindView(R.id.cast_img) ImageView image;
    @BindView(R.id.cast_char_name) TextView charName;
    @BindView(R.id.cast_actor_name) TextView actorName;

    CastHolder(View itemView) {
      super(itemView);
    }

    @OnClick()
    public void onClick() {
      click.getMovieId(getItematPosition(getLayoutPosition()).getId(),"");
    }

  }
}
