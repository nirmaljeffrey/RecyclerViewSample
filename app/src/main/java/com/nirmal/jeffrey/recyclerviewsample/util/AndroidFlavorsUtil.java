package com.nirmal.jeffrey.recyclerviewsample.util;

import com.nirmal.jeffrey.recyclerviewsample.R;
import com.nirmal.jeffrey.recyclerviewsample.model.AndroidFlavor;
import java.util.ArrayList;
import java.util.List;

public final class AndroidFlavorsUtil {
  public static final String FLAVOR_INFO_DIALOG_FRAGMENT_TAG = "flavor_info_dialog";

  public static List<AndroidFlavor> createAndroidFlavorList() {
    ArrayList<AndroidFlavor> androidFlavorList = new ArrayList<>();
    androidFlavorList.add(new AndroidFlavor("Donut", "1.6", R.drawable.donut));
    androidFlavorList.add(new AndroidFlavor("Eclair", "2.0-2.1", R.drawable.eclair));
    androidFlavorList.add(new AndroidFlavor("Froyo", "2.2-2.2.3", R.drawable.froyo));
    androidFlavorList.add(new AndroidFlavor("GingerBread", "2.3-2.3.7", R.drawable.gingerbread));
    androidFlavorList.add(new AndroidFlavor("Honeycomb", "3.0-3.2.6", R.drawable.honeycomb));
    androidFlavorList
        .add(new AndroidFlavor("Ice Cream Sandwich", "4.0-4.0.4", R.drawable.icecream));
    androidFlavorList.add(new AndroidFlavor("Jelly Bean", "4.1-4.3.1", R.drawable.jellybean));
    androidFlavorList.add(new AndroidFlavor("KitKat", "4.4-4.4.4", R.drawable.kitkat));
    androidFlavorList.add(new AndroidFlavor("Lollipop", "5.0-5.1.1", R.drawable.lollipop));
    androidFlavorList.add(new AndroidFlavor("Marshmallow", "6.0-6.0.1", R.drawable.marshmallow));
    return androidFlavorList;
  }
}
