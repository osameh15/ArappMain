package ir.arapp.arappmain.util.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import ir.arapp.arappmain.model.Service;
import ir.arapp.arappmain.databinding.ServicesItemLayoutBinding;

public class HighOrderServicesAdapter extends RecyclerView.Adapter<HighOrderServicesAdapter.HighOrderServicesViewHolder> {

    private ArrayList<Service> services = new ArrayList<>();

    public HighOrderServicesAdapter(ArrayList<Service> items) {
        this.services = items;
//
//        addOnItemTouchListener(enforcer)
//        addOnScrollListener(enforcer)
    }
    public HighOrderServicesAdapter(){

    }

    @NonNull
    @NotNull
    @Override
    public HighOrderServicesViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ServicesItemLayoutBinding viewBinding = ServicesItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new HighOrderServicesViewHolder(viewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HighOrderServicesViewHolder holder, int position) {
//        holder.binding.highOrderServicesPlaceTextview.setText(services.get(position).getLocation());
//        holder.binding.highOrderServicesTitleService.setText(services.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return 5;
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }



    public class HighOrderServicesViewHolder extends RecyclerView.ViewHolder {
        private final ServicesItemLayoutBinding binding;

        public HighOrderServicesViewHolder(@NonNull @NotNull ServicesItemLayoutBinding viewBinding) {
            super(viewBinding.getRoot());
            this.binding = viewBinding;
        }
    }
}