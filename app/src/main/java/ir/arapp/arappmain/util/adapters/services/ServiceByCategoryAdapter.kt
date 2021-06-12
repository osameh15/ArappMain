package ir.arapp.arappmain.util.adapters.services

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.arapp.arappmain.databinding.ServicesItemLayoutBinding
import ir.arapp.arappmain.model.base.Service
import ir.arapp.arappmain.util.adapters.services.ServiceByCategoryAdapter.HighOrderServicesViewHolder
import java.util.*
import kotlin.collections.ArrayList

class ServiceByCategoryAdapter(
    val context: Context,
    _services: ArrayList<Service>
) :
    RecyclerView.Adapter<HighOrderServicesViewHolder>() {

    var orientation = LinearLayoutManager.HORIZONTAL
    var services = _services
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.layoutManager.apply {
            if (this is LinearLayoutManager) {
                this@ServiceByCategoryAdapter.orientation = orientation
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighOrderServicesViewHolder {
        val viewBinding =
            ServicesItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HighOrderServicesViewHolder(viewBinding)
    }

    fun setWidthHeightByOrientation(viewGroup: ViewGroup, orientation: Int) {
        val layoutParams = viewGroup.layoutParams
        viewGroup.layoutParams = if (orientation == LinearLayoutManager.HORIZONTAL) {
            layoutParams.apply {
                width = ViewGroup.LayoutParams.WRAP_CONTENT
                height = ViewGroup.LayoutParams.MATCH_PARENT
            }
            layoutParams
        } else {
            layoutParams.apply {
                height = ViewGroup.LayoutParams.WRAP_CONTENT
                width = ViewGroup.LayoutParams.MATCH_PARENT
            }
            layoutParams
        }
    }


    override fun onBindViewHolder(holder: HighOrderServicesViewHolder, position: Int) {
        setWidthHeightByOrientation(holder.binding.root, orientation)
        holder.binding.apply {
            val service = services[position]
            serviceItemLayoutTitleService.text = service.title
            serviceItemLayoutRateServiceFrameLayout.rate = service.rate
            serviceItemLayoutPlaceTextview.text = service.location
            Glide.with(context).load(service.picture).into(serviceImageView)
        }
//        holder.binding.highOrderServicesPlaceTextview.setText(services.get(position).getLocation());
//        holder.binding.highOrderServicesTitleService.setText(services.get(position).getTitle());
    }

    override fun getItemCount(): Int {
        return services.size
    }

    inner class HighOrderServicesViewHolder(val binding: ServicesItemLayoutBinding) :
        RecyclerView.ViewHolder(
            binding.root
        )
}