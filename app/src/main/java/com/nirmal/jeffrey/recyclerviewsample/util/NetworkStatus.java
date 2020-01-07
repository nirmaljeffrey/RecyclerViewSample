package com.nirmal.jeffrey.recyclerviewsample.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NetworkStatus {

  @NonNull
  public final Status status;
  @Nullable
  public final String message;

  public NetworkStatus(@NonNull Status status,
      @Nullable String message) {
    this.status = status;
    this.message = message;
  }

  public static NetworkStatus success() {
    return new NetworkStatus(Status.SUCCESS, null);
  }

  public static  NetworkStatus error(String msg) {
    return new NetworkStatus(Status.ERROR, msg);
  }

  public static  NetworkStatus loading() {
    return new NetworkStatus(Status.LOADING, null);
  }
  public static  NetworkStatus empty() {
    return new NetworkStatus(Status.EMPTY, null);
  }


}