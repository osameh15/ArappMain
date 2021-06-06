package ir.arapp.arappmain.util.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import ir.arapp.arappmain.R
import ir.arapp.arappmain.util.adapter.HomeFragmentMainAdapter.AdapterViewHolder
import java.util.*

class HomeFragmentMainAdapter(var context: Context) : RecyclerView.Adapter<AdapterViewHolder>() {
    class RecyclerViewProperties(var context: Context) {
        var layoutManager: LinearLayoutManager
        var adapter: HighOrderServicesAdapter

        init {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            layoutManager.recycleChildrenOnDetach = true
            adapter = HighOrderServicesAdapter()
        }
    }

    var recyclerViewPropertiesList = ArrayList<RecyclerViewProperties>()
    var recycledViewPool: RecycledViewPool
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.service_category_layout, parent, false)
        //        ServiceCategoryLayoutBinding binding = ServiceCategoryLayoutBinding
//                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        val holder = AdapterViewHolder(view)
        holder.recyclerView.setRecycledViewPool(recycledViewPool)
        return holder
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.recyclerView.adapter = recyclerViewPropertiesList[position].adapter
        holder.recyclerView.layoutManager = recyclerViewPropertiesList[position].layoutManager
    }

    override fun getItemCount(): Int {
        return recyclerViewPropertiesList.size
    }

    class AdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var recyclerView: RecyclerView

        init {
            recyclerView = itemView.findViewById(R.id.recycler_view)
            Log.i(
                "TAG1212",
                "index: $absoluteAdapterPosition, $adapterPosition , $bindingAdapterPosition"
            )
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }

    init {
        recycledViewPool = RecycledViewPool()
        recycledViewPool.setMaxRecycledViews(0, 5)
    }
}