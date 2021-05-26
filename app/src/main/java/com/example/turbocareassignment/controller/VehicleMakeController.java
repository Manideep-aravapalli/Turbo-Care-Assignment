package com.example.turbocareassignment.controller;

import android.content.Context;

import com.example.turbocareassignment.model.Connectivity.APIService;
import com.example.turbocareassignment.model.Connectivity.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.turbocareassignment.util.ProgressCaller.hideProgress;

public class VehicleMakeController {
    private VehicleMakeInterface vehicleMakeInterface;
    private APIService apiService;

    public VehicleMakeController(VehicleMakeInterface vehicleMakeInterface) {
        this.vehicleMakeInterface = vehicleMakeInterface;
    }

    /**
     * Based on  Vehicle Type show companies
     *
     * @param context
     * @param type (2w & 4W)
     */
    public void vehicleMakeSelection(Context context, String type) {
        apiService = RetrofitClient.getClient(context).create(APIService.class);
        apiService.twoAndFourWheelerApi(type).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                try {
                    hideProgress();
                    if (response.body().size() > 0) {
                        vehicleMakeInterface.onSuccess(response.body());
                    } else {
                        vehicleMakeInterface.onFailure("No Data Found");
                    }
                } catch (Exception e) {
                    hideProgress();
                    vehicleMakeInterface.onFailure(e.getMessage());

                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                hideProgress();
                vehicleMakeInterface.onFailure(t.getMessage());

            }
        });
    }

    public interface VehicleMakeInterface {
        void onSuccess(List<String> vehicleCompanies);

        void onFailure(String message);
    }
}
