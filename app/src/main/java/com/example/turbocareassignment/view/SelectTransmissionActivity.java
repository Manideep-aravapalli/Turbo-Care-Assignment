package com.example.turbocareassignment.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.turbocareassignment.R;
import com.example.turbocareassignment.model.adapter.VehicleTransmissionAdapter;
import com.example.turbocareassignment.model.dataModel.VehicleSelectionTransmission;
import com.example.turbocareassignment.storage.SQLiteHelper;
import com.example.turbocareassignment.storage.SharedPrefManager;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class SelectTransmissionActivity extends AppCompatActivity {

    private MaterialToolbar materialToolbar;
    private RecyclerView recyclerView;
    private ArrayList<VehicleSelectionTransmission> vehicleSelectionTransmissions = new ArrayList<>();
    private VehicleTransmissionAdapter vehicleTransmissionAdapter;
    private SQLiteHelper dbHelper;
    private String vehicleNumber;
    private String vehicleType;
    private String vehicleCompany;
    private String vehicleModel;
    private String vehicleFuelType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_transmission);
        initView();
        onClickListener();
        sharedPrefData();
    }

    private void onClickListener() {
        materialToolbar.setNavigationOnClickListener(view -> onBackPressed());
    }


    private void initView() {
        dbHelper = new SQLiteHelper(this);
        materialToolbar = findViewById(R.id.toolbar_SelectTransmissionActivity);
        recyclerView = findViewById(R.id.vehicle_transmission_type_selection_recycleView);

        vehicleSelectionTransmissions.clear();
        vehicleSelectionTransmissions.add(new VehicleSelectionTransmission(getResources().getString(R.string.manual)));
        vehicleSelectionTransmissions.add(new VehicleSelectionTransmission(getResources().getString(R.string.automatic)));

        vehicleTransmissionAdapter = new VehicleTransmissionAdapter(this, vehicleSelectionTransmissions);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(vehicleTransmissionAdapter);
        recyclerView.scrollToPosition(0);
        vehicleTransmissionAdapter.notifyDataSetChanged();
    }

    private void sharedPrefData() {
        vehicleNumber = SharedPrefManager.getInstance(this).getVehicleNumber();
        vehicleType = SharedPrefManager.getInstance(this).getVehicleType();
        vehicleCompany = SharedPrefManager.getInstance(this).getVehicleCompany();
        vehicleModel = SharedPrefManager.getInstance(this).getVehicleModel();
        vehicleFuelType = SharedPrefManager.getInstance(this).getVehicleFuelType();
    }

    @SuppressLint("QueryPermissionsNeeded")
    public void selectSelectTransmission(String vehicleTransmission) {
        SharedPrefManager.getInstance(this).saveVehicleTransmission(vehicleTransmission);
        dbHelper.insertData(vehicleNumber, vehicleType, vehicleCompany, vehicleModel, vehicleFuelType, vehicleTransmission);
        Intent intent = new Intent(SelectTransmissionActivity.this, VehicleProfileActivity.class);
        intent.putExtra("vehicleNumber", vehicleNumber);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}