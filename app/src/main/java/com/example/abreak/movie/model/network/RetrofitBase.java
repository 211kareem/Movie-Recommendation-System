package com.example.abreak.movie.model.network;

import com.example.abreak.BuildConfig;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.abreak.movie.utility.Constants.BASE_URL;

public class RetrofitBase {
  private static ApplicationApi api;

  public static ApplicationApi getApi() {
    api = new RetrofitBase().initRetrofit();
    return api;
  }

  private static ApplicationApi initRetrofit() {
    Retrofit retrofitInstance = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(getClient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
        .build();
    return retrofitInstance.create(ApplicationApi.class);
  }

  private static OkHttpClient getClient() {
    return new OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .addInterceptor(logging())
        .build();
  }

  private static HttpLoggingInterceptor logging() {
    return new HttpLoggingInterceptor()
        .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
            : HttpLoggingInterceptor.Level.NONE);
  }
}
