package ir.arapp.arappmain.adapters.banner

import android.view.View
import com.makeramen.roundedimageview.RoundedImageView
import com.zhpan.bannerview.BaseViewHolder
import ir.arapp.arappmain.R
import ir.arapp.arappmain.model.Banner

class BannerViewHolder(itemView: View) : BaseViewHolder<Banner>(itemView) {
    //        region Variables
    var roundedImageView: RoundedImageView

    //        Bind data
    override fun bindData(data: Banner, position: Int, pageSize: Int) {
        roundedImageView.setImageResource(data.picture)
    }

    //        endregion
    init {
        roundedImageView = itemView.findViewById(R.id.bannerImage)
    }
}