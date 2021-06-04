package ir.arapp.arappmain.util.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import ir.arapp.arappmain.databinding.ServiceCategoryLayoutBinding;

public class HomeFragmentMainAdapter extends RecyclerView.Adapter<HomeFragmentMainAdapter.AdapterViewHolder> {

    Context context;
    public HomeFragmentMainAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ServiceCategoryLayoutBinding binding = ServiceCategoryLayoutBinding
                .inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new AdapterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterViewHolder holder, int position) {
//        SingleDirectionRecyclerView recyclerView = ((SingleDirectionRecyclerView)holder.binding.getRoot().findViewById(R.id.main_recycler_view));
        holder.binding.recyclerView.setAdapter(new HighOrderServicesAdapter());
        holder.binding.recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        ServiceCategoryLayoutBinding binding ;
        public AdapterViewHolder(@NonNull @NotNull ServiceCategoryLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
