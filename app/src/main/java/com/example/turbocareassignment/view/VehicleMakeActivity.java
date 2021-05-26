package com.example.turbocareassignment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.turbocareassignment.R;
import com.example.turbocareassignment.controller.VehicleMakeController;
import com.example.turbocareassignment.model.adapter.VehicleMakeAdapter;
import com.example.turbocareassignment.storage.SharedPrefManager;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import static com.example.turbocareassignment.util.AppConfig.BIKE;
import static com.example.turbocareassignment.util.AppConfig.FOUR_WELLER;
import static com.example.turbocareassignment.util.AppConfig.TWO_WELLER;
import static com.example.turbocareassignment.util.ProgressCaller.showProgress;

public class VehicleMakeActivity extends AppCompatActivity implements VehicleMakeController.VehicleMakeInterface {

    private String vehicleNumber;
    private String vehicleType;
    private MaterialToolbar materialToolbar;
    private RecyclerView recyclerView;
    private VehicleMakeController vehicleMakeController;
    private ConstraintLayout constraintLayout;
    private VehicleMakeAdapter vehicleMakeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_make);

        initView();
        onClickListener();
        vehicleMakeController = new VehicleMakeController(VehicleMakeActivity.this);
        Intent intent = getIntent();
        if (intent != null) {
            vehicleNumber = intent.getStringExtra("vehicleNumber");
            vehicleType = intent.getStringExtra("vehicleType");
        }

        if (vehicleType.equals(BIKE)) {
            showProgress(this);
            vehicleMakeController.vehicleMakeSelection(getApplicationContext(), TWO_WELLER);
        } else {
            showProgress(this);
            vehicleMakeController.vehicleMakeSelection(getApplicationContext(), FOUR_WELLER);
        }

    }

    private void onClickListener() {
        materialToolbar.setNavigationOnClickListener(view -> onBackPressed());
    }


    private void initView() {
        materialToolbar = findViewById(R.id.toolbar_VehicleMakeActivity);
        recyclerView = findViewById(R.id.vehicle_make_selection_recycleView);
        constraintLayout = findViewById(R.id.constrationLayout);
    }

    @Override
    public void onSuccess(List<String> vehicleCompanies) {
        if (vehicleCompanies.size() > 0) {
            vehicleMakeAdapter = new VehicleMakeAdapter(this, vehicleCompanies);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(vehicleMakeAdapter);
            recyclerView.scrollToPosition(0);
            vehicleMakeAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure(String message) {
        Snackbar.make(constraintLayout, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @SuppressLint("QueryPermissionsNeeded")
    public void selectVehicleCompany(String companyName) {
        SharedPrefManager.getInstance(this).saveVehicleCompany(companyName);
        Intent intent = new Intent(VehicleMakeActivity.this, VehicleModelActivity.class);
        intent.putExtra("CompanyName", companyName);
        intent.putExtra("vehicleType", vehicleType);
        intent.putExtra("vehicleNumber", vehicleNumber);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}