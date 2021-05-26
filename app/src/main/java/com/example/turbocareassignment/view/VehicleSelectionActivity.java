package com.example.turbocareassignment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.turbocareassignment.R;
import com.example.turbocareassignment.model.adapter.VehicleTypeAdapter;
import com.example.turbocareassignment.model.dataModel.VehicleType;
import com.example.turbocareassignment.storage.SharedPrefManager;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

import static com.example.turbocareassignment.util.AppConfig.BIKE;
import static com.example.turbocareassignment.util.AppConfig.CAR;

public class VehicleSelectionActivity extends AppCompatActivity {

    private MaterialToolbar materialToolbar;
    private RecyclerView recyclerView;
    private ArrayList<VehicleType> vehicleTypes = new ArrayList<>();
    private VehicleTypeAdapter vehicleTypeAdapter;
    private String vehicleNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_selection);

        Intent intent = getIntent();
        if (intent != null) {
            vehicleNumber = intent.getStringExtra("vehicleNumber");
        }

        initView();
        onClickListener();
    }

    private void onClickListener() {
        materialToolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

    private void initView() {
        materialToolbar = findViewById(R.id.toolbar_VehicleSelectionActivity);
        recyclerView = findViewById(R.id.vehicle_type_selection_recycleView);

        vehicleTypes.clear();
        vehicleTypes.add(new VehicleType(BIKE));
        vehicleTypes.add(new VehicleType(CAR));

        vehicleTypeAdapter = new VehicleTypeAdapter(this, vehicleTypes);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(vehicleTypeAdapter);
        recyclerView.scrollToPosition(0);
        vehicleTypeAdapter.notifyDataSetChanged();

    }

    @SuppressLint("QueryPermissionsNeeded")
    public void selectVehicleType(String vehicleType) {
        SharedPrefManager.getInstance(getApplicationContext()).saveVehicleType(vehicleType);
        Intent intent = new Intent(VehicleSelectionActivity.this, VehicleMakeActivity.class);
        intent.putExtra("vehicleType", vehicleType);
        intent.putExtra("vehicleNumber", vehicleNumber);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}