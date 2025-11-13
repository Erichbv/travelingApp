package com.example.travelingapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travelingapp.R;
import com.example.travelingapp.models.Destination;

import java.util.List;

public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.DestinationViewHolder> {
    private final Context context;
    private final List<Destination> destinationList;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onDetailClick(Destination destination);
    }

    public DestinationAdapter(Context context, List<Destination> destinationList, OnItemClickListener listener) {
        this.context = context;
        this.destinationList = destinationList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DestinationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_destination, parent, false);
        return new DestinationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DestinationViewHolder holder, int position) {
        Destination destination = destinationList.get(position);
        holder.textTitle.setText(destination.getName());
        holder.textDescription.setText(destination.getDescription());

        Glide.with(context)
                .load(destination.getImageUrl())
                .placeholder(R.drawable.ic_placeholder) // Asegúrate de tener un ícono de placeholder
                .into(holder.imageDestination);

        holder.buttonDetails.setOnClickListener(v -> listener.onDetailClick(destination));
    }

    @Override
    public int getItemCount() {
        return destinationList.size();
    }

    public static class DestinationViewHolder extends RecyclerView.ViewHolder {

        ImageView imageDestination;
        TextView textTitle, textDescription;
        Button buttonDetails;

        public DestinationViewHolder(@NonNull View itemView) {
            super(itemView);
            imageDestination = itemView.findViewById(R.id.imageDestination);
            textTitle = itemView.findViewById(R.id.textViewTitle);
            textDescription = itemView.findViewById(R.id.textViewDescription);
            buttonDetails = itemView.findViewById(R.id.buttonDetails);
        }
    }
}
