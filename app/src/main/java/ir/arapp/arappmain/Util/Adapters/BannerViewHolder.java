package ir.arapp.arappmain.Util.Adapters;

import android.view.View;

import androidx.annotation.NonNull;

import com.makeramen.roundedimageview.RoundedImageView;
import com.zhpan.bannerview.BaseViewHolder;

import ir.arapp.arappmain.Model.Banner;
import ir.arapp.arappmain.R;

public class BannerViewHolder extends BaseViewHolder<Banner>
{
//        region Variables
    RoundedImageView roundedImageView;
//        endregion
    public BannerViewHolder(@NonNull View itemView)
    {
        super(itemView);
        roundedImageView = itemView.findViewById(R.id.bannerImage);
    }
//        Bind data
    @Override
    public void bindData(Banner data, int position, int pageSize)
    {
        roundedImageView.setImageResource(data.getPicture());
    }
}
