package com.example.turbocareassignment.model.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.turbocareassignment.R;
import com.example.turbocareassignment.model.dataModel.GetVehicleDetails;
import com.example.turbocareassignment.view.MainActivity;

import java.util.ArrayList;

public class AddDataIntoLocalDbAdapter extends RecyclerView.Adapter<AddDataIntoLocalDbAdapter.ViewHolder> {
    private ArrayList<GetVehicleDetails> getVehicleDetails;
    private Context context;

    public AddDataIntoLocalDbAdapter(Context mainActivity, ArrayList<GetVehicleDetails> getVehicleDetails) {
        this.getVehicleDetails = getVehicleDetails;
        this.context = mainActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_of_added_vehicles, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(getVehicleDetails.get(position));
    }

    @Override
    public int getItemCount() {
        return getVehicleDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout constraintLayout;
        private TextView vehicleNo;
        private TextView companyName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.details);
            vehicleNo = itemView.findViewById(R.id.vehicle_number);
            companyName = itemView.findViewById(R.id.vehicle_company_name);
        }

        @SuppressLint("SetTextI18n")
        public void bindView(GetVehicleDetails getVehicleDetails) {
            vehicleNo.setText(getVehicleDetails.getVehicleNumber());
            companyName.setText(getVehicleDetails.getVehicleCompany() + " " + getVehicleDetails.getVehicleModel());

            constraintLayout.setOnClickListener(view -> {
                if (context instanceof MainActivity) {
                    ((MainActivity) context).allVehicleData(
                            getVehicleDetails.getVehicleNumber());
                }
            });
        }
    }
}
