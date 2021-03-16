package ir.arapp.arappmain.Util.Adapters;

import android.view.View;
import androidx.annotation.NonNull;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zhpan.bannerview.BaseBannerAdapter;
import com.zhpan.bannerview.BaseViewHolder;

import java.util.ArrayList;

import ir.arapp.arappmain.Model.BannerItem;
import ir.arapp.arappmain.R;

public class BannerViewAdapter extends BaseBannerAdapter<BannerItem, BannerViewHolder>
{
//    region Variables
    ArrayList<BannerItem> bannerItems = new ArrayList<>();
//    endregion

//    region Methods
    @Override
    protected void onBind(BannerViewHolder holder, BannerItem data, int position, int pageSize)
    {
        holder.bindData(data, position, pageSize);
    }
    @Override
    public BannerViewHolder createViewHolder(View itemView, int viewType)
    {
        return new BannerViewHolder(itemView);
    }
    @Override
    public int getLayoutId(int viewType)
    {
        return R.layout.custom_banner_item_container;
    }
//    Set banner Items
    public void setBannerItems(ArrayList<BannerItem> bannerItems)
    {
        this.bannerItems = bannerItems;
        notifyDataSetChanged();
    }
//    Get banner Items
    public ArrayList<BannerItem> getBannerItems()
    {
        return this.bannerItems;
    }
//    endregion
}
