package ir.arapp.arappmain.Util.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import ir.arapp.arappmain.Model.Service;
import ir.arapp.arappmain.databinding.HomeHighOrderServicesItemBinding;

public class HighOrderServicesAdapter extends RecyclerView.Adapter<HighOrderServicesAdapter.HighOrderServicesViewHolder> {


    private final ArrayList<Service> services;

    public HighOrderServicesAdapter(ArrayList<Service> items){
        this.services = items;
    }
    @NonNull
    @NotNull
    @Override
    public HighOrderServicesViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        HomeHighOrderServicesItemBinding viewBinding = HomeHighOrderServicesItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new HighOrderServicesViewHolder(viewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HighOrderServicesViewHolder holder, int position) {
        holder.binding.highOrderServicesPlaceTextview.setText(services.get(position).getLocation());
        holder.binding.highOrderServicesTitleService.setText(services.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return services.size();
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class HighOrderServicesViewHolder extends RecyclerView.ViewHolder {
        private final HomeHighOrderServicesItemBinding binding;

        public HighOrderServicesViewHolder(@NonNull @NotNull HomeHighOrderServicesItemBinding viewBinding) {
            super(viewBinding.getRoot());
            this.binding = viewBinding;
        }
    }
}