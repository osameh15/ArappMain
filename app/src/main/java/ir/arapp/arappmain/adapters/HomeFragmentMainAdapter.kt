package ir.arapp.arappmain.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import ir.arapp.arappmain.R
import ir.arapp.arappmain.adapters.HomeFragmentMainAdapter.AdapterViewHolder
import ir.arapp.arappmain.adapters.services.ServiceByCategoryAdapter
import java.util.*

class HomeFragmentMainAdapter(var context: Context) : RecyclerView.Adapter<AdapterViewHolder>() {
    var recycledViewPool :RecycledViewPool = RecycledViewPool().apply {
        setMaxRecycledViews(0,Int.MAX_VALUE)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.service_category_layout, parent, false)
        //        ServiceCategoryLayoutBinding binding = ServiceCategoryLayoutBinding
//                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return AdapterViewHolder(view)

    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 12
    }

    class AdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var recyclerView: RecyclerView

        init {
            recyclerView = itemView.findViewById(R.id.recycler_view)
            recyclerView.apply {
                isNestedScrollingEnabled = false
                this.setRecycledViewPool(recycledViewPool)
                adapter = ServiceByCategoryAdapter()
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
            Log.i(
                "TAG1212",
                "index: $absoluteAdapterPosition, $adapterPosition , $bindingAdapterPosition"
            )
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }


}