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
import com.example.turbocareassignment.view.VehicleMakeActivity;

import java.util.List;

public class VehicleMakeAdapter extends RecyclerView.Adapter<VehicleMakeAdapter.ViewHolder> {
    private List<String> vehicleCompanies;
    private Context context;

    public VehicleMakeAdapter(Context vehicleMakeActivity, List<String> vehicleCompanies) {
        this.vehicleCompanies = vehicleCompanies;
        this.context = vehicleMakeActivity;
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
        holder.bindView(vehicleCompanies.get(position));
    }

    @Override
    public int getItemCount() {
        return vehicleCompanies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout selectVehicleType;
        private TextView typeName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            selectVehicleType = itemView.findViewById(R.id.vehicle_type_layout);
            typeName = itemView.findViewById(R.id.vehicle_types);
        }

        public void bindView(String vehicleCompanies) {
            typeName.setText(vehicleCompanies);

            selectVehicleType.setOnClickListener(view -> {
                if (context instanceof VehicleMakeActivity) {
                    ((VehicleMakeActivity) context).selectVehicleCompany(
                            vehicleCompanies);
                }
            });
        }
    }
}
