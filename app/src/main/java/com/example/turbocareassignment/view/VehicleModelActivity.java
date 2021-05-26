package com.example.turbocareassignment.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.turbocareassignment.R;
import com.example.turbocareassignment.controller.VehicleModelController;
import com.example.turbocareassignment.model.adapter.VehicleModelAdapter;
import com.example.turbocareassignment.storage.SharedPrefManager;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import static com.example.turbocareassignment.util.ProgressCaller.showProgress;

public class VehicleModelActivity extends AppCompatActivity implements VehicleModelController.VehicleModelInterface {

    private String vehicleNumber;
    private String vehicleType;
    private String vehicleCompany;
    private MaterialToolbar materialToolbar;
    private RecyclerView recyclerView;
    private VehicleModelController vehicleModelController;
    private ConstraintLayout constraintLayout;
    private VehicleModelAdapter vehicleModelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_model);


        initView();
        onClickListener();
        Intent intent = getIntent();
        vehicleModelController = new VehicleModelController(VehicleModelActivity.this);
        if (intent != null) {
            vehicleNumber = intent.getStringExtra("vehicleNumber");
            vehicleType = intent.getStringExtra("vehicleType");
            vehicleCompany = intent.getStringExtra("CompanyName");
        }

        if (vehicleType.equals("Bike")) {
            showProgress(this);
            vehicleModelController.vehicleModels(getApplicationContext(), "2w", vehicleCompany);
        } else {
            showProgress(this);
            vehicleModelController.vehicleModels(getApplicationContext(), "4w", vehicleCompany);
        }
    }

    private void onClickListener() {
        materialToolbar.setNavigationOnClickListener(view -> onBackPressed());
    }


    private void initView() {
        materialToolbar = findViewById(R.id.toolbar_VehicleModelActivity);
        recyclerView = findViewById(R.id.vehicle_model_selection_recycleView);
        constraintLayout = findViewById(R.id.model_constraint_layout);
    }

    @Override
    public void onSuccess(List<String> modelNames) {
        if (modelNames.size() > 0) {
            vehicleModelAdapter = new VehicleModelAdapter(this, modelNames);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(vehicleModelAdapter);
            recyclerView.scrollToPosition(0);
            vehicleModelAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure(String message) {
        Snackbar.make(constraintLayout, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @SuppressLint("QueryPermissionsNeeded")
    public void selectVehicleModel(String model) {
        SharedPrefManager.getInstance(this).saveVehicleModel(model);
        Intent intent = new Intent(VehicleModelActivity.this, VehicleFuelTypeActivity.class);
        intent.putExtra("CompanyName", vehicleCompany);
        intent.putExtra("vehicleType", vehicleType);
        intent.putExtra("vehicleNumber", vehicleNumber);
        intent.putExtra("vehicleModel", model);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}