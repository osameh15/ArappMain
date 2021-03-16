package ir.arapp.arappmain.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import ir.arapp.arappmain.Model.BannerItem;
import ir.arapp.arappmain.R;

public class HomeViewModel extends AndroidViewModel
{
//    region Variables
    ArrayList<BannerItem> bannerItems;
    MutableLiveData<ArrayList<BannerItem>> allBannerItems;
//    endregion
//    Constructor
    public HomeViewModel(@NonNull Application application)
    {
        super(application);
//        Hooks
        bannerItems = new ArrayList<>();
        allBannerItems = new MutableLiveData<>();
//        Initialize
        init();
    }
//    region Methods
//    Initialize function and variables
    private void init()
    {
        populateList();
        allBannerItems.setValue(bannerItems);
    }
//    Set data to banner array list
    private void populateList()
    {
        bannerItems.add(new BannerItem(1, 1, R.drawable.arapp_default, "اطلاعیه شماره 1", "23 اسفند 1399"));
        bannerItems.add(new BannerItem(2, 1, R.drawable.news_one, "اطلاعیه شماره 2", "3 فروردین 1400"));
        bannerItems.add(new BannerItem(3, 1, R.drawable.news_two, "اطلاعیه شماره 3", "23 فروردین 1400"));
        bannerItems.add(new BannerItem(4, 1, R.drawable.news_three, "اطلاعیه شماره 4", "28 فروردین 1400"));
        bannerItems.add(new BannerItem(5, 1, R.drawable.news_four, "اطلاعیه شماره 5", "10 اردیبهشت 1400"));
    }
//    Get all Category items
    public MutableLiveData<ArrayList<BannerItem>> getAllBannerItems()
    {
        return allBannerItems;
    }
//    endregion
}
