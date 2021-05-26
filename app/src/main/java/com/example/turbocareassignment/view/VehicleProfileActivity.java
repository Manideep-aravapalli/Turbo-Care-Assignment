package com.example.turbocareassignment.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.turbocareassignment.R;
import com.example.turbocareassignment.storage.SQLiteHelper;

import java.util.List;

public class VehicleProfileActivity extends AppCompatActivity {

    private String vehicleNumber;
    private TextView headingTxt;
    private TextView numberTxt;
    private TextView makeTxt;
    private TextView modelTxt;
    private TextView fuelTypeTxt;
    private TextView transmissionTxt;
    private ImageView backPress;
    private SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_profile);

        Intent intent = getIntent();
        if (intent != null) {
            vehicleNumber = intent.getStringExtra("vehicleNumber");
        }
        initView();
        onClickListener();
    }

    @SuppressLint("QueryPermissionsNeeded")
    private void onClickListener() {
        backPress.setOnClickListener(view -> {
            Intent intent = new Intent(VehicleProfileActivity.this, MainActivity.class);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
                finish();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void initView() {
        dbHelper = new SQLiteHelper(this);
        headingTxt = findViewById(R.id.model_and_fulType_profile);
        numberTxt = findViewById(R.id.vehicle_number_profile);
        makeTxt = findViewById(R.id.make_profile);
        modelTxt = findViewById(R.id.model_profile);
        fuelTypeTxt = findViewById(R.id.fuel_type_profile);
        transmissionTxt = findViewById(R.id.transmission_profile);
        backPress = findViewById(R.id.back_press);

        List<String> strings = dbHelper.getVehicleDetailsById(vehicleNumber);

        try {
            headingTxt.setText(strings.get(3) + " " + strings.get(4));
            numberTxt.setText(strings.get(0));
            makeTxt.setText(strings.get(2));
            modelTxt.setText(strings.get(3));
            fuelTypeTxt.setText(strings.get(4));
            transmissionTxt.setText(strings.get(5));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}