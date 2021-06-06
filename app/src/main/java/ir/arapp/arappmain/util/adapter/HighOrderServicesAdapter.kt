package ir.arapp.arappmain.util.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.arapp.arappmain.databinding.ServicesItemLayoutBinding
import ir.arapp.arappmain.model.Service
import ir.arapp.arappmain.util.adapter.HighOrderServicesAdapter.HighOrderServicesViewHolder
import java.util.*

class HighOrderServicesAdapter : RecyclerView.Adapter<HighOrderServicesViewHolder> {
    private var services = ArrayList<Service>()

    constructor(items: ArrayList<Service>) {
        services = items
        //
//        addOnItemTouchListener(enforcer)
//        addOnScrollListener(enforcer)
    }

    constructor() {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighOrderServicesViewHolder {
        val viewBinding =
            ServicesItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HighOrderServicesViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: HighOrderServicesViewHolder, position: Int) {
//        holder.binding.highOrderServicesPlaceTextview.setText(services.get(position).getLocation());
//        holder.binding.highOrderServicesTitleService.setText(services.get(position).getTitle());
    }

    override fun getItemCount(): Int {
        return 5
    }

    class HighOrderServicesViewHolder(private val binding: ServicesItemLayoutBinding) :
        RecyclerView.ViewHolder(
            binding.root
        )
}