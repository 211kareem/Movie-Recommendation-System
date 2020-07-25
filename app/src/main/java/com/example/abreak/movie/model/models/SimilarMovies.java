package com.example.abreak.movie.model.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SimilarMovies {
  @SerializedName("page") private int currentPage;
  @SerializedName("total_pages") private int totalPages;
  @SerializedName("total_results") private int totalMovies;
  @SerializedName("results") private List<Results> results;

  public int getCurrentPage() {
    return currentPage;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public int getTotalMovies() {
    return totalMovies;
  }

  public List<Results> getResults() {
    return results;
  }

  public class Results {
    @SerializedName("backdrop_path") private String backdropImage;
    @SerializedName("original_title") private String title;
    @SerializedName("poster_path") private String posterPath;
    @SerializedName("id") private int id;

    public String getBackdropImage() {
      return backdropImage;
    }

    public String getTitle() {
      return title;
    }

    public String getPosterPath() {
      return posterPath;
    }

    public int getId() {
      return id;
    }
  }
}
