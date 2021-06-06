package ir.arapp.arappmain.adapters.services

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.arapp.arappmain.databinding.ServicesItemLayoutBinding
import ir.arapp.arappmain.model.Service
import ir.arapp.arappmain.adapters.services.ServiceByCategoryAdapter.HighOrderServicesViewHolder
import java.util.*

class ServiceByCategoryAdapter : RecyclerView.Adapter<HighOrderServicesViewHolder> {
    private var services = ArrayList<Service>()

    var orientation = LinearLayoutManager.HORIZONTAL
    constructor(items: ArrayList<Service>) {
        services = items
    }

    constructor()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.layoutManager.apply {
            if (this is LinearLayoutManager){
                this@ServiceByCategoryAdapter.orientation = orientation
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighOrderServicesViewHolder {
        val viewBinding =
            ServicesItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HighOrderServicesViewHolder(viewBinding)
    }
    fun setWidthHeightByOrientation(viewGroup: ViewGroup,orientation:Int){
        val layoutParams = viewGroup.layoutParams
        viewGroup.layoutParams = if (orientation == LinearLayoutManager.HORIZONTAL){
            layoutParams.apply {
                width = ViewGroup.LayoutParams.WRAP_CONTENT
                height = ViewGroup.LayoutParams.MATCH_PARENT
            }
            layoutParams
        } else{
            layoutParams.apply {
                height = ViewGroup.LayoutParams.WRAP_CONTENT
                width = ViewGroup.LayoutParams.MATCH_PARENT
            }
            layoutParams
        }
    }
    override fun onBindViewHolder(holder: HighOrderServicesViewHolder, position: Int) {
        setWidthHeightByOrientation(holder.binding.root,orientation)
//        holder.binding.highOrderServicesPlaceTextview.setText(services.get(position).getLocation());
//        holder.binding.highOrderServicesTitleService.setText(services.get(position).getTitle());
    }

    override fun getItemCount(): Int {
        return 5
    }

    inner class HighOrderServicesViewHolder(val binding: ServicesItemLayoutBinding) :
        RecyclerView.ViewHolder(
            binding.root
        )
}