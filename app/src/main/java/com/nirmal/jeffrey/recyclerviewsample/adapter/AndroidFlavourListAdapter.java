package com.nirmal.jeffrey.recyclerviewsample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.nirmal.jeffrey.recyclerviewsample.model.AndroidFlavor;
import com.nirmal.jeffrey.recyclerviewsample.R;
import com.nirmal.jeffrey.recyclerviewsample.adapter.AndroidFlavourListAdapter.AndroidFlavorViewHolder;
import com.nirmal.jeffrey.recyclerviewsample.databinding.AndroidFlavorListItemBinding;

public class AndroidFlavourListAdapter extends ListAdapter<AndroidFlavor, AndroidFlavorViewHolder> {
  private AndroidFlavorClickListener flavorClickListener;
  private static DiffUtil.ItemCallback<AndroidFlavor> DIFF_CALLBACK = new ItemCallback<AndroidFlavor>() {

    @Override
    public boolean areItemsTheSame(@NonNull AndroidFlavor oldItem, @NonNull AndroidFlavor newItem) {
      return oldItem.getVersionNumber().equals(newItem.getVersionNumber()) ;
    }

    @Override
    public boolean areContentsTheSame(@NonNull AndroidFlavor oldItem,
        @NonNull AndroidFlavor newItem) {
      return oldItem.equals(newItem);
    }
  };

  public AndroidFlavourListAdapter(AndroidFlavorClickListener flavorClickListener) {
    super(DIFF_CALLBACK);
    this.flavorClickListener = flavorClickListener;
  }

  @NonNull
  @Override
  public AndroidFlavorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    AndroidFlavorListItemBinding binding = DataBindingUtil
        .inflate(LayoutInflater.from(parent.getContext()), R.layout.android_flavor_list_item,
            parent, false);
    return new AndroidFlavorViewHolder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull AndroidFlavorViewHolder holder, int position) {
    holder.bind(getItem(position));
  }

  public interface AndroidFlavorClickListener {
    void onAndroidFlavorCardClick(AndroidFlavor flavor);
    void onAndroidFlavorInfoClick(AndroidFlavor flavor);
  }

  public class AndroidFlavorViewHolder extends ViewHolder implements View.OnClickListener {

    private AndroidFlavorListItemBinding binding;

    public AndroidFlavorViewHolder(@NonNull AndroidFlavorListItemBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
      binding.flavorInfo.setOnClickListener(this);
      binding.androidFlavorListItem.setOnClickListener(this);
    }

    private void bind(AndroidFlavor flavor) {
      binding.setFlavor(flavor);
      binding.executePendingBindings();
    }

    @Override
    public void onClick(View view) {
      int position = getAdapterPosition();

      if (position != RecyclerView.NO_POSITION) {
        AndroidFlavor flavor = getItem(position);

        switch (view.getId()) {
          case R.id.android_flavor_list_item: {
            flavorClickListener.onAndroidFlavorCardClick(flavor);
            break;
          }
          case R.id.flavor_info: {
            flavorClickListener.onAndroidFlavorInfoClick(flavor);
            break;
          }
        }
      }
    }


  }
}
