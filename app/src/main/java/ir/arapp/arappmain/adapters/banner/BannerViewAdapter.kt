package ir.arapp.arappmain.adapters.banner

import android.view.View
import com.zhpan.bannerview.BaseBannerAdapter
import ir.arapp.arappmain.R
import ir.arapp.arappmain.model.Banner
import java.util.*

class BannerViewAdapter : BaseBannerAdapter<Banner, BannerViewHolder>() {
    //    region Variables
    var banners = ArrayList<Banner>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    //    endregion
    //    region Methods
    override fun onBind(holder: BannerViewHolder, data: Banner, position: Int, pageSize: Int) {
        holder.bindData(data, position, pageSize)
    }

    override fun createViewHolder(itemView: View, viewType: Int): BannerViewHolder {
        return BannerViewHolder(itemView)
    }

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.custom_banner_item_container
    }

    //    Set banner Items


    //    Get banner Items
}