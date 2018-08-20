package com.example.damia.solarsystemeduweb;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SolarObjectsAdapter  extends RecyclerView.Adapter<SolarObjectsAdapter.SolarObjectViewHolder>{

    private final SolarObject[] solarObjects;

    private SolarObjectClickedListener solarObjectClickedListener;

    public SolarObjectsAdapter(SolarObject[] solarObjects) {
        this.solarObjects = solarObjects;
    }

    public void setSolarObjectClickedListener(SolarObjectClickedListener solarObjectClickedListener) {
        this.solarObjectClickedListener = solarObjectClickedListener;
    }

    @NonNull
    @Override
    public SolarObjectViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_solar_object, viewGroup, false);
        return new SolarObjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SolarObjectViewHolder solarObjectViewHolder, int i) {
        SolarObject solarObject = solarObjects[i];
        solarObjectViewHolder.setSolarObject(solarObject);
    }

    @Override
    public int getItemCount() {
        return solarObjects.length;
    }

    private void itemClicked(SolarObject solarObject) {
        if(solarObjectClickedListener != null){
            solarObjectClickedListener.solarObjectClicked(solarObject);
        }
    }

    class SolarObjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.itemImageView)
        ImageView itemImageView;

        @BindView(R.id.itemTextView)
        TextView itemTextView;

        private SolarObject solarObject;

        public SolarObjectViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setSolarObject(SolarObject solarObject) {
            this.solarObject = solarObject;
            itemTextView.setText(solarObject.getName());

            Glide.with(itemImageView.getContext())
                    .load(solarObject.getImagePath()).apply(new RequestOptions()
                    .placeholder(R.drawable.planet_placeholder).fitCenter()).into(itemImageView);
        }

        public SolarObject getSolarObject() {
            return solarObject;
        }

        @Override
        public void onClick(View view) {
            itemClicked(solarObject);
        }
    }


    public interface SolarObjectClickedListener {
        void solarObjectClicked(SolarObject solarObject);
    }


}
