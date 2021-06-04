package ir.arapp.arappmain.Util.Adapters;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.adapters.AbsListViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import ir.arapp.arappmain.Model.Service;
import ir.arapp.arappmain.databinding.ServicesItemLayoutBinding;

public class HighOrderServicesAdapter extends RecyclerView.Adapter<HighOrderServicesAdapter.HighOrderServicesViewHolder> implements RecyclerView.OnItemTouchListener {

    SingleScrollDirectionEnforcer enforcer = new SingleScrollDirectionEnforcer();
    private final ArrayList<Service> services;

    public HighOrderServicesAdapter(ArrayList<Service> items) {
        this.services = items;

//
//        addOnItemTouchListener(enforcer)
//        addOnScrollListener(enforcer)


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

    @Override
    public boolean onInterceptTouchEvent(@NonNull @NotNull RecyclerView rv, @NonNull @NotNull MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull @NotNull RecyclerView rv, @NonNull @NotNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }


    public class HighOrderServicesViewHolder extends RecyclerView.ViewHolder {
        private final ServicesItemLayoutBinding binding;

        public HighOrderServicesViewHolder(@NonNull @NotNull ServicesItemLayoutBinding viewBinding) {
            super(viewBinding.getRoot());
            this.binding = viewBinding;
        }
    }
}