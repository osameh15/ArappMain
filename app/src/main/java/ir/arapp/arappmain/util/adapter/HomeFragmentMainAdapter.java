 package ir.arapp.arappmain.util.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executors;

import ir.arapp.arappmain.R;
import ir.arapp.arappmain.databinding.ServiceCategoryLayoutBinding;

public class HomeFragmentMainAdapter extends RecyclerView.Adapter<HomeFragmentMainAdapter.AdapterViewHolder> {


    public static class RecyclerViewProperties {
        Context context;
        LinearLayoutManager layoutManager;
        HighOrderServicesAdapter adapter;

        public RecyclerViewProperties(Context context) {
            this.context = context;
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            layoutManager.setRecycleChildrenOnDetach(true);
            adapter = new HighOrderServicesAdapter();
        }


    }

    public ArrayList<RecyclerViewProperties> recyclerViewPropertiesList = new ArrayList<>();
    Context context;
    RecyclerView.RecycledViewPool recycledViewPool;

    public HomeFragmentMainAdapter(Context context) {
        this.context = context;
        recycledViewPool = new RecyclerView.RecycledViewPool();
        recycledViewPool.setMaxRecycledViews(0, 5);
    }

    @NonNull
    @NotNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_category_layout,parent,false);
//        ServiceCategoryLayoutBinding binding = ServiceCategoryLayoutBinding
//                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        AdapterViewHolder holder = new AdapterViewHolder(view);
        holder.recyclerView.setRecycledViewPool(recycledViewPool);

        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterViewHolder holder, int position) {

        holder.recyclerView.setAdapter(recyclerViewPropertiesList.get(position).adapter);
        holder.recyclerView.setLayoutManager(recyclerViewPropertiesList.get(position).layoutManager);
    }

    @Override
    public int getItemCount() {
        return recyclerViewPropertiesList.size();
    }

    public static class AdapterViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        public AdapterViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recycler_view);
            Log.i("TAG1212", "index: "+getAbsoluteAdapterPosition()+", "+getAdapterPosition()+" , "+getBindingAdapterPosition());
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull @NotNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
