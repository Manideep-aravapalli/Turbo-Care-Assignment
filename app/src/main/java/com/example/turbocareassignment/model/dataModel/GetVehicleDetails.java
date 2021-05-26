package com.example.turbocareassignment.model.dataModel;

public class GetVehicleDetails {

    private String vehicleNumber;
    private String vehicleCompany;
    private String vehicleModel;

    public GetVehicleDetails(String vehicleNumber, String vehicleCompany, String vehicleModel) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleCompany = vehicleCompany;
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleCompany() {
        return vehicleCompany;
    }

    public void setVehicleCompany(String vehicleCompany) {
        this.vehicleCompany = vehicleCompany;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }
}
