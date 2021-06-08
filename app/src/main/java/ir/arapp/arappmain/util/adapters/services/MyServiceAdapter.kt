package ir.arapp.arappmain.util.adapters.services

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.card.MaterialCardView
import com.makeramen.roundedimageview.RoundedImageView
import ir.arapp.arappmain.R
import ir.arapp.arappmain.databinding.ServicesItemLayoutBinding
import ir.arapp.arappmain.model.base.Service
import ir.arapp.arappmain.util.adapters.services.MyServiceAdapter.MyServiceItemViewHolder
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
////        Inflate Layout
//        val itemView = LayoutInflater.from(parent.context)
//            .inflate(R.layout.custom_my_service_container, parent, false)
//        return MyServiceItemViewHolder(itemView)

        val binding = ServicesItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyServiceItemViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyServiceItemViewHolder, position: Int) {
//        Get current Item in Service
        val currentItem = services[position]
        //        Set data and update xml
        holder.binding.apply {
            var layoutParams = root.layoutParams
            layoutParams.width = ConstraintLayout.LayoutParams.MATCH_PARENT
            layoutParams.height = ConstraintLayout.LayoutParams.WRAP_CONTENT
            serviceImageView.setImageResource(currentItem.picture)
            serviceItemLayoutLastTimeChangedTextView.text = currentItem.createdAt
            serviceItemLayoutTitleService.text = currentItem.title
            serviceItemLayoutCommentService.text = currentItem.comments.toString()
            serviceItemLayoutRateServiceFrameLayout.rate = currentItem.rate
            serviceItemLayoutLoading.visibility = View.GONE
            serviceItemLayoutServiceImageCardView.setOnClickListener { view: View? ->
                itemClickListener!!.onItemClickListener(
                    this@MyServiceAdapter.view,
                    currentItem.id,
                    currentItem.title
                )
            }

        }
//        holder.roundedImageView.setImageResource(currentItem.picture)
//        holder.time.text = currentItem.createdAt
//        holder.title.text = currentItem.title
//        holder.comment.text = Integer.toString(currentItem.comments)
//        holder.loading.visibility = View.GONE
//        if (currentItem.rate == 0.0) {
//            holder.rate.text = "-"
//            holder.rateFrameLayout.setBackgroundResource(R.drawable.rate_zero_shape)
//        } else if (currentItem.rate < 2) {
//            holder.rate.text = currentItem.rate.toString()
//            holder.rateFrameLayout.setBackgroundResource(R.drawable.rate_min_shape)
//        } else if (currentItem.rate < 4) {
//            holder.rate.text = currentItem.rate.toString()
//            holder.rateFrameLayout.setBackgroundResource(R.drawable.rate_normal_shape)
//        } else if (currentItem.rate <= 5) {
//            holder.rate.text = currentItem.rate.toString()
//            holder.rateFrameLayout.setBackgroundResource(R.drawable.rate_max_shape)
//        }
//        holder.cardView.setOnClickListener { view: View? ->
//            itemClickListener!!.onItemClickListener(
//                this.view,
//                currentItem.id,
//                currentItem.title
//            )
//        }
//        holder.editService.setOnClickListener { view: View? ->
//            itemClickListener!!.onItemClickListener(
//                this.view,
//                currentItem.id,
//                "edit"
//            )
//        }
//        holder.deleteService.setOnClickListener { view: View? ->
//            itemClickListener!!.onItemClickListener(
//                this.view,
//                currentItem.id,
//                "delete"
//            )
//        }
    }

    override fun getItemCount(): Int {
        return services.size
    }


    //    endregion
    //    View holder class
    class MyServiceItemViewHolder(val binding: ServicesItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        //        region Variables
//        var cardView: MaterialCardView
//        var roundedImageView: RoundedImageView
//        var loading: LottieAnimationView
//        var rateFrameLayout: FrameLayout
//        var rate: TextView
//        var time: TextView
//        var title: TextView
//        var comment: TextView
//        var editService: ImageView
//        var deleteService: ImageView

        //        endregion
        //        Constructor to View holder
        init {
            //            Hooks
//            cardView = binding.findViewById(R.id.serviceCardView)
//            roundedImageView = binding.findViewById(R.id.serviceImageContainer)
//            loading = binding.findViewById(R.id.loading)
//            rateFrameLayout = binding.findViewById(R.id.rateServiceFrameLayout)
//            rate = binding.findViewById(R.id.rateService)
//            time = binding.findViewById(R.id.timeService)
//            title = binding.findViewById(R.id.titleService)
//            comment = binding.findViewById(R.id.commentService)
//            editService = binding.findViewById(R.id.editMyService)
//            deleteService = binding.findViewById(R.id.deleteMyService)
        }
    }
}