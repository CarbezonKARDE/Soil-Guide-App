// SoilAdapter.java
package com.example.soilappadv1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SoilAdapter extends RecyclerView.Adapter<SoilAdapter.SoilViewHolder> {

    private ArrayList<Soil> soils;
    private OnSoilClickListener listener;

    public interface OnSoilClickListener {
        void onSoilClick(Soil soil);
    }

    public SoilAdapter(ArrayList<Soil> soils, OnSoilClickListener listener) {
        this.soils = soils;
        this.listener = listener;
    }

    @Override
    public SoilViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.soil_card_item, parent, false);
        return new SoilViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SoilViewHolder holder, int position) {
        Soil soil = soils.get(position);
        holder.tvSoilName.setText(soil.getName());
        holder.ivSoilImage.setImageResource(soil.getImageResource());
        holder.itemView.setOnClickListener(v -> listener.onSoilClick(soil));
    }

    @Override
    public int getItemCount() {
        return soils.size();
    }

    public static class SoilViewHolder extends RecyclerView.ViewHolder {
        ImageView ivSoilImage;
        TextView tvSoilName;

        public SoilViewHolder(View itemView) {
            super(itemView);
            ivSoilImage = itemView.findViewById(R.id.ivSoilImage);
            tvSoilName = itemView.findViewById(R.id.tvSoilName);
        }
    }
}
