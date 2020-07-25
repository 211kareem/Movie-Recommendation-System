package com.example.abreak.movie.model.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class StarMovie {
  @SerializedName("cast") private List<Cast> startMovie;

  public List<Cast> getStartMovie() {
    return startMovie;
  }

  public class Cast {
    @SerializedName("title") private String title;
    @SerializedName("poster_path") private String posterPath;
    @SerializedName("backdrop_path") private String backdropPath;
    @SerializedName("id") private int id;

    public String getTitle() {
      return title; }

    public String getPosterPath() {
      return posterPath; }

    public String getBackdropPath() {
      return backdropPath; }

    public int getId() {
      return id;
    }
  }
}
