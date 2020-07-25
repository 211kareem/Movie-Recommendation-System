package com.example.abreak.movie.utility;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.example.abreak.R;
import java.util.Locale;

public class ActivityUtil {

  public static void intentWithoutData(Context context, Class<?> destination) {
    Intent in = new Intent(context, destination);
    context.startActivity(in);
  }
  public static void makeViewRotate(Context context, View view) {
    Animation animation = AnimationUtils.loadAnimation(context, R.anim.rotate);
    animation.setRepeatCount(1);
    view.startAnimation(animation);
  }

  public static void intentWithData(Context context, Intent in) {
    context.startActivity(in);
  }

  public static void intentDataWithTimer(final Context context, final Class<?> destination,
      long millis) {
    Handler h = new Handler();
    h.postDelayed(() -> {
      Intent in = new Intent(context, destination);
      context.startActivity(in);
    }, millis);
  }

  public static void languageToLoad(String lang, Context context, Class<?> destination) {
    Locale locale = new Locale(lang);
    Locale.setDefault(locale);
    Configuration config = new Configuration();
    config.locale = locale;
    context.getResources()
        .updateConfiguration(config, context.getResources().getDisplayMetrics());
    intentWithoutData(context, destination);
  }
  public static void goToFacebook(Context context,String url) {
    try {
      String facebookUrl = getFacebookPageURL(context,url);
      Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
      facebookIntent.setData(Uri.parse(facebookUrl));
     context.startActivity(facebookIntent);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public static Intent newInstagramProfileIntent(Context context , String url) {
    final Intent intent = new Intent(Intent.ACTION_VIEW);
    try {
      if (context.getPackageManager().getPackageInfo("com.instagram.android", 0) != null) {
        if (url.endsWith("/")) {
          url = url.substring(0, url.length() - 1);
        }
        final String username = url.substring(url.lastIndexOf("/") + 1);

        intent.setData(Uri.parse("http://instagram.com/_u/" + username));
        intent.setPackage("com.instagram.android");
        return intent;
      }
    } catch (PackageManager.NameNotFoundException ignored) {
    }
    intent.setData(Uri.parse(url));
    return intent;
  }


  private static String getFacebookPageURL(Context context,String url) {
    String FACEBOOK_URL = "https://www.facebook.com/"+url;
    String facebookurl = null;

    try {
      PackageManager packageManager =context.getPackageManager();

      if (packageManager != null) {
        Intent activated = packageManager.getLaunchIntentForPackage("com.facebook.katana");

        if (activated != null) {
          int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;

          if (versionCode >= 3002850) {
            facebookurl = "fb://facewebmodal/f?href="+FACEBOOK_URL;
          }
        } else {
          facebookurl = FACEBOOK_URL;
        }
      } else {
        facebookurl = FACEBOOK_URL;
      }
    } catch (Exception e) {
      facebookurl = FACEBOOK_URL;
    }
    return facebookurl;
  }

}
