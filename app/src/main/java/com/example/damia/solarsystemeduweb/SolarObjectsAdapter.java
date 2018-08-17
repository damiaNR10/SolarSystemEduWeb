package com.example.damia.solarsystemeduweb;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SolarObjectsAdapter  extends RecyclerView.Adapter<SolarObjectsAdapter.SolarObjectViewHolder>{

    private final SolarObject[] solarObjects;

    public SolarObjectsAdapter(SolarObject[] solarObjects) {
        this.solarObjects = solarObjects;
    }

    @NonNull
    @Override
    public SolarObjectViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_solar_object, viewGroup, false);
        return new SolarObjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SolarObjectViewHolder solarObjectViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return solarObjects.length;
    }

    class SolarObjectViewHolder extends RecyclerView.ViewHolder{
         public SolarObjectViewHolder(View itemView) {
            super(itemView);
        }

    }
}
