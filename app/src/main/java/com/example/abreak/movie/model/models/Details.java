package com.example.abreak.movie.model.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Details {
  @SerializedName("backdrop_path") private String backPath;
  @SerializedName("belongs_to_collection") private Collection collection;
  @SerializedName("budget") private long budget;
  @SerializedName("genres") private List<Genres> genres;
  @SerializedName("imdb_id") private String imdbId;
  @SerializedName("original_title") private String title;
  @SerializedName("overview") private String overview;
  @SerializedName("poster_path") private String posterPath;
  @SerializedName("production_companies") private List<ProductionCompanies> productionCompanies;
  @SerializedName("release_date") private String date;
  @SerializedName("runtime") private String runtime;
  @SerializedName("revenue") private long revenue;
  @SerializedName("vote_average") private double voteAverage;

  public String getBackPath() {
    return backPath;
  }

  public Collection getCollection() {
    return collection;
  }

  public long getBudget() {
    return budget;
  }

  public List<Genres> getGenres() {
    return genres;
  }

  public String getImdbId() {
    return imdbId;
  }

  public String getTitle() {
    return title;
  }

  public String getOverview() {
    return overview;
  }

  public String getPosterPath() {
    return posterPath;
  }

  public List<ProductionCompanies> getProductionCompanies() {
    return productionCompanies;
  }

  public String getDate() {
    return date;
  }

  public String getRuntime() {
    return runtime;
  }

  public long getRevenue() {
    return revenue;
  }

  public double getVoteAverage() {
    return voteAverage;
  }

  public class Collection {
    @SerializedName("name") private String name;
    @SerializedName("poster_path") private String posterPath;
    @SerializedName("backdrop_path") private String backdropPath;

    public String getName() {
      return name;
    }

    public String getPosterPath() {
      return posterPath;
    }

    public String getBackdropPath() {
      return backdropPath;
    }
  }

  public class Genres {
    @SerializedName("name") private String name;
    @SerializedName("id") private int id;

    public String getName() {
      return name;
    }

    public int getId() {
      return id;
    }
  }

  public class ProductionCompanies {
    @SerializedName("logo_path") private String logo;
    @SerializedName("name") private String name;
    @SerializedName("id") private int id;

    public String getLogo() {
      return logo;
    }

    public String getName() {
      return name;
    }

    public int getId() {
      return id;
    }
  }
}
