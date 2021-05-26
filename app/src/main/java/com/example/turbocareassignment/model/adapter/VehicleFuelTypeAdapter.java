package com.example.turbocareassignment.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.turbocareassignment.R;
import com.example.turbocareassignment.model.dataModel.VehicleFuelType;
import com.example.turbocareassignment.view.VehicleFuelTypeActivity;
import com.example.turbocareassignment.view.VehicleMakeActivity;

import java.util.ArrayList;

public class VehicleFuelTypeAdapter extends RecyclerView.Adapter<VehicleFuelTypeAdapter.ViewHolder> {
    private ArrayList<VehicleFuelType> vehicleFuelTypes;
    private Context context;

    public VehicleFuelTypeAdapter(Context vehicleFuelTypeActivity, ArrayList<VehicleFuelType> vehicleFuelTypes) {
        this.context = vehicleFuelTypeActivity;
        this.vehicleFuelTypes = vehicleFuelTypes;
    }

    @NonNull
    @Override
    public VehicleFuelTypeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.common_list_all_recycle_view, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleFuelTypeAdapter.ViewHolder holder, int position) {
        holder.bindView(vehicleFuelTypes.get(position));
    }

    @Override
    public int getItemCount() {
        return vehicleFuelTypes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout selectVehicleType;
        private TextView typeName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            selectVehicleType = itemView.findViewById(R.id.vehicle_type_layout);
            typeName = itemView.findViewById(R.id.vehicle_types);
        }

        public void bindView(VehicleFuelType vehicleFuelType) {
            typeName.setText(vehicleFuelType.getVehicleFuelType());

            selectVehicleType.setOnClickListener(view -> {
                if (context instanceof VehicleFuelTypeActivity) {
                    ((VehicleFuelTypeActivity) context).selectVehicleFuelType(
                            vehicleFuelType.getVehicleFuelType());
                }
            });

        }
    }
}
