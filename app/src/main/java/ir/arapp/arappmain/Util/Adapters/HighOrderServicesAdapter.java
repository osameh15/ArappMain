package ir.arapp.arappmain.Util.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import ir.arapp.arappmain.databinding.HomeHighOrderServicesItemBinding;

public class HighOrderServicesAdapter extends RecyclerView.Adapter<HighOrderServicesAdapter.HighOrderServicesViewHolder> {
    @NonNull
    @NotNull
    @Override
    public HighOrderServicesViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        HomeHighOrderServicesItemBinding viewBinding = HomeHighOrderServicesItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new HighOrderServicesViewHolder(viewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HighOrderServicesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class HighOrderServicesViewHolder extends RecyclerView.ViewHolder {
        public HighOrderServicesViewHolder(@NonNull @NotNull HomeHighOrderServicesItemBinding viewBinding) {
            super(viewBinding.getRoot());
        }
    }
}