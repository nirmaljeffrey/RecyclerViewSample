package com.nirmal.jeffrey.recyclerviewsample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.nirmal.jeffrey.recyclerviewsample.model.AndroidFlavor;
import com.nirmal.jeffrey.recyclerviewsample.R;
import com.nirmal.jeffrey.recyclerviewsample.adapter.AndroidFlavorRecyclerViewAdapter.AndroidFlavorViewHolder;
import com.nirmal.jeffrey.recyclerviewsample.databinding.AndroidFlavorListItemBinding;
import java.util.List;

public class AndroidFlavorRecyclerViewAdapter extends
    RecyclerView.Adapter<AndroidFlavorViewHolder> {

  private AndroidFlavorClickListener flavorClickListener;
  private List<AndroidFlavor> flavorList;

  public AndroidFlavorRecyclerViewAdapter(AndroidFlavorClickListener flavorClickListener) {
    this.flavorClickListener = flavorClickListener;
  }

  public void setFlavorList(List<AndroidFlavor> flavorList) {
    this.flavorList = flavorList;
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public AndroidFlavorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    AndroidFlavorListItemBinding binding = DataBindingUtil
        .inflate(LayoutInflater.from(parent.getContext()), R.layout.android_flavor_list_item,
            parent, false);
    return new AndroidFlavorViewHolder(binding);
  }

  /*
   * Never set onClickListeners for views in onBindViewHolder,
   * set onClickListeners either in onCreateViewHolder or constructor of viewHolder
   */
  @Override
  public void onBindViewHolder(@NonNull AndroidFlavorViewHolder holder, int position) {
    holder.bind(flavorList.get(position));
  }

  @Override
  public int getItemCount() {
    return (flavorList == null) ? 0 : flavorList.size();
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

      if(position != RecyclerView.NO_POSITION) {
        AndroidFlavor flavor = flavorList.get(position);

        switch (view.getId()) {
          case R.id.android_flavor_list_item: {
            flavorClickListener.onAndroidFlavorCardClick(flavor);
            break;
          }
          case R.id.flavor_info:{
             flavorClickListener.onAndroidFlavorInfoClick(flavor);
             break;
          }
        }
      }
    }
  }



}
