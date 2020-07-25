package com.example.abreak.movie.ui.base;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseRecyclerAdapter<T, H extends BaseViewHolder>
    extends RecyclerView.Adapter<H> {
  private List<T> recyclerItems;

  public BaseRecyclerAdapter(List<T> recyclerItems) {
    this.recyclerItems = recyclerItems;
  }

  protected LayoutInflater getLayoutInflater(Context context) {
    return LayoutInflater.from(context);
  }

  protected T getItematPosition(int position) {
    return recyclerItems.get(position);
  }

  @Override public int getItemCount() {
    return recyclerItems.size();
  }
}
