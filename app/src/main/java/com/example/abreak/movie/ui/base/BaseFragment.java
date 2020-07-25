package com.example.abreak.movie.ui.base;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseFragment extends Fragment {
  private FragmentActivity context;
  private Unbinder unbinder;
  protected CompositeDisposable disposables = new CompositeDisposable();

  @Nullable
  @Override public FragmentActivity getContext() {
    return context;
  }

  private View setView(LayoutInflater inflater, ViewGroup group) {
    int layout = fragmentLayout();
    View v = inflater.inflate(layout, group, false);
    unbinder = ButterKnife.bind(this, v);
    this.context = (FragmentActivity) v.getContext();
    return v;
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    return setView(inflater, container);
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    init();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    if (disposables.isDisposed()) {
      disposables.clear();
    }
  }

  public abstract @LayoutRes
  int fragmentLayout();

  public abstract void init();
}
