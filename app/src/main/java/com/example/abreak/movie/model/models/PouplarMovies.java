package com.example.abreak.movie.model.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PouplarMovies implements Parcelable {
  @SerializedName("page") private int page;
  @SerializedName("results") private List<Results> results;
  @SerializedName("total_pages") private int totalPage;
  @SerializedName("total_results") private int movieCount;

  public int getTotalPage() {
    return totalPage;
  }

  protected PouplarMovies(Parcel in) {
    page = in.readInt();
    results = in.createTypedArrayList(Results.CREATOR);
    totalPage = in.readInt();
    movieCount=in.readInt();
  }

  public static final Creator<PouplarMovies> CREATOR = new Creator<PouplarMovies>() {
    @Override
    public PouplarMovies createFromParcel(Parcel in) {
      return new PouplarMovies(in);
    }

    @Override
    public PouplarMovies[] newArray(int size) {
      return new PouplarMovies[size];
    }
  };

  public int getPage() {
    return page;
  }

  public List<Results> getResults() {
    return results;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(page);
    dest.writeTypedList(results);
    dest.writeInt(totalPage);
    dest.writeInt(movieCount);
  }

  public int getMovieCount() {
    return movieCount;
  }

  public static class Results implements Parcelable {
    @SerializedName("id") private int id;
    @SerializedName("original_title") private String title;
    @SerializedName("poster_path") private String posterPath;
    @SerializedName("original_language") private String language;
    @SerializedName("overview") private String overView;
    @SerializedName("release_date") private String date;
    @SerializedName("vote_average") private double voteAverage;
    @SerializedName("backdrop_path") private String backDropImage;

    protected Results(Parcel in) {
      id = in.readInt();
      title = in.readString();
      posterPath = in.readString();
      language = in.readString();
      overView = in.readString();
      date = in.readString();
      voteAverage = in.readDouble();
      backDropImage = in.readString();
    }

    public String getBackDropImage() {
      return backDropImage;
    }

    public static final Creator<Results> CREATOR = new Creator<Results>() {
      @Override
      public Results createFromParcel(Parcel in) {
        return new Results(in);
      }

      @Override
      public Results[] newArray(int size) {
        return new Results[size];
      }
    };

    public int getId() {
      return id;
    }

    public String getTitle() {
      return title;
    }

    public String getPosterPath() {
      return posterPath;
    }

    public String getLanguage() {
      return language;
    }

    public String getOverView() {
      return overView;
    }

    public String getDate() {
      return date;
    }

    public double getVoteAverage() {
      return voteAverage;
    }

    @Override public int describeContents() {
      return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
      dest.writeInt(id);
      dest.writeString(title);
      dest.writeString(posterPath);
      dest.writeString(language);
      dest.writeString(overView);
      dest.writeString(date);
      dest.writeDouble(voteAverage);
      dest.writeString(backDropImage);
    }
  }
}
