package com.example.abreak.movie.model.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Hollywood implements Parcelable {
  @SerializedName("page") private int currentPage;
  @SerializedName("total_pages") private int totalPage;
  @SerializedName("total_results") private int totalResult;
  @SerializedName("results") private List<Result> movies;

  protected Hollywood(Parcel in) {
    currentPage = in.readInt();
    totalPage = in.readInt();
    totalResult = in.readInt();
    movies = in.createTypedArrayList(Result.CREATOR);
  }

  public static final Creator<Hollywood> CREATOR = new Creator<Hollywood>() {
    @Override
    public Hollywood createFromParcel(Parcel in) {
      return new Hollywood(in);
    }

    @Override
    public Hollywood[] newArray(int size) {
      return new Hollywood[size];
    }
  };

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(currentPage);
    dest.writeInt(totalPage);
    dest.writeInt(totalResult);
    dest.writeTypedList(movies);
  }

  public int getCurrentPage() {
    return currentPage;
  }

  public int getTotalPage() {
    return totalPage;
  }

  public int getTotalResult() {
    return totalResult;
  }

  public List<Result> getMovies() {
    return movies;
  }

  public static class Result implements Parcelable {
    @SerializedName("name") private String name;
    @SerializedName("profile_path") private String path;
    @SerializedName("id") private int id;

    protected Result(Parcel in) {
      name = in.readString();
      path = in.readString();
      id = in.readInt();
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
      @Override
      public Result createFromParcel(Parcel in) {
        return new Result(in);
      }

      @Override
      public Result[] newArray(int size) {
        return new Result[size];
      }
    };

    @Override public int describeContents() {
      return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
      dest.writeString(name);
      dest.writeString(path);
      dest.writeInt(id);
    }

    public String getName() {
      return name;
    }

    public String getPath() {
      return path;
    }

    public int getId() {
      return id;
    }
  }
}
