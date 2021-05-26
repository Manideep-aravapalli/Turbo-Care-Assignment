package com.example.turbocareassignment.storage;

import android.content.Context;
import android.content.SharedPreferences;

import static com.example.turbocareassignment.util.AppConfig.VEHICLE_COMPANY;
import static com.example.turbocareassignment.util.AppConfig.VEHICLE_FUEL_TYPE;
import static com.example.turbocareassignment.util.AppConfig.VEHICLE_MODEL;
import static com.example.turbocareassignment.util.AppConfig.VEHICLE_NUMBER;
import static com.example.turbocareassignment.util.AppConfig.VEHICLE_SELECT_TRANSMISSION;
import static com.example.turbocareassignment.util.AppConfig.VEHICLE_TYPE;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "turboCareAssignment";
    private static SharedPrefManager mInstance;
    private Context mCtx;

    public SharedPrefManager(Context mCtx) {
        this.mCtx = mCtx;
    }

    public static synchronized SharedPrefManager getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(mCtx);
        }
        return mInstance;
    }

    public void saveVehicleNumber(String number) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(VEHICLE_NUMBER, number);
        editor.apply();
    }

    public void saveVehicleType(String type) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(VEHICLE_TYPE, type);
        editor.apply();
    }

    public void saveVehicleCompany(String company) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(VEHICLE_COMPANY, company);
        editor.apply();
    }

    public void saveVehicleModel(String model) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(VEHICLE_MODEL, model);
        editor.apply();
    }

    public void saveVehicleFuelType(String fuelType) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(VEHICLE_FUEL_TYPE, fuelType);
        editor.apply();
    }

    public void saveVehicleTransmission(String fuelType) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(VEHICLE_SELECT_TRANSMISSION, fuelType);
        editor.apply();
    }

    public String getVehicleNumber() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(VEHICLE_NUMBER, null);
    }

    public String getVehicleType() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(VEHICLE_TYPE, null);
    }

    public String getVehicleCompany() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(VEHICLE_COMPANY, null);
    }

    public String getVehicleModel() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(VEHICLE_MODEL, null);
    }

    public String getVehicleFuelType() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(VEHICLE_FUEL_TYPE, null);
    }
}
