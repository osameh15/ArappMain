package ir.arapp.arappmain.util.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.card.MaterialCardView
import com.makeramen.roundedimageview.RoundedImageView
import ir.arapp.arappmain.R
import ir.arapp.arappmain.model.Service
import ir.arapp.arappmain.util.adapter.MyServiceAdapter.MyServiceItemViewHolder
import ir.arapp.arappmain.util.services.ItemClickListener
import java.util.*

class MyServiceAdapter     //    endregion
//    region Methods
//    Constructor
    (  //    region Variables
    private val view: View
) : RecyclerView.Adapter<MyServiceItemViewHolder>() {
    var itemClickListener: ItemClickListener? = null
    var services = ArrayList<Service>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyServiceItemViewHolder {
//        Inflate Layout
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_my_service_container, parent, false)
        return MyServiceItemViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyServiceItemViewHolder, position: Int) {
//        Get current Item in Service
        val currentItem = services[position]
        //        Set data and update xml
        holder.roundedImageView.setImageResource(currentItem.picture)
        holder.time.text = currentItem.createdAt
        holder.title.text = currentItem.title
        holder.comment.text = Integer.toString(currentItem.comments)
        holder.loading.visibility = View.GONE
        if (currentItem.rate == 0.0) {
            holder.rate.text = "-"
            holder.rateFrameLayout.setBackgroundResource(R.drawable.rate_zero_shape)
        } else if (currentItem.rate < 2) {
            holder.rate.text = java.lang.Double.toString(currentItem.rate)
            holder.rateFrameLayout.setBackgroundResource(R.drawable.rate_min_shape)
        } else if (currentItem.rate < 4) {
            holder.rate.text = java.lang.Double.toString(currentItem.rate)
            holder.rateFrameLayout.setBackgroundResource(R.drawable.rate_normal_shape)
        } else if (currentItem.rate <= 5) {
            holder.rate.text = java.lang.Double.toString(currentItem.rate)
            holder.rateFrameLayout.setBackgroundResource(R.drawable.rate_max_shape)
        }
        holder.cardView.setOnClickListener { view: View? ->
            itemClickListener!!.onItemClickListener(
                this.view,
                currentItem.id,
                currentItem.title
            )
        }
        holder.editService.setOnClickListener { view: View? ->
            itemClickListener!!.onItemClickListener(
                this.view,
                currentItem.id,
                "edit"
            )
        }
        holder.deleteService.setOnClickListener { view: View? ->
            itemClickListener!!.onItemClickListener(
                this.view,
                currentItem.id,
                "delete"
            )
        }
    }

    override fun getItemCount(): Int {
        return services.size
    }


    //    endregion
    //    View holder class
    class MyServiceItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //        region Variables
        var cardView: MaterialCardView
        var roundedImageView: RoundedImageView
        var loading: LottieAnimationView
        var rateFrameLayout: FrameLayout
        var rate: TextView
        var time: TextView
        var title: TextView
        var comment: TextView
        var editService: ImageView
        var deleteService: ImageView

        //        endregion
        //        Constructor to View holder
        init {
            //            Hooks
            cardView = itemView.findViewById(R.id.serviceCardView)
            roundedImageView = itemView.findViewById(R.id.serviceImageContainer)
            loading = itemView.findViewById(R.id.loading)
            rateFrameLayout = itemView.findViewById(R.id.rateServiceFrameLayout)
            rate = itemView.findViewById(R.id.rateService)
            time = itemView.findViewById(R.id.timeService)
            title = itemView.findViewById(R.id.titleService)
            comment = itemView.findViewById(R.id.commentService)
            editService = itemView.findViewById(R.id.editMyService)
            deleteService = itemView.findViewById(R.id.deleteMyService)
        }
    }
}