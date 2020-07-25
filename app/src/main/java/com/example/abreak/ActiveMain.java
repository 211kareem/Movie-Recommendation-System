package com.example.abreak;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.abreak.music.Music;
import com.example.abreak.signed.ProfileActivity;
import com.example.abreak.movie.ui.screens.maincontentactivity.MainContentScreen;

public class ActiveMain extends AppCompatActivity implements View.OnClickListener {
    private SoundPool soundpool;
    private int s1,s2,s3;
    private Switch aSwitch;
    boolean loaded = false;
    public static final String MyPREFERENCES = "nightModePrefs";
    public static final String KEY_ISNIGHTMODE = "isNightMode";
    SharedPreferences sharedpreferences;
    private boolean defValue;
    Button b1,b2,b3,btnMovie,btnMusic,Book,profile;
    ViewFlipper view_flipper,view_flipper1,viewFlipper;
    ImageView left,right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_main);
        btnMovie=findViewById(R.id.btnMovie);
        btnMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActiveMain.this, MainContentScreen.class);
                startActivity(intent);
            }
        });
        btnMusic=findViewById(R.id.btnMusic);
        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActiveMain.this, Music.class);
                startActivity(intent);
            }
        });
        Book=findViewById(R.id.Book);
        Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActiveMain.this, com.example.abreak.book.Book.class);
                startActivity(intent);
            }
        });

        profile=findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActiveMain.this, ProfileActivity.class);
                startActivity(intent);
            }
        });




        // Music play/pause
        b1=findViewById(R.id.btnM0);
        final MediaPlayer soundpool0 = MediaPlayer.create(ActiveMain.this,R.raw.s1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soundpool0.isPlaying()){
                    soundpool0.pause();
                    b1.setBackgroundResource(R.drawable.play);
                }else{soundpool0.start();
                    b1.setBackgroundResource(R.drawable.stop); }
            }
        });
        b2=findViewById(R.id.btnM1);
        final MediaPlayer soundpool1 = MediaPlayer.create(ActiveMain.this,R.raw.s2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soundpool1.isPlaying()){
                    soundpool1.pause();
                    b2.setBackgroundResource(R.drawable.play); }
                else{soundpool1.start();
                    b2.setBackgroundResource(R.drawable.stop);}
            }
        });

        b3=findViewById(R.id.btnM2);
        final MediaPlayer soundpool2 = MediaPlayer.create(ActiveMain.this,R.raw.s3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soundpool2.isPlaying()){
                    soundpool2.pause();
                    b3.setBackgroundResource(R.drawable.play); }
                else{soundpool2.start();
                    b3.setBackgroundResource(R.drawable.stop);}
            }
        });
        // Music play/pause End
        //slide setting
        view_flipper = findViewById(R.id.view_flipper);
        left  = findViewById(R.id.left);
        right  = findViewById(R.id.right);
        view_flipper.startFlipping();
        view_flipper.setFlipInterval(30000);
        left.setOnClickListener(this);
        right.setOnClickListener(this);


        view_flipper1 = findViewById(R.id.view_flipper1);
        view_flipper1.startFlipping();
        view_flipper1.setFlipInterval(3000);


        viewFlipper= findViewById(R.id.viewFlipper);
        viewFlipper.startFlipping();
        viewFlipper.setFlipInterval(3000);
        //slide setting End


        //Dark Mode
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        aSwitch = findViewById(R.id.switch1);
        checkNightModeActivated();
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    saveNightModeState(true);
                    recreate();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    saveNightModeState(false);
                    recreate();
                }
            }
        });
        //End Dark Mode
        //Music slider
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes =new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundpool =new SoundPool.Builder().setMaxStreams(3).setAudioAttributes(audioAttributes).build();}
        else{ soundpool =new SoundPool(3, AudioManager.STREAM_MUSIC,0);}
        s1= soundpool.load(this,R.raw.s1,1);
        s2= soundpool.load(this,R.raw.s2,1);
        s3= soundpool.load(this,R.raw.s3,1);
    }
    //Music ImageBtn play/pause
    public void playSound(View v){
        switch (v.getId()){
            case R.id.s1:
                soundpool.play(s1,1,1,0,0,1);
                soundpool.autoPause();
                break;
            case R.id.s2:
                soundpool.play(s2,1,1,0,0,1);
                soundpool.autoPause();

                break;
            case R.id.s3:
                soundpool.play(s3,1,1,0,0,1);
                soundpool.autoPause();
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (soundpool != null) soundpool.release(); }
    //Music ImageBtn play/pause End

    //Dark Mode fn.
    private void saveNightModeState(boolean nightMode) {
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putBoolean(KEY_ISNIGHTMODE, nightMode);
        editor.apply();
    }

    public void checkNightModeActivated(){
        if (sharedpreferences.getBoolean(KEY_ISNIGHTMODE, defValue)){
            aSwitch.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            aSwitch.setChecked(false);
            AppCompatDelegate.setDefaultNightMode((AppCompatDelegate.MODE_NIGHT_NO)); }
    }

    //Dark Mode fn. End
    //slide fn.

    @Override
    public void onClick(View v) {
        if(v==left){
            view_flipper.showNext();
        }else {
            view_flipper.showPrevious();
        }
    }

    public void previousView(View v) {
        view_flipper1.setInAnimation(this, R.anim.slide_out_right);
        view_flipper1.setOutAnimation(this, R.anim.slide_in_left);
        view_flipper1.showPrevious();
    }

    public void nextView(View v) {
        view_flipper1.setInAnimation(this, android.R.anim.slide_in_left);
        view_flipper1.setOutAnimation(this, android.R.anim.slide_out_right);
        view_flipper1.showNext();
    }
    public void previous(View v) {
        viewFlipper.setInAnimation(this, R.anim.slide_out_right);
        viewFlipper.setOutAnimation(this, R.anim.slide_in_left);
        viewFlipper.showPrevious();
    }

    public void next(View v) {
        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
        viewFlipper.showNext();
    }

    public void right(View v) {
        view_flipper.setInAnimation(this, R.anim.slide_out_right);
        view_flipper.setOutAnimation(this, R.anim.slide_in_left);
        view_flipper.showPrevious();
    }

    public void left(View v) {
        view_flipper.setInAnimation(this, android.R.anim.slide_in_left);
        view_flipper.setOutAnimation(this, android.R.anim.slide_out_right);
        view_flipper.showNext();
    }
    //slide fn. End



}