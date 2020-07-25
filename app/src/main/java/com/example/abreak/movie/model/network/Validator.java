package com.example.abreak.movie.model.network;

import android.content.Context;
import retrofit2.Response;

public class Validator {
  private Services.TransformResponse2Java transformResponse2Java;
  Context context;

  public Validator(Context context, Services.TransformResponse2Java transformResponse2Java) {
    this.context = context;
    this.transformResponse2Java = transformResponse2Java;
  }

  public void transform2Java(Response<?> response) {
    Object model = response.body();
    transformResponse2Java.transfrom(model);
  }
}
