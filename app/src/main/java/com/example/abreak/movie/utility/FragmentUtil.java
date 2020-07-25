package com.example.abreak.movie.utility;

import android.app.Activity;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class FragmentUtil {

  public static void showFragmentOnActivity(Activity hostActivity, Fragment showingFragment,
                                            @IdRes int container, boolean replace) {
    FragmentManager fragmentManager = ((FragmentActivity) hostActivity).getSupportFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    if (replace) {
      transaction.replace(container, showingFragment).commit();
    } else {
      transaction.addToBackStack(null);
      transaction.add(container, showingFragment).commit();
    }
  }

  public static void showFragmentOnFragment(Fragment hostFragment, Fragment guestFragment,
      @IdRes int container, boolean replace) {
    FragmentManager manager = hostFragment.getChildFragmentManager();
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    if (replace) {
      transaction.replace(container, guestFragment).commit();
    } else {
      transaction.addToBackStack(null);
      transaction.add(container, guestFragment).commit();
    }
  }
}
