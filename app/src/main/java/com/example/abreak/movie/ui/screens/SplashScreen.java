package com.example.abreak.movie.ui.screens;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.abreak.ActiveMain;
import com.example.abreak.R;
import com.example.abreak.movie.ui.screens.maincontentactivity.MainContentScreen;
import com.example.abreak.signed.Login;
import com.example.abreak.signed.SignUpActivity;

public class SplashScreen extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    Handler h = new Handler();
    h.postDelayed(new Runnable() {
      @Override
      public void run() {
        Intent intent = new Intent(SplashScreen.this, Login.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
      }
    }, 2000);

  }




}