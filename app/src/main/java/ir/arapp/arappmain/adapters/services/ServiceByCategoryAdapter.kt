package ir.arapp.arappmain.adapters.services

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.arapp.arappmain.R
import ir.arapp.arappmain.databinding.ServicesItemLayoutBinding
import ir.arapp.arappmain.model.Service
import ir.arapp.arappmain.adapters.services.ServiceByCategoryAdapter.HighOrderServicesViewHolder
import java.util.*
import kotlin.collections.ArrayList

class ServiceByCategoryAdapter(var context: Context) : RecyclerView.Adapter<HighOrderServicesViewHolder>() {

    var orientation = LinearLayoutManager.HORIZONTAL


    var imageDrawable: ArrayList<Bitmap?> = ArrayList()

    init {


            imageDrawable.add(BitmapFactory.decodeResource(context.resources, R.drawable.cafe))
            imageDrawable.add(BitmapFactory.decodeResource(context.resources, R.drawable.hotels))
            imageDrawable.add(BitmapFactory.decodeResource(context.resources, R.drawable.restaurant))


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
        context?.let {
            holder.binding.serviceImageView.setImageBitmap(imageDrawable[position % 3])
        }
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