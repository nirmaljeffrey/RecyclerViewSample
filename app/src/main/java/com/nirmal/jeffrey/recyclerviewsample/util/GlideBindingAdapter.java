package com.nirmal.jeffrey.recyclerviewsample.util;

import android.util.Log;
import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;

public class GlideBindingAdapter {
  private static final String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w185/";

  @BindingAdapter("imageUrl")
  public static void loadImage(ImageView view, String url) {
    Log.d( "loadImage: ",getMovieImageUrl(url));
    Glide.with(view.getContext()).load(getMovieImageUrl(url)).fitCenter().into(view);
  }

  public static String getMovieImageUrl(String imagePath) {
    return POSTER_BASE_URL + imagePath;
  }

}
