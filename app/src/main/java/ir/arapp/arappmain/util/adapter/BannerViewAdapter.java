package ir.arapp.arappmain.util.adapter;

import android.view.View;

import com.zhpan.bannerview.BaseBannerAdapter;

import java.util.ArrayList;

import ir.arapp.arappmain.model.Banner;
import ir.arapp.arappmain.R;

public class BannerViewAdapter extends BaseBannerAdapter<Banner, BannerViewHolder>
{
//    region Variables
    ArrayList<Banner> banners = new ArrayList<>();
//    endregion

//    region Methods
    @Override
    protected void onBind(BannerViewHolder holder, Banner data, int position, int pageSize)
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
    public void setBanners(ArrayList<Banner> banners)
    {
        this.banners = banners;
        notifyDataSetChanged();
    }
//    Get banner Items
    public ArrayList<Banner> getBanners()
    {
        return this.banners;
    }
//    endregion
}
