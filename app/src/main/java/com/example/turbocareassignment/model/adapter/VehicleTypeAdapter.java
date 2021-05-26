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
import com.example.turbocareassignment.model.dataModel.VehicleType;
import com.example.turbocareassignment.view.VehicleSelectionActivity;

import java.util.ArrayList;

public class VehicleTypeAdapter extends RecyclerView.Adapter<VehicleTypeAdapter.ViewHolder> {
    private ArrayList<VehicleType> vehicleTypes;
    private Context context;

    public VehicleTypeAdapter(Context applicationContext, ArrayList<VehicleType> vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
        this.context = applicationContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.common_list_all_recycle_view, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(vehicleTypes.get(position));
    }

    @Override
    public int getItemCount() {
        return vehicleTypes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout selectVehicleType;
        private TextView typeName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            selectVehicleType = itemView.findViewById(R.id.vehicle_type_layout);
            typeName = itemView.findViewById(R.id.vehicle_types);
        }

        public void bindView(VehicleType vehicleType) {
            typeName.setText(vehicleType.getVehicleType());

            selectVehicleType.setOnClickListener(view -> {
                if (context instanceof VehicleSelectionActivity) {
                    ((VehicleSelectionActivity) context).selectVehicleType(
                            vehicleType.getVehicleType());
                }
            });
        }
    }
}
