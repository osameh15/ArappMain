package ir.arapp.arappmain.util.adapters.services

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.arapp.arappmain.databinding.ServicesItemVerticalBinding

class VerticalServiceAdapter  :RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ServicesItemVerticalBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return VerticalServiceHolder(binding)
    }

    inner class VerticalServiceHolder(val binding: ServicesItemVerticalBinding):RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is VerticalServiceHolder){
            holder.binding.apply {
                serviceItemHorizontalTitle
                serviceItemHorizontalDistance
                serviceItemHorizontalLastTime
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }
    override fun getItemCount(): Int {
        return 16
    }
}