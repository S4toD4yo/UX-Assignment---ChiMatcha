package com.example.chimatcha;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CarouselAdapter
        extends RecyclerView.Adapter<CarouselAdapter.ViewHolder> {

    private final int[] images;

    public CarouselAdapter(int[] images) {
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(
                                R.layout.item_carousel,
                                parent,
                                false
                        );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {

        holder.imgBanner.setImageResource(
                images[position]
        );
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    static class ViewHolder
            extends RecyclerView.ViewHolder {

        ImageView imgBanner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBanner =
                    itemView.findViewById(
                            R.id.imgBanner
                    );
        }
    }
}