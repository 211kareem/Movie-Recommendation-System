package com.example.abreak.movie.utility;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.LayoutRes;

public class Other {
  public static void adapterAnimation(Context context, View animated, int index, int layoutPos) {
    if (index > layoutPos) {
      Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
      animated.startAnimation(animation);
      index = layoutPos;
    }
  }

  public static void fadeAdapterAnimation(Context context, View animated, int index,
      int layoutPos) {
    if (index > layoutPos) {
      Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
      animated.startAnimation(animation);
      index = layoutPos;
    }
  }

  public static Dialog transparentDialog(Context context, @LayoutRes int layoutId, int style) {
    final Dialog transDialog = new Dialog(context, style);
    transDialog.setContentView(layoutId);
    transDialog.setCancelable(true);
    transDialog.setCanceledOnTouchOutside(true);
    return transDialog;
  }

  @SuppressWarnings("ConstantConditions") public static boolean isOnline(Context context) {
    ConnectivityManager cm =
        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo netInfo = cm.getActiveNetworkInfo();
    return netInfo != null && netInfo.isConnectedOrConnecting();
  }
}
