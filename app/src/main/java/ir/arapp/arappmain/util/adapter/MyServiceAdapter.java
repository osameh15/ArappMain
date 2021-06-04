package ir.arapp.arappmain.util.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.card.MaterialCardView;
import com.makeramen.roundedimageview.RoundedImageView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import ir.arapp.arappmain.model.Service;
import ir.arapp.arappmain.R;
import ir.arapp.arappmain.util.services.ItemClickListener;

public class MyServiceAdapter extends RecyclerView.Adapter<MyServiceAdapter.MyServiceItemViewHolder>
{
//    region Variables
    private final View view;
    public ItemClickListener itemClickListener;
    ArrayList<Service> services = new ArrayList<>();
//    endregion

//    region Methods
//    Constructor
    public MyServiceAdapter(View view)
    {
        this.view = view;
    }
    @NotNull
    @Override
    public MyServiceItemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType)
    {
//        Inflate Layout
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_my_service_container, parent, false);
        return new MyServiceItemViewHolder(itemView);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull MyServiceItemViewHolder holder, int position)
    {
//        Get current Item in Service
        Service currentItem = services.get(position);
//        Set data and update xml
        holder.roundedImageView.setImageResource(currentItem.getPicture());
        holder.time.setText(currentItem.getCreatedAt());
        holder.title.setText(currentItem.getTitle());
        holder.comment.setText(Integer.toString(currentItem.getComments()));
        holder.loading.setVisibility(View.GONE);
        if (currentItem.getRate() == 0)
        {
            holder.rate.setText("-");
            holder.rateFrameLayout.setBackgroundResource(R.drawable.rate_zero_shape);
        }
        else if (currentItem.getRate() < 2)
        {
            holder.rate.setText(Double.toString(currentItem.getRate()));
            holder.rateFrameLayout.setBackgroundResource(R.drawable.rate_min_shape);
        }
        else if (currentItem.getRate() < 4)
        {
            holder.rate.setText(Double.toString(currentItem.getRate()));
            holder.rateFrameLayout.setBackgroundResource(R.drawable.rate_normal_shape);
        }
        else if (currentItem.getRate() <= 5)
        {
            holder.rate.setText(Double.toString(currentItem.getRate()));
            holder.rateFrameLayout.setBackgroundResource(R.drawable.rate_max_shape);
        }
        holder.cardView.setOnClickListener(view -> itemClickListener.onItemClickListener(this.view, currentItem.getId(), currentItem.getTitle()));
        holder.editService.setOnClickListener(view -> itemClickListener.onItemClickListener(this.view, currentItem.getId(), "edit"));
        holder.deleteService.setOnClickListener(view -> itemClickListener.onItemClickListener(this.view, currentItem.getId(), "delete"));
    }
    @Override
    public int getItemCount()
    {
        return services.size();
    }
    public void setServices(ArrayList<Service> services)
    {
        this.services = services;
        notifyDataSetChanged();
    }
//    endregion
//    View holder class
    static class MyServiceItemViewHolder extends RecyclerView.ViewHolder
    {
//        region Variables
        MaterialCardView cardView;
        RoundedImageView roundedImageView;
        LottieAnimationView loading;
        FrameLayout rateFrameLayout;
        TextView rate;
        TextView time;
        TextView title;
        TextView comment;
        ImageView editService;
        ImageView deleteService;
//        endregion
//        Constructor to View holder
        public MyServiceItemViewHolder(@NonNull @NotNull View itemView)
        {
            super(itemView);
//            Hooks
            cardView = itemView.findViewById(R.id.serviceCardView);
            roundedImageView = itemView.findViewById(R.id.serviceImageContainer);
            loading = itemView.findViewById(R.id.loading);
            rateFrameLayout = itemView.findViewById(R.id.rateServiceFrameLayout);
            rate = itemView.findViewById(R.id.rateService);
            time = itemView.findViewById(R.id.timeService);
            title = itemView.findViewById(R.id.titleService);
            comment = itemView.findViewById(R.id.commentService);
            editService = itemView.findViewById(R.id.editMyService);
            deleteService = itemView.findViewById(R.id.deleteMyService);
        }
    }
}
