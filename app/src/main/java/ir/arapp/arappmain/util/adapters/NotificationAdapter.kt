package ir.arapp.arappmain.util.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import com.ramotion.foldingcell.FoldingCell
import ir.arapp.arappmain.R
import ir.arapp.arappmain.model.base.Notification
import ir.arapp.arappmain.util.adapters.NotificationAdapter.NotificationViewHolder
import java.util.*

class NotificationAdapter : RecyclerView.Adapter<NotificationViewHolder>() {
    //    region Variables
    var notificationArrayList = ArrayList<Notification>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    //    endregion
    //    region Methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
//        Inflate layout
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_notification_container, parent, false)
        //        Return view
        return NotificationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
//        get current item in notification
        val currentNotification = notificationArrayList[position]
        if (notificationArrayList.size == 0) {
            return
        }
        //        Set data and update xml
        holder.image.setImageResource(currentNotification.image)
        holder.title.text = currentNotification.title
        holder.subtitle.text = currentNotification.subtitle
        holder.imageContext.setImageResource(currentNotification.imageContext)
        holder.titleContext.text = currentNotification.title
        holder.date.text = currentNotification.createdAt
        holder.text.text = currentNotification.text
        holder.foldingCell.setOnClickListener { view: View? -> holder.foldingCell.toggle(false) }
    }

    override fun getItemCount(): Int {
        return notificationArrayList.size
    }

    //    Notify data changed


    //    endregion
    //    View holder Class
    inner class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //        region Variables
        var foldingCell: FoldingCell
        var image: RoundedImageView
        var imageContext: RoundedImageView
        var title: TextView
        var titleContext: TextView
        var subtitle: TextView
        var text: TextView
        var date: TextView

        //        endregion
        //        Constructor to view holder
        init {
            //            Hooks
            foldingCell = itemView.findViewById(R.id.foldingCell)
            image = itemView.findViewById(R.id.notificationImage)
            imageContext = itemView.findViewById(R.id.notificationImageContext)
            title = itemView.findViewById(R.id.notificationTitle)
            titleContext = itemView.findViewById(R.id.notificationTitleContext)
            subtitle = itemView.findViewById(R.id.notificationSubtitle)
            text = itemView.findViewById(R.id.notificationText)
            date = itemView.findViewById(R.id.notificationDate)
        }
    }
}