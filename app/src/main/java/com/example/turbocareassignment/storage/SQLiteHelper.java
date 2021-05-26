package com.example.turbocareassignment.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.turbocareassignment.model.dataModel.GetVehicleDetails;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "TurboCareAssignment";
    private static final int DB_VERSION = 1;

    private static final String createTable = "create table VehicleDetails(id integer primary key autoincrement," +
            "vehicleNumber text, vehicleType text,vehicleCompany text,vehicleModel text,vehicleFuelType text," +
            "vehicleTransmission text)";
    private static final String dropTable = "drop table if exists VehicelDetails";


    public SQLiteHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(dropTable);
        onCreate(db);
    }

    public void insertData(String vehicleNumber, String vehicleType, String vehicleCompany, String vehicleModel,
                           String vehicleFuelType, String vehicleTransmission) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("vehicleNumber", vehicleNumber);
        values.put("vehicleType", vehicleType);
        values.put("vehicleCompany", vehicleCompany);
        values.put("vehicleModel", vehicleModel);
        values.put("vehicleFuelType", vehicleFuelType);
        values.put("vehicleTransmission", vehicleTransmission);

        db.insert("VehicleDetails", null, values);
    }


    public List<String> getVehicleDetailsById(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String rawQuery = "select * from VehicleDetails where vehicleNumber='" + id + "'";
        Cursor cursor = db.rawQuery(rawQuery, null);

        List<String> listCities = new ArrayList<>();

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    try {

                        listCities.add(cursor.getString(cursor.getColumnIndex("vehicleNumber")));
                        listCities.add(cursor.getString(cursor.getColumnIndex("vehicleType")));
                        listCities.add(cursor.getString(cursor.getColumnIndex("vehicleCompany")));
                        listCities.add(cursor.getString(cursor.getColumnIndex("vehicleModel")));
                        listCities.add(cursor.getString(cursor.getColumnIndex("vehicleFuelType")));
                        listCities.add(cursor.getString(cursor.getColumnIndex("vehicleTransmission")));
                        cursor.moveToNext();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        assert cursor != null;
        cursor.close();
        return listCities;
    }

    public List<GetVehicleDetails> getAllVehicleDetails() {
        SQLiteDatabase db = this.getWritableDatabase();
        String rawQuery = "select * from VehicleDetails";
        Cursor cursor = db.rawQuery(rawQuery, null);

        List<GetVehicleDetails> listVehicleDetails = new ArrayList<>();

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    try {

                        listVehicleDetails.add(new GetVehicleDetails(cursor.getString(cursor.getColumnIndex("vehicleNumber")),
                                cursor.getString(cursor.getColumnIndex("vehicleCompany")),
                                cursor.getString(cursor.getColumnIndex("vehicleModel"))));
                        cursor.moveToNext();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        assert cursor != null;
        cursor.close();
        return listVehicleDetails;
    }

    public String getVehicleId(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select vehicleNumber from VehicleDetails where vehicleNumber='" + id + "'";
        Cursor cursor = db.rawQuery(query, null);
        String vehicleNumber = "";
        if (cursor != null) {
            while (cursor.moveToNext()) {
                vehicleNumber = cursor.getString(cursor.getColumnIndex("vehicleNumber"));
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return vehicleNumber;
    }
}
