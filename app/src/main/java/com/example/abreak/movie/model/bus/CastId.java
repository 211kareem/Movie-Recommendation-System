package com.example.abreak.movie.model.bus;

public class CastId {
  private static int id;

  public static int getId() {
    return id;
  }

  public static void setId(int id) {
    CastId.id = id;
  }
}
