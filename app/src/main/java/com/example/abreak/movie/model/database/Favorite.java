package com.example.abreak.movie.model.database;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Favorite implements Parcelable {
  @PrimaryKey(autoGenerate = true)
  private int id;
  @ColumnInfo(name = "movie_id")
  private int movieId;
  @ColumnInfo(name = "movie_name")
  private String name;
  @ColumnInfo(name = "movie_img")
  private String image;

  public Favorite(int movieId, String name, String image) {
    this.movieId = movieId;
    this.name = name;
    this.image = image;
  }

  protected Favorite(Parcel in) {
    id = in.readInt();
    movieId = in.readInt();
    name = in.readString();
    image = in.readString();
  }

  public static final Creator<Favorite> CREATOR = new Creator<Favorite>() {
    @Override
    public Favorite createFromParcel(Parcel in) {
      return new Favorite(in);
    }

    @Override
    public Favorite[] newArray(int size) {
      return new Favorite[size];
    }
  };

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getMovieId() {
    return movieId;
  }

  public void setMovieId(int movieId) {
    this.movieId = movieId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeInt(movieId);
    dest.writeString(name);
    dest.writeString(image);
  }
}
