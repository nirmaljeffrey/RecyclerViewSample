package com.nirmal.jeffrey.recyclerviewsample.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {

  private static final String API_KEY_PARAMETER = "api_key";
  private static final String API_KEY_VALUE = "ff8d4e21a42ffa17f94a3a5280a30a9b";
  private static final String BASE_URL = "https://api.themoviedb.org/3/";
  private static NetworkClient instance;

  private Retrofit retrofit;

  private NetworkClient() {
    Builder client = new Builder().addInterceptor(chain -> {
      Request originalRequest = chain.request();
      HttpUrl originalUrl = originalRequest.url();
      HttpUrl modifiedUrl = originalUrl.newBuilder()
          .addQueryParameter(API_KEY_PARAMETER, API_KEY_VALUE)
          .build();
      Request.Builder builder = originalRequest.newBuilder().url(modifiedUrl);
      return chain.proceed(builder.build());
    }).addNetworkInterceptor(new StethoInterceptor());
    retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client.build())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  public static synchronized NetworkClient getInstance() {
    if (instance == null) {
      instance = new NetworkClient();
    }
    return instance;
  }

  public MovieApi getMovieApi() {
    return retrofit.create(MovieApi.class);
  }

}
