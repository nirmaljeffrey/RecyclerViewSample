package com.nirmal.jeffrey.recyclerviewsample.userinterface.activity;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import com.nirmal.jeffrey.recyclerviewsample.R;
import com.nirmal.jeffrey.recyclerviewsample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    binding.recyclerViewAdapterButton.setOnClickListener(this);
    binding.listAdapterButton.setOnClickListener(this);
    binding.pagedListAdapterButton.setOnClickListener(this);

  }

  @Override
  public void onClick(View view) {
    Intent intent = null;
    switch (view.getId()){
      case R.id.recycler_view_adapter_button:{
        intent = new Intent(this, RecyclerViewAdapterActivity.class);
        break;
      }
      case R.id.list_adapter_button:{
        intent = new Intent(this, ListAdapterActivity.class);
        break;
      }
      case R.id.paged_list_adapter_button:{
        break;
      }
    }
    if(intent != null) {
      startActivity(intent);
    }
  }
}
