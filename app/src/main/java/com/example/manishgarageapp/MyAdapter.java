package com.example.manishgarageapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.viewHolder> {

    ArrayList<CarData> allVehicle;
    Context context;

    public MyAdapter(ArrayList<CarData> allVehicle, Context context) {
        this.allVehicle = allVehicle;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.eachitem, parent, false);
        return new viewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.carMake.setText(allVehicle.get(position).getMakeName());
        holder.carModel.setText(allVehicle.get(position).getModelName());
        //holder.makeName.setText(model.getMakeName());
        holder.deleteCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = Room.databaseBuilder(holder.carMake.getContext(), DatabaseHelper.class, "vehicledatabase")
                        .allowMainThreadQueries().build();
                VehicleDao userDao = db.vehicleDao();

                userDao.deleteTx(allVehicle.remove(0));
                if (holder.getAdapterPosition() > 0) {
                    //allVehicle.remove(holder.getAdapterPosition());
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {

        return allVehicle.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView carMake;
        TextView carModel;
        Button carImage;
        Button deleteCar;
        ImageView imageCar;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            carMake = itemView.findViewById(R.id.carmake);
            carModel = itemView.findViewById(R.id.carmodel);
            carImage = itemView.findViewById(R.id.carimage);
            deleteCar = itemView.findViewById(R.id.deletecar);
            imageCar = itemView.findViewById(R.id.imageCar);
        }
    }

}
