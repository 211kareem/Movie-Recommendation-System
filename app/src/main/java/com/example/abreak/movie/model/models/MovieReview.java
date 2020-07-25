package com.example.abreak.movie.model.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MovieReview {
  @SerializedName("page") private int currentPage;
  @SerializedName("total_pages") private int totalPages;
  @SerializedName("total_results") private int totalReviews;
  @SerializedName("results") private List<Reviews> reviews;

  public int getCurrentPage() {
    return currentPage;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public int getTotalReviews() {
    return totalReviews;
  }

  public List<Reviews> getReviews() {
    return reviews;
  }

  public class Reviews {
    @SerializedName("author") private String authorName;
    @SerializedName("content") private String content;
    @SerializedName("url") private String url;

    public String getAuthorName() {
      return authorName;
    }

    public String getContent() {
      return content;
    }

    public String getUrl() {
      return url;
    }
  }
}
