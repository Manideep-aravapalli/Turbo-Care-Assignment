package com.example.turbocareassignment.controller;

import android.content.Context;

import com.example.turbocareassignment.model.Connectivity.APIService;
import com.example.turbocareassignment.model.Connectivity.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.turbocareassignment.util.ProgressCaller.hideProgress;

public class VehicleModelController {
    private VehicleModelInterface vehicleModelInterface;
    private APIService apiService;

    public VehicleModelController(VehicleModelInterface vehicleModelInterface) {
        this.vehicleModelInterface = vehicleModelInterface;
    }

    public void vehicleModels(Context context, String type, String company) {
        apiService = RetrofitClient.getClient(context).create(APIService.class);
        apiService.LIST_CALL(type, company).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                try {
                    hideProgress();
                    if (response.body().size() > 0) {
                        vehicleModelInterface.onSuccess(response.body());
                    } else {
                        vehicleModelInterface.onFailure("No Data Found");
                    }

                } catch (Exception e) {
                    hideProgress();
                    e.getMessage();
                    vehicleModelInterface.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                hideProgress();
                vehicleModelInterface.onFailure(t.getMessage());
            }
        });
    }

    public interface VehicleModelInterface {
        void onSuccess(List<String> body);

        void onFailure(String message);
    }
}
