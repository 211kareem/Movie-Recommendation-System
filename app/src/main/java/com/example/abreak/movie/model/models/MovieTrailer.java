package com.example.abreak.movie.model.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MovieTrailer implements Parcelable {
  @SerializedName("results") private List<Results> trailerList;

  protected MovieTrailer(Parcel in) {
    trailerList = in.createTypedArrayList(Results.CREATOR);
  }

  public static final Creator<MovieTrailer> CREATOR = new Creator<MovieTrailer>() {
    @Override
    public MovieTrailer createFromParcel(Parcel in) {
      return new MovieTrailer(in);
    }

    @Override
    public MovieTrailer[] newArray(int size) {
      return new MovieTrailer[size];
    }
  };

  public List<Results> getTrailerList() {
    return trailerList;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeTypedList(trailerList);
  }

  public static class Results implements Parcelable {
    @SerializedName("name") private String title;
    @SerializedName("key") private String key;

    protected Results(Parcel in) {
      title = in.readString();
      key = in.readString();
    }

    public String getTitle() {
      return title;
    }

    public String getKey() {
      return key;
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

    @Override public int describeContents() {
      return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
      dest.writeString(title);
      dest.writeString(key);
    }
  }

}
