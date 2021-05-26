package com.example.turbocareassignment.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.turbocareassignment.R;
import com.example.turbocareassignment.storage.SQLiteHelper;
import com.example.turbocareassignment.storage.SharedPrefManager;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class VehicleNumberActivity extends AppCompatActivity {

    private MaterialToolbar materialToolbar;
    private EditText vehicleNumber;
    private FloatingActionButton nextActivity;
    private SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_number);

        initView();
        onClickListener();
    }

    @SuppressLint("QueryPermissionsNeeded")
    private void onClickListener() {
        dbHelper = new SQLiteHelper(this);
        materialToolbar.setNavigationOnClickListener(view -> onBackPressed());
        nextActivity.setOnClickListener(view -> {
            String number = vehicleNumber.getText().toString().trim();
            String dataBaseVehicleNumber = dbHelper.getVehicleId(number);
            if (number.isEmpty()) {
                Snackbar.make(view, "Please Enter Vehicle Number.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            } else {
                if (dataBaseVehicleNumber != null) {
                    if (!dataBaseVehicleNumber.equalsIgnoreCase(number)) {
                        SharedPrefManager.getInstance(getApplicationContext()).saveVehicleNumber(number);
                        Intent intent = new Intent(VehicleNumberActivity.this, VehicleSelectionActivity.class);
                        intent.putExtra("vehicleNumber", number);
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent);
                        }
                    } else {
                        Snackbar.make(view, "This vehicle number already added.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                } else {
                    Snackbar.make(view, "This --- vehicle number already added.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }


    private void initView() {
        materialToolbar = findViewById(R.id.toolbar_VehicleNumberActivity);
        nextActivity = findViewById(R.id.next_screen);
        vehicleNumber = findViewById(R.id.vehicle_number_edit_text);
    }
}