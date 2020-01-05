package com.nirmal.jeffrey.recyclerviewsample.userinterface.dialog;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import com.nirmal.jeffrey.recyclerviewsample.R;
import com.nirmal.jeffrey.recyclerviewsample.databinding.DialogAndroidFlavorBinding;
import com.nirmal.jeffrey.recyclerviewsample.model.AndroidFlavor;

public class FlavorInfoDialogFragment extends DialogFragment {

  private AppCompatActivity activityContext;
  private static final String FLAVOR_BUNDLE_KEY = "flavour";
  private AndroidFlavor flavor;

  public static FlavorInfoDialogFragment getInstance(AndroidFlavor flavor) {
    FlavorInfoDialogFragment fragment = new FlavorInfoDialogFragment();
    Bundle args = new Bundle();
    args.putParcelable(FLAVOR_BUNDLE_KEY, flavor);
    fragment.setArguments(args);
    return fragment;
  }


  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    try {
      activityContext = (AppCompatActivity) context;
    }catch (ClassCastException exception){
      throw new IllegalArgumentException("Activity must extend AppCompatActivity");
    }

  }

  @Override
  public void onDetach() {
    super.onDetach();
    activityContext = null;
  }



    @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    if(getArguments() != null) {
      AndroidFlavor flavor = getArguments().getParcelable(FLAVOR_BUNDLE_KEY);
      AlertDialog.Builder dialogBuilder = new Builder(activityContext);
      DialogAndroidFlavorBinding binding = DataBindingUtil
          .inflate(activityContext.getLayoutInflater(), R.layout.dialog_android_flavor, null, false);
      binding.setFlavor(flavor);
      dialogBuilder.setView(binding.getRoot());
      dialogBuilder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
      return dialogBuilder.create();
    }
      return super.onCreateDialog(savedInstanceState);
  }


}
