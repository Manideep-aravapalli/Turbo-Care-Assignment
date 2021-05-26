package com.example.turbocareassignment.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.turbocareassignment.R;
import com.example.turbocareassignment.model.adapter.AddDataIntoLocalDbAdapter;
import com.example.turbocareassignment.model.dataModel.GetVehicleDetails;
import com.example.turbocareassignment.storage.SQLiteHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton addNewVehicles;
    private RecyclerView addDataIntoLocalDbRecycleView;
    private SQLiteHelper dbHelper;
    private AddDataIntoLocalDbAdapter addDataIntoLocalDbAdapter;
    private ArrayList<GetVehicleDetails> getVehicleDetails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        onClickListener();


    }

    @SuppressLint("QueryPermissionsNeeded")
    private void onClickListener() {
        addNewVehicles.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, VehicleNumberActivity.class);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        });
    }

    private void initView() {
        dbHelper = new SQLiteHelper(this);
        addNewVehicles = findViewById(R.id.add_vehicle_info);
        addDataIntoLocalDbRecycleView = findViewById(R.id.vehicle_recycleView);

        getVehicleDetails.clear();
        getVehicleDetails = (ArrayList<GetVehicleDetails>) dbHelper.getAllVehicleDetails();
        if (getVehicleDetails.size() > 0) {
            addDataIntoLocalDbAdapter = new AddDataIntoLocalDbAdapter(this, getVehicleDetails);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
            addDataIntoLocalDbRecycleView.setLayoutManager(mLayoutManager);
            addDataIntoLocalDbRecycleView.setAdapter(addDataIntoLocalDbAdapter);
            addDataIntoLocalDbRecycleView.scrollToPosition(0);
            addDataIntoLocalDbAdapter.notifyDataSetChanged();
        }

    }

    @SuppressLint("QueryPermissionsNeeded")
    public void allVehicleData(String vehicleNumber) {
        Intent intent = new Intent(MainActivity.this, VehicleProfileActivity.class);
        intent.putExtra("vehicleNumber", vehicleNumber);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}