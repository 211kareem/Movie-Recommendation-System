package com.example.abreak.movie.model.models;

import com.google.gson.annotations.SerializedName;

public class ExternalIds {
  @SerializedName("imdb_id") private String imdbId;
  @SerializedName("facebook_id") private String facebookId;
  @SerializedName("instagram_id") private String instagramId;

  public String getImdbId() {
    return imdbId;
  }

  public String getFacebookId() {
    return facebookId;
  }

  public String getInstagramId() {
    return instagramId;
  }

}
