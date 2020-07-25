package com.example.abreak.movie.ui.screens.detailsscreen;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import butterknife.BindString;

import com.example.abreak.R;
import com.example.abreak.movie.ui.screens.detailsscreen.tablayout_items.recommendation.Recommendations;
import com.example.abreak.movie.ui.screens.detailsscreen.tablayout_items.similars.Similars;
import com.example.abreak.movie.ui.screens.detailsscreen.tablayout_items.cast.Cast;
import com.example.abreak.movie.ui.screens.detailsscreen.tablayout_items.details.MovieDetails;
import com.example.abreak.movie.ui.screens.detailsscreen.tablayout_items.review.Reviews;
import com.example.abreak.movie.ui.screens.detailsscreen.tablayout_items.trailers.Trailers;

@SuppressWarnings("UnnecessaryLocalVariable") public class TabAdapter extends FragmentPagerAdapter {
  @BindString(R.string.details) String details;
  @BindString(R.string.cast) String cast;
  @BindString(R.string.trailers) String trailers;
  @BindString(R.string.reviews) String reviews;
  @BindString(R.string.recommend) String recommend;
  @BindString(R.string.similars) String similar;

  TabAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override public Fragment getItem(int position) {
    switch (position) {
      case 0:
        MovieDetails details = new MovieDetails();
        return details;
      case 1:
        Cast cast = new Cast();
        return cast;
      case 2:
        Trailers trailers = new Trailers();
        return trailers;
      case 3:
        Reviews reviews = new Reviews();
        return reviews;
      case 4:
        Recommendations recommend = new Recommendations();
        return recommend;
      case 5:
        Similars similars = new Similars();
        return similars;
      default:
        return null;
    }
  }

  @Nullable
  @Override public CharSequence getPageTitle(int position) {
    switch (position) {
      case 0:
        return "DETAILS";
      case 1:
        return "CAST";
      case 2:
        return "TRAILERS";
      case 3:
        return "REVIEWS";
      case 4:
        return "RECOMMENDED MOVIES";
      case 5:
        return "SIMILAR MOVIES";
      default:
        return null;
    }
  }

  @Override public int getCount() {
    return 6;
  }
}
