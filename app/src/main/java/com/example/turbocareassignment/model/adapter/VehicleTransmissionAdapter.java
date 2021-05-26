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
import com.example.turbocareassignment.model.dataModel.VehicleSelectionTransmission;
import com.example.turbocareassignment.view.SelectTransmissionActivity;

import java.util.ArrayList;

public class VehicleTransmissionAdapter extends RecyclerView.Adapter<VehicleTransmissionAdapter.ViewAdapter> {

    private ArrayList<VehicleSelectionTransmission> vehicleSelectionTransmissions;
    private Context context;

    public VehicleTransmissionAdapter(Context selectTransmissionActivity, ArrayList<VehicleSelectionTransmission> vehicleSelectionTransmissions) {
        this.vehicleSelectionTransmissions = vehicleSelectionTransmissions;
        this.context = selectTransmissionActivity;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.common_list_all_recycle_view, parent, false);

        return new ViewAdapter(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        holder.bindView(vehicleSelectionTransmissions.get(position));
    }

    @Override
    public int getItemCount() {
        return vehicleSelectionTransmissions.size();
    }

    public class ViewAdapter extends RecyclerView.ViewHolder {
        private ConstraintLayout selectVehicleType;
        private TextView typeName;

        public ViewAdapter(@NonNull View itemView) {
            super(itemView);
            selectVehicleType = itemView.findViewById(R.id.vehicle_type_layout);
            typeName = itemView.findViewById(R.id.vehicle_types);
        }

        public void bindView(VehicleSelectionTransmission vehicleSelectionTransmission) {
            typeName.setText(vehicleSelectionTransmission.getVehicleTransmission());

            selectVehicleType.setOnClickListener(view -> {
                if (context instanceof SelectTransmissionActivity) {
                    ((SelectTransmissionActivity) context).selectSelectTransmission(
                            vehicleSelectionTransmission.getVehicleTransmission());
                }
            });
        }
    }
}
