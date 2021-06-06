package ir.arapp.arappmain.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ir.arapp.arappmain.R
import ir.arapp.arappmain.model.Banner
import ir.arapp.arappmain.model.Service
import ir.arapp.arappmain.adapters.services.ServiceByCategoryAdapter
import java.util.*

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    //    region Variables
    var banners: ArrayList<Banner>

    //    Get all Category items
    var allBannerItems: MutableLiveData<ArrayList<Banner>>

    //    endregion
    var highOrderServicesAdapter = MutableLiveData<ServiceByCategoryAdapter>()
    var highOrderServices: ArrayList<Service>? = null

    //    region Methods
    //    Initialize function and variables
    private fun init() {
        populateList()
        allBannerItems.value = banners
        initHighOrderServices()
    }

    private fun initHighOrderServices() {
        highOrderServices = ArrayList()
        var item = Service(R.drawable.hotels, "سرویس های ویژه", "تهران")
        highOrderServices!!.add(item)
        item = Service(R.drawable.hotels, "محبوب ترین سرویس ها", "تهران")
        highOrderServices!!.add(item)
        item = Service(R.drawable.hotels, "جدید ترین سرویس ها", "تهران")
        highOrderServices!!.add(item)
//        highOrderServicesAdapter.postValue(ServiceByCategoryAdapter(highOrderServices!!))
    }

    //    Set data to banner array list
    private fun populateList() {
        banners.add(Banner(1, 1, R.drawable.arapp_default, "اطلاعیه شماره 1", "23 اسفند 1399"))
        banners.add(Banner(2, 1, R.drawable.news_one, "اطلاعیه شماره 2", "3 فروردین 1400"))
        banners.add(Banner(3, 1, R.drawable.news_two, "اطلاعیه شماره 3", "23 فروردین 1400"))
        banners.add(Banner(4, 1, R.drawable.news_three, "اطلاعیه شماره 4", "28 فروردین 1400"))
        banners.add(Banner(5, 1, R.drawable.news_four, "اطلاعیه شماره 5", "10 اردیبهشت 1400"))
    }

    //    endregion
    //    Constructor
    init {
        //        Hooks
        banners = ArrayList()
        allBannerItems = MutableLiveData()
        //        Initialize
        init()
    }
}