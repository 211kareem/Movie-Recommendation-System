package com.example.abreak.movie.ui.screens.detailsscreen;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import butterknife.BindView;
import com.example.abreak.R;
import com.example.abreak.movie.model.bus.IdBus;
import com.example.abreak.movie.ui.base.BaseActivity;
import com.google.android.material.tabs.TabLayout;

import static com.example.abreak.movie.utility.Constants.BACKDROP_PATH;
import static com.example.abreak.movie.utility.Constants.MOVIE_ID;

public class MovieDetailsScreen extends BaseActivity {
  @BindView(R.id.tabs_pager)
  ViewPager pager;
  @BindView(R.id.tab_layout)
  TabLayout tabLayout;

  @Override public int setLayout() {
    return R.layout.movie_detail_screen;
  }

  @Override public void init(Bundle savedState) {
    managerIncomingData();
    tabSetup();
  }

  @Override public void setupToolbar() {

  }

  private void managerIncomingData() {
    int id = getIntent().getExtras().getInt(MOVIE_ID);
    String path = getIntent().getExtras().getString(BACKDROP_PATH);
    IdBus.setId(id);
    IdBus.setPath(path);
  }

  private void tabSetup() {
    TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
    pager.setAdapter(adapter);
    tabLayout.setupWithViewPager(pager);
  }
}
