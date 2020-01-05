package com.nirmal.jeffrey.recyclerviewsample.model;


import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AndroidFlavor implements Parcelable {

  public static final Creator<AndroidFlavor> CREATOR = new Creator<AndroidFlavor>() {
    @Override
    public AndroidFlavor createFromParcel(Parcel in) {
      return new AndroidFlavor(in);
    }

    @Override
    public AndroidFlavor[] newArray(int size) {
      return new AndroidFlavor[size];
    }
  };
  private String mVersionName;
  private String mVersionNumber;
  private int mImageResourceId;


  public AndroidFlavor(String vName, String vNumber, int imageResourceId) {
    mVersionName = vName;
    mVersionNumber = vNumber;
    mImageResourceId = imageResourceId;
  }

  protected AndroidFlavor(Parcel in) {
    mVersionName = in.readString();
    mVersionNumber = in.readString();
    mImageResourceId = in.readInt();
  }

  public String getVersionName() {
    return mVersionName;
  }


  public String getVersionNumber() {
    return mVersionNumber;
  }


  public int getImageResourceId() {
    return mImageResourceId;
  }


  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(mVersionName);
    dest.writeString(mVersionNumber);
    dest.writeInt(mImageResourceId);
  }

  @NonNull
  @Override
  public String toString() {
    return "AndroidFlavor{" +
        "mVersionName='" + mVersionName + '\'' +
        ", mVersionNumber='" + mVersionNumber + '\'' +
        ", mImageResourceId=" + mImageResourceId +
        '}';
  }

  @Override
  public boolean equals(@Nullable Object obj) {
    if(!(obj instanceof AndroidFlavor)){
      return false;
    }
    AndroidFlavor flavor = (AndroidFlavor) obj;
    return mVersionNumber.equals(flavor.getVersionNumber()) &&
        mVersionName.equals(flavor.getVersionName()) &&
        mImageResourceId == flavor.getImageResourceId();
  }

}