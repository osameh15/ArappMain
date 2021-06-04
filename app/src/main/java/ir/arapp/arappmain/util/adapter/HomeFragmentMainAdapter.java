package ir.arapp.arappmain.util.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.concurrent.Executors;

import ir.arapp.arappmain.databinding.ServiceCategoryLayoutBinding;

public class HomeFragmentMainAdapter extends RecyclerView.Adapter<HomeFragmentMainAdapter.AdapterViewHolder> {

    public static class RecyclerViewProperties {
        Context context;
        LinearLayoutManager layoutManager;
        HighOrderServicesAdapter adapter;

        public RecyclerViewProperties(Context context) {
            this.context = context;
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            adapter = new HighOrderServicesAdapter();
        }


    }

    public ArrayList<RecyclerViewProperties> recyclerViewPropertiesList = new ArrayList<>();
    Context context;

    public HomeFragmentMainAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ServiceCategoryLayoutBinding binding = ServiceCategoryLayoutBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AdapterViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterViewHolder holder, int position) {
        holder.binding.recyclerView.setNestedScrollingEnabled(false);
        holder.binding.recyclerView.setAdapter(recyclerViewPropertiesList.get(position).adapter);
        holder.binding.recyclerView.setLayoutManager(recyclerViewPropertiesList.get(position).layoutManager);
    }

    @Override
    public int getItemCount() {
        return recyclerViewPropertiesList.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        ServiceCategoryLayoutBinding binding;

        public AdapterViewHolder(@NonNull @NotNull ServiceCategoryLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
