package com.example.abreak.movie.ui.screens.detailsscreen.tablayout_items.details.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import butterknife.BindView;

import com.bumptech.glide.Glide;
import com.example.abreak.R;
import com.example.abreak.movie.model.models.Details;
import com.example.abreak.movie.ui.base.BaseRecyclerAdapter;
import com.example.abreak.movie.ui.base.BaseViewHolder;
import com.example.abreak.movie.utility.Constants;
import com.example.abreak.movie.utility.Other;
import com.squareup.picasso.Picasso;
import java.util.List;

import static com.example.abreak.movie.utility.Constants.BASE_IMG_URL;

public class ProductionAdapter
    extends BaseRecyclerAdapter<Details.ProductionCompanies, BaseViewHolder> {

  public ProductionAdapter(
      List<Details.ProductionCompanies> recyclerItems) {
    super(recyclerItems);
  }

  @NonNull
  @Override
  public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = getLayoutInflater(parent.getContext()).inflate(R.layout.single_production_item, parent,
        false);
    return new CollectionHolder(v);
  }

  @Override public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
    CollectionHolder hold = (CollectionHolder) holder;
    Picasso.with(hold.itemView.getContext())
            .load(BASE_IMG_URL + getItematPosition(position).getLogo())
            .into(hold.image);
    hold.name.setText(getItematPosition(position).getName());

    Other.fadeAdapterAnimation(hold.itemView.getContext(),hold.itemView,position,-1);
  }

  class CollectionHolder extends BaseViewHolder {
    @BindView(R.id.collection_image) ImageView image;
    @BindView(R.id.movie_collection_name) TextView name;

    CollectionHolder(View itemView) {
      super(itemView);
    }
  }
}
