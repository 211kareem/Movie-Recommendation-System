package com.example.abreak.movie.ui.screens.youtube;

import android.os.Bundle;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.abreak.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import static com.example.abreak.movie.utility.Constants.YOUTUBE_API_KEY;
import static com.example.abreak.movie.utility.Constants.YOUTUBE_VIDEO_KEY;

@SuppressWarnings("ConstantConditions") public class YoutubeScreen extends YouTubeBaseActivity implements
    YouTubePlayer.OnInitializedListener {

  @BindView(R.id.recap_player) YouTubePlayerView playerView;

  private String id;

  @Override protected void onCreate(Bundle bundle) {
    super.onCreate(bundle);
    setContentView(R.layout.youtube_page);
    ButterKnife.bind(this);
    playerView.initialize(YOUTUBE_API_KEY, this);
    id = getIntent().getExtras().getString(YOUTUBE_VIDEO_KEY);
  }

  private YouTubePlayer.PlaybackEventListener playbackEventListener =
      new YouTubePlayer.PlaybackEventListener() {
        @Override public void onPlaying() {

        }

        @Override public void onPaused() {

        }

        @Override public void onStopped() {

        }

        @Override public void onBuffering(boolean b) {

        }

        @Override public void onSeekTo(int i) {

        }
      };
  private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener =
      new YouTubePlayer.PlayerStateChangeListener() {
        @Override public void onLoading() {

        }

        @Override public void onLoaded(String s) {

        }

        @Override public void onAdStarted() {

        }

        @Override public void onVideoStarted() {

        }

        @Override public void onVideoEnded() {

        }

        @Override public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
      };

  @Override
  public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer,
      boolean b) {
    youTubePlayer.setPlaybackEventListener(playbackEventListener);
    youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
    if (!b) {
      youTubePlayer.cueVideo(id);
    }
  }

  @Override public void onInitializationFailure(YouTubePlayer.Provider provider,
      YouTubeInitializationResult youTubeInitializationResult) {

  }
}
