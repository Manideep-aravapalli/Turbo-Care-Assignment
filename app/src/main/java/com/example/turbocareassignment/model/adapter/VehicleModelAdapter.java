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
import com.example.turbocareassignment.view.VehicleModelActivity;

import java.util.List;

public class VehicleModelAdapter extends RecyclerView.Adapter<VehicleModelAdapter.ViewHolder> {

    private List<String> vehicleModels;
    private Context context;

    public VehicleModelAdapter(Context context, List<String> vehicleModels) {
        this.vehicleModels = vehicleModels;
        this.context = context;
    }

    @NonNull
    @Override
    public VehicleModelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.common_list_all_recycle_view, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleModelAdapter.ViewHolder holder, int position) {
        holder.bindView(vehicleModels.get(position));
    }

    @Override
    public int getItemCount() {
        return vehicleModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout selectVehicleType;
        private TextView typeName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            selectVehicleType = itemView.findViewById(R.id.vehicle_type_layout);
            typeName = itemView.findViewById(R.id.vehicle_types);
        }

        public void bindView(String s) {
            typeName.setText(s);

            selectVehicleType.setOnClickListener(view -> {
                if (context instanceof VehicleModelActivity) {
                    ((VehicleModelActivity) context).selectVehicleModel(
                            s);
                }
            });
        }
    }
}
