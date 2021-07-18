package ir.arapp.arappmain.util.adapters.services

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import androidx.viewpager2.widget.ViewPager2
import ir.arapp.arappmain.databinding.HomeFragmentBannerViewLayoutBinding
import ir.arapp.arappmain.databinding.ServiceCategoryLayoutBinding
import ir.arapp.arappmain.model.HomeAdapterViewHolderModel
import ir.arapp.arappmain.model.HomeAdapterViewHolderModel.ViewType.BANNER
import ir.arapp.arappmain.model.HomeAdapterViewHolderModel.ViewType.SERVICE_BY_CATEGORY
import ir.arapp.arappmain.util.adapters.banner.BannerViewAdapter
import java.util.*
import kotlin.collections.ArrayList

class AllServicesCategoryAdapter(
    var context: Context,
    var fragment: Fragment,
    var _viewHolderModels: ArrayList<HomeAdapterViewHolderModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var viewHolderModels: ArrayList<HomeAdapterViewHolderModel> = _viewHolderModels
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    var recycledViewPool: RecycledViewPool = RecycledViewPool().apply {
        setMaxRecycledViews(0, Int.MAX_VALUE)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            BANNER.ordinal -> {
                val viewBinding = HomeFragmentBannerViewLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return SliderViewHolder(viewBinding)
            }
            SERVICE_BY_CATEGORY.ordinal -> {
                val viewBinding = ServiceCategoryLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ServiceByCategoryHolder(viewBinding)
            }
            else -> {
                val viewBinding = ServiceCategoryLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ServiceByCategoryHolder(viewBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ServiceByCategoryHolder) {
            holder.binding.apply {
                viewHolderModels[position].serviceByCategoryModel?.let {
                    categoryName.text = it.category.title
                    recyclerView.adapter?.let { adapter ->
                        if (adapter is ServiceByCategoryAdapter) {
                            adapter.services = it.services
                        }
                    }
                }
            }
        } else if (holder is SliderViewHolder) {
            var bannerViewPager = holder.binding.bannerView
            bannerViewPager.apply {
                setOffScreenPageLimit(2)
                    .setLifecycleRegistry(fragment.lifecycle)
                    .registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                        override fun onPageSelected(position: Int) {
                            super.onPageSelected(position)
                        }
                    })
                    .setOnPageClickListener { position: Int ->
//                        val banner = bannerViewPager!!.data[position]
//                        snackBarToast!!.snackBarLongTime(
//                            banner.text,
//                            requireActivity().findViewById(R.id.bottomNavigationView)
//                        )
                    }
                    .create(viewHolderModels[position].bannerModel!!)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return viewHolderModels[position].viewType.ordinal
    }

    override fun getItemCount(): Int {
        return viewHolderModels.size
    }

    inner class ServiceByCategoryHolder(val binding: ServiceCategoryLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.recyclerView.apply {
                isNestedScrollingEnabled = false
                this.setRecycledViewPool(this@AllServicesCategoryAdapter.recycledViewPool)
                adapter =
                    ServiceByCategoryAdapter(this@AllServicesCategoryAdapter.context, arrayListOf())
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

    class SliderViewHolder(val binding: HomeFragmentBannerViewLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.bannerView.setAdapter(BannerViewAdapter())
        }
    }


}