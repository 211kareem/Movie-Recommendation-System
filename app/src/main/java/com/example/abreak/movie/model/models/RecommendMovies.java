package com.example.abreak.movie.model.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class RecommendMovies {
  @SerializedName("page") private int currentPage;
  @SerializedName("total_pages") private int totalPages;
  @SerializedName("total_results") private int totalResults;
  @SerializedName("results") private List<Results> recommendedMovies;

  public int getCurrentPage() {
    return currentPage;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public int getTotalResults() {
    return totalResults;
  }

  public List<Results> getRecommendedMovies() {
    return recommendedMovies;
  }

  public class Results {
    @SerializedName("poster_path") private String path;
    @SerializedName("original_title") private String title;
    @SerializedName("id") private int id;

    public String getPath() {
      return path;
    }

    public String getTitle() {
      return title;
    }

    public int getId() {
      return id;
    }
  }
}
