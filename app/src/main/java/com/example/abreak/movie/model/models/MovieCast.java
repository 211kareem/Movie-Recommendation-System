package com.example.abreak.movie.model.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MovieCast {
  @SerializedName("cast") private List<Cast> cast;

  public List<Cast> getCast() {
    return cast;
  }

  public class Cast {
    @SerializedName("profile_path") private String path;
    @SerializedName("name") private String name;
    @SerializedName("character") private String characterName;
    @SerializedName("id") private int id;

    public String getPath() {
      return path;
    }

    public String getName() {
      return name;
    }

    public String getCharacterName() {
      return characterName;
    }

    public int getId() {
      return id;
    }
  }
}
