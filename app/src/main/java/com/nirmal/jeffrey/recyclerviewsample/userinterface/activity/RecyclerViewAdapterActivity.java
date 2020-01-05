package com.nirmal.jeffrey.recyclerviewsample.userinterface.activity;

import static com.nirmal.jeffrey.recyclerviewsample.util.AndroidFlavorsUtil.FLAVOR_INFO_DIALOG_FRAGMENT_TAG;
import static com.nirmal.jeffrey.recyclerviewsample.util.AndroidFlavorsUtil.createAndroidFlavorList;

import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.nirmal.jeffrey.recyclerviewsample.R;
import com.nirmal.jeffrey.recyclerviewsample.adapter.AndroidFlavorRecyclerViewAdapter;
import com.nirmal.jeffrey.recyclerviewsample.databinding.ActivityRecyclerViewBinding;
import com.nirmal.jeffrey.recyclerviewsample.model.AndroidFlavor;
import com.nirmal.jeffrey.recyclerviewsample.userinterface.dialog.FlavorInfoDialogFragment;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterActivity extends AppCompatActivity implements
    AndroidFlavorRecyclerViewAdapter.AndroidFlavorClickListener {

  private List<AndroidFlavor> flavorList;
  private AndroidFlavorRecyclerViewAdapter adapter;

  private ItemTouchHelper.SimpleCallback itemTouchHelperSimpleCallBack =
      new SimpleCallback(ItemTouchHelper.ACTION_STATE_IDLE,
          ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull ViewHolder viewHolder,
            @NonNull ViewHolder target) {
          return false;
        }

        @Override
        public void onSwiped(@NonNull ViewHolder viewHolder, int direction) {
          int position = viewHolder.getAdapterPosition();
          if (position != RecyclerView.NO_POSITION && flavorList != null && adapter != null) {
           flavorList.remove(position);
           adapter.notifyItemRemoved(position);
          }


        }
      };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityRecyclerViewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_recycler_view);
    setTitle(RecyclerViewAdapterActivity.class.getSimpleName());
    // If the size of each recycler view list item layout is fixed, even when the data is changed,
    // you must call this method
    binding.recyclerView.setHasFixedSize(true);
    binding.recyclerView
        .setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    adapter = new AndroidFlavorRecyclerViewAdapter(this);
    binding.recyclerView.setAdapter(adapter);
    flavorList = createAndroidFlavorList();
    setUpItemTouchHelperToRecyclerView(binding.recyclerView);
    adapter.setFlavorList(flavorList);

  }


  private void setUpItemTouchHelperToRecyclerView(RecyclerView recyclerView) {
    new ItemTouchHelper(itemTouchHelperSimpleCallBack).attachToRecyclerView(recyclerView);
  }



  @Override
  public void onAndroidFlavorCardClick(AndroidFlavor flavor) {
    Toast.makeText(this, flavor.getVersionName(), Toast.LENGTH_SHORT).show();
  }


  @Override
  public void onAndroidFlavorInfoClick(AndroidFlavor flavor) {
    FlavorInfoDialogFragment dialog = FlavorInfoDialogFragment.getInstance(flavor);
    dialog.show(getSupportFragmentManager(), FLAVOR_INFO_DIALOG_FRAGMENT_TAG);

  }
}
