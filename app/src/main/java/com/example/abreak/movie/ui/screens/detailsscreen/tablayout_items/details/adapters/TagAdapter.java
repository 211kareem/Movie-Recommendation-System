package com.example.abreak.movie.ui.screens.detailsscreen.tablayout_items.details.adapters;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import butterknife.BindView;
import com.example.abreak.R;
import com.example.abreak.movie.model.models.Details;
import com.example.abreak.movie.ui.base.BaseRecyclerAdapter;
import com.example.abreak.movie.ui.base.BaseViewHolder;
import com.example.abreak.movie.utility.Other;
import java.util.List;

public class TagAdapter extends BaseRecyclerAdapter<Details.Genres, BaseViewHolder> {

  public TagAdapter(
      List<Details.Genres> recyclerItems) {
    super(recyclerItems);
  }

  @NonNull
  @Override
  public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v =
        getLayoutInflater(parent.getContext()).inflate(R.layout.single_tag_item, parent, false);
    return new TagHolder(v);
  }

  @SuppressLint("SetTextI18n") @Override
  public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
    TagHolder hold = (TagHolder) holder;
    hold.tag.setText("  " + getItematPosition(position).getName());
    Other.adapterAnimation(hold.itemView.getContext(), hold.itemView, position, -1);
  }

  class TagHolder extends BaseViewHolder {
    @BindView(R.id.movie_tag) TextView tag;

    TagHolder(View itemView) {
      super(itemView);
    }
  }
}
