package com.example.abreak.movie.model.bus;

public class IdBus {
 private static int id;
 private static String path;
 private static String name;

  public IdBus() {
  }

  public static int getId() {
    return id;
  }

  public static void setId(int id) {
    IdBus.id = id;
  }

  public static String getPath() {
    return path;
  }

  public static void setPath(String path) {
    IdBus.path = path;
  }

  public static String getName() {
    return name;
  }

  public static void setName(String name) {
    IdBus.name = name;
  }
}
