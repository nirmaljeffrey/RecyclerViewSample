package com.nirmal.jeffrey.recyclerviewsample.userinterface.activity;

import static com.nirmal.jeffrey.recyclerviewsample.util.AndroidFlavorsUtil.FLAVOR_INFO_DIALOG_FRAGMENT_TAG;
import static com.nirmal.jeffrey.recyclerviewsample.util.AndroidFlavorsUtil.createAndroidFlavorList;

import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.nirmal.jeffrey.recyclerviewsample.R;
import com.nirmal.jeffrey.recyclerviewsample.adapter.AndroidFlavourListAdapter;
import com.nirmal.jeffrey.recyclerviewsample.adapter.AndroidFlavourListAdapter.AndroidFlavorClickListener;
import com.nirmal.jeffrey.recyclerviewsample.databinding.ActivityListAdapterBinding;
import com.nirmal.jeffrey.recyclerviewsample.model.AndroidFlavor;
import com.nirmal.jeffrey.recyclerviewsample.userinterface.dialog.FlavorInfoDialogFragment;
import java.util.ArrayList;
import java.util.List;

public class ListAdapterActivity extends AppCompatActivity implements AndroidFlavorClickListener {
private AndroidFlavourListAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    ActivityListAdapterBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_list_adapter);
    setTitle(ListAdapterActivity.class.getSimpleName());
    // If the size of each recycler view list item layout is fixed, even when the data is changed,
    // you must call this method
    binding.recyclerView.setHasFixedSize(true);
    binding.recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    List<AndroidFlavor> flavorList = createAndroidFlavorList();
    setUpItemTouchHelperToRecyclerView(binding.recyclerView);
    adapter = new AndroidFlavourListAdapter(this);
    binding.recyclerView.setAdapter(adapter);
    adapter.submitList(flavorList);
  }

  private void setUpItemTouchHelperToRecyclerView(RecyclerView recyclerView) {
    new ItemTouchHelper(itemTouchHelperSimpleCallBack).attachToRecyclerView(recyclerView);
  }

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
          if (position != RecyclerView.NO_POSITION && adapter != null) {
                List<AndroidFlavor> androidFlavorList = new ArrayList<>(adapter.getCurrentList());
            androidFlavorList.remove(position);
            adapter.submitList(androidFlavorList);
          }
        }
      };

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
