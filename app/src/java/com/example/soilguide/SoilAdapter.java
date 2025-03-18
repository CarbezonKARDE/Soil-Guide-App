package com.example.soilguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class SoilAdapter extends RecyclerView.Adapter<SoilAdapter.SoilViewHolder> implements Filterable {
    private List<Soil> soilList;
    private List<Soil> soilListFull;
    private final OnSoilClickListener listener;

    public interface OnSoilClickListener {
        void onSoilClick(int position);
    }

    public SoilAdapter(List<Soil> soilList, OnSoilClickListener listener) {
        this.soilList = soilList;
        this.listener = listener;
        soilListFull = new ArrayList<>(soilList);
    }

    @NonNull
    @Override
    public SoilViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_soil, parent, false);
        return new SoilViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SoilViewHolder holder, int position) {
        Soil soil = soilList.get(position);
        holder.imageView.setImageResource(soil.getImageResource());
        holder.textView.setText(soil.getName());
        holder.itemView.setOnClickListener(v -> listener.onSoilClick(position));
    }

    @Override
    public int getItemCount() {
        return soilList.size();
    }

    static class SoilViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        SoilViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            textView = itemView.findViewById(R.id.text_view);
        }
    }

    @Override
    public Filter getFilter() {
        return soilFilter;
    }

    private final Filter soilFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Soil> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(soilListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Soil item : soilListFull) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            soilList.clear();
            soilList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}