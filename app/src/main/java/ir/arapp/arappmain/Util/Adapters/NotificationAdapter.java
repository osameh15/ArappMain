package ir.arapp.arappmain.Util.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;

import ir.arapp.arappmain.Model.Notification;
import ir.arapp.arappmain.R;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>
{

//    region Variables
    ArrayList<Notification> notificationArrayList = new ArrayList<>();
//    endregion

//    region Methods
    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
//        Inflate layout
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_notification_container, parent, false);
//        Return view
        return new NotificationViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position)
    {
//        get current item in notification
        Notification currentNotification = notificationArrayList.get(position);
        if (notificationArrayList.size() == 0)
        {
            return;
        }
//        Set data and update xml
        holder.image.setImageResource(currentNotification.getImage());
        holder.title.setText(currentNotification.getTitle());
        holder.subtitle.setText(currentNotification.getSubtitle());
        holder.imageContext.setImageResource(currentNotification.getImageContext());
        holder.titleContext.setText(currentNotification.getTitle());
        holder.date.setText(currentNotification.getCreatedAt());
        holder.text.setText(currentNotification.getText());
        holder.foldingCell.setOnClickListener(view -> holder.foldingCell.toggle(false));
    }
    @Override
    public int getItemCount()
    {
        return notificationArrayList.size();
    }
//    Notify data changed
    public void setNotificationArrayList(ArrayList<Notification> notificationArrayList)
    {
        this.notificationArrayList = notificationArrayList;
        notifyDataSetChanged();
    }
//    endregion

//    View holder Class
    static class NotificationViewHolder extends RecyclerView.ViewHolder
    {
//        region Variables
        FoldingCell foldingCell;
        RoundedImageView image;
        RoundedImageView imageContext;
        TextView title;
        TextView titleContext;
        TextView subtitle;
        TextView text;
        TextView date;
//        endregion
//        Constructor to view holder
        public NotificationViewHolder(@NonNull View itemView)
        {
            super(itemView);
//            Hooks
            foldingCell = itemView.findViewById(R.id.foldingCell);
            image = itemView.findViewById(R.id.notificationImage);
            imageContext = itemView.findViewById(R.id.notificationImageContext);
            title = itemView.findViewById(R.id.notificationTitle);
            titleContext = itemView.findViewById(R.id.notificationTitleContext);
            subtitle = itemView.findViewById(R.id.notificationSubtitle);
            text = itemView.findViewById(R.id.notificationText);
            date = itemView.findViewById(R.id.notificationDate);
        }
    }
}
