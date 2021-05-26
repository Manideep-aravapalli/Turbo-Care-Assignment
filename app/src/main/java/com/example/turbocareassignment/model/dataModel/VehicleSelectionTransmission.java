package com.example.turbocareassignment.model.dataModel;

public class VehicleSelectionTransmission {

    private String vehicleTransmission;

    public VehicleSelectionTransmission(String vehicleModelName) {
        this.vehicleTransmission = vehicleModelName;
    }

    public String getVehicleTransmission() {
        return vehicleTransmission;
    }

    public void setVehicleTransmission(String vehicleTransmission) {
        this.vehicleTransmission = vehicleTransmission;
    }
}
