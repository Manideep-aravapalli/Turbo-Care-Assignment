package com.example.turbocareassignment.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.turbocareassignment.R;
import com.example.turbocareassignment.model.adapter.VehicleFuelTypeAdapter;
import com.example.turbocareassignment.model.dataModel.VehicleFuelType;
import com.example.turbocareassignment.storage.SharedPrefManager;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class VehicleFuelTypeActivity extends AppCompatActivity {

    private MaterialToolbar materialToolbar;
    private RecyclerView recyclerView;
    private ArrayList<VehicleFuelType> vehicleFuelTypes = new ArrayList<>();
    private VehicleFuelTypeAdapter vehicleFuelTypeAdapter;
    private String vehicleNumber;
    private String vehicleType;
    private String vehicleCompany;
    private String vehicleModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_fuel_type);
        initView();
        onClickListener();
        Intent intent = getIntent();
        if (intent != null) {
            vehicleNumber = intent.getStringExtra("vehicleNumber");
            vehicleType = intent.getStringExtra("vehicleType");
            vehicleCompany = intent.getStringExtra("CompanyName");
            vehicleModel = intent.getStringExtra("vehicleModel");
        }
    }

    private void onClickListener() {
        materialToolbar.setNavigationOnClickListener(view -> onBackPressed());
    }


    private void initView() {
        materialToolbar = findViewById(R.id.toolbar_VehicleFuelTypeActivity);
        recyclerView = findViewById(R.id.vehicle_fuel_type_selection_recycleView);

        vehicleFuelTypes.clear();
        vehicleFuelTypes.add(new VehicleFuelType("Petrol"));
        vehicleFuelTypes.add(new VehicleFuelType("Diesel"));
        vehicleFuelTypes.add(new VehicleFuelType("CNG"));
        vehicleFuelTypes.add(new VehicleFuelType("Petrol + CNG"));
        vehicleFuelTypes.add(new VehicleFuelType("Electric"));
        vehicleFuelTypes.add(new VehicleFuelType("Hybrid"));

        vehicleFuelTypeAdapter = new VehicleFuelTypeAdapter(this, vehicleFuelTypes);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(vehicleFuelTypeAdapter);
        recyclerView.scrollToPosition(0);
        vehicleFuelTypeAdapter.notifyDataSetChanged();
    }

    @SuppressLint("QueryPermissionsNeeded")
    public void selectVehicleFuelType(String vehicleFuelType) {
        SharedPrefManager.getInstance(this).saveVehicleFuelType(vehicleFuelType);
        Intent intent = new Intent(VehicleFuelTypeActivity.this, SelectTransmissionActivity.class);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}