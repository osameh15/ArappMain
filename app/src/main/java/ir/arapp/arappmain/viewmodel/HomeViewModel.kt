package ir.arapp.arappmain.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ir.arapp.arappmain.R
import ir.arapp.arappmain.model.HomeAdapterViewHolderModel
import ir.arapp.arappmain.model.HomeAdapterViewHolderModel.ViewType.BANNER
import ir.arapp.arappmain.model.HomeAdapterViewHolderModel.ViewType.SERVICE_BY_CATEGORY
import ir.arapp.arappmain.model.ServiceByCategory
import ir.arapp.arappmain.model.base.Banner
import ir.arapp.arappmain.model.base.Category
import ir.arapp.arappmain.model.base.Service
import kotlin.collections.ArrayList

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    //    region Variables
    private var banners: ArrayList<Banner> = ArrayList()

    //    Get all Category items
    var allBannerItems: MutableLiveData<ArrayList<Banner>> = MutableLiveData()

    //    endregion

    private var homeAdapterData = ArrayList<HomeAdapterViewHolderModel>()
        set(value) {
            field = value
            homeAdapterDataLiveData.postValue(value)
        }
    var homeAdapterDataLiveData = MutableLiveData<ArrayList<HomeAdapterViewHolderModel>>()
    private fun initHomeAdapter() {
        setTestData()
        homeAdapterDataLiveData.postValue(homeAdapterData)
    }

    private fun setTestData() {
        homeAdapterData.add(HomeAdapterViewHolderModel(SERVICE_BY_CATEGORY).apply {
            serviceByCategoryModel = ServiceByCategory(
                arrayListOf(
                    Service(R.drawable.cafe, "کافه", "رشت", 1.3),
                    Service(R.drawable.shops, "هتل", "تنکابن", 2.4),
                    Service(R.drawable.restaurant, "رستوران", "تهران", 1.3),
                    Service(R.drawable.hotels, "هتل", "کاشان", 3.2),
                    Service(R.drawable.fast_food, "فست فود", "رامسر", 5.0),
                ),
                Category(0, "ویژه")
            )
        })
        homeAdapterData.add(HomeAdapterViewHolderModel(SERVICE_BY_CATEGORY).apply {
            serviceByCategoryModel = ServiceByCategory(
                arrayListOf(
                    Service(R.drawable.hotels, "هتل", "کاشان", 3.2),
                    Service(R.drawable.restaurant, "رستوران", "تهران", 4.3),
                    Service(R.drawable.cafe, "کافه", "رشت", 4.3),
                    Service(R.drawable.fast_food, "فست فود", "رامسر", 4.93),
                    Service(R.drawable.shops, "هتل", "تنکابن", 4.4),
                ),
                Category(0, "محبوب ترین ها")
            )
        })
        homeAdapterData.add(HomeAdapterViewHolderModel(BANNER).apply {
            bannerModel = arrayListOf(
                Banner(1, 1, R.drawable.hotels, "اطلاعیه شماره 1", "23 اسفند 1399"),
                Banner(2, 1, R.drawable.restaurant, "اطلاعیه شماره 2", "3 فروردین 1400"),
                Banner(3, 1, R.drawable.fast_food, "اطلاعیه شماره 3", "23 فروردین 1400"),
                Banner(4, 1, R.drawable.cafe, "اطلاعیه شماره 4", "28 فروردین 1400"),
                Banner(5, 1, R.drawable.restaurant, "اطلاعیه شماره 5", "10 اردیبهشت 1400")
            )
        })
        homeAdapterData.add(HomeAdapterViewHolderModel(SERVICE_BY_CATEGORY).apply {
            serviceByCategoryModel = ServiceByCategory(
                arrayListOf(
                    Service(R.drawable.hotels, "هتل", "کاشان", 3.2),
                    Service(R.drawable.restaurant, "رستوران", "تهران", 4.3),
                    Service(R.drawable.fast_food, "فست فود", "رامسر", 4.93),
                    Service(R.drawable.cafe, "کافه", "رشت", 4.3),
                    Service(R.drawable.shops, "هتل", "تنکابن", 4.4),
                ),
                Category(0, "رستوران")
            )
        })

        homeAdapterData.add(HomeAdapterViewHolderModel(SERVICE_BY_CATEGORY).apply {
            serviceByCategoryModel = ServiceByCategory(
                arrayListOf(
                    Service(R.drawable.fast_food, "فست فود", "رامسر", 4.93),
                    Service(R.drawable.hotels, "هتل", "کاشان", 3.2),
                    Service(R.drawable.hotels, "هتل", "تنکابن", 4.4),
                    Service(R.drawable.restaurant, "رستوران", "تهران", 4.3),
                    Service(R.drawable.cafe, "کافه", "رشت", 4.3),
                ),
                Category(0, "فست فود")
            )
        })
        homeAdapterData.add(HomeAdapterViewHolderModel(SERVICE_BY_CATEGORY).apply {
            serviceByCategoryModel = ServiceByCategory(
                arrayListOf(
                    Service(R.drawable.fast_food, "فست فود", "رامسر", 4.93),
                    Service(R.drawable.restaurant, "رستوران", "تهران", 4.3),
                    Service(R.drawable.cafe, "کافه", "رشت", 4.3),
                    Service(R.drawable.hotels, "هتل", "کاشان", 3.2),
                    Service(R.drawable.hotels, "هتل", "تنکابن", 4.4),
                ),
                Category(0, "کافه")
            )
        })
        homeAdapterData.add(HomeAdapterViewHolderModel(SERVICE_BY_CATEGORY).apply {
            serviceByCategoryModel = ServiceByCategory(
                arrayListOf(
                    Service(R.drawable.fast_food, "فست فود", "رامسر", 4.93),
                    Service(R.drawable.restaurant, "رستوران", "تهران", 4.3),
                    Service(R.drawable.cafe, "کافه", "رشت", 4.3),
                    Service(R.drawable.hotels, "هتل", "کاشان", 3.2),
                    Service(R.drawable.hotels, "هتل", "تنکابن", 4.4),
                ),
                Category(0, "هتل")
            )
        })
        homeAdapterData.add(HomeAdapterViewHolderModel(SERVICE_BY_CATEGORY).apply {
            serviceByCategoryModel = ServiceByCategory(
                arrayListOf(
                    Service(R.drawable.fast_food, "فست فود", "رامسر", 4.93),
                    Service(R.drawable.restaurant, "رستوران", "تهران", 4.3),
                    Service(R.drawable.cafe, "کافه", "رشت", 4.3),
                    Service(R.drawable.hotels, "هتل", "کاشان", 3.2),
                    Service(R.drawable.hotels, "هتل", "تنکابن", 4.4),
                ),
                Category(0, "مسافرخانه")
            )
        })
        homeAdapterData.add(HomeAdapterViewHolderModel(SERVICE_BY_CATEGORY).apply {
            serviceByCategoryModel = ServiceByCategory(
                arrayListOf(
                    Service(R.drawable.fast_food, "فست فود", "رامسر", 4.93),
                    Service(R.drawable.restaurant, "رستوران", "تهران", 4.3),
                    Service(R.drawable.cafe, "کافه", "رشت", 4.3),
                    Service(R.drawable.hotels, "هتل", "کاشان", 3.2),
                    Service(R.drawable.hotels, "هتل", "تنکابن", 4.4),
                ),
                Category(0, "مرکز خرید")
            )
        })
        homeAdapterData.add(HomeAdapterViewHolderModel(BANNER).apply {
            bannerModel = arrayListOf(
                Banner(1, 1, R.drawable.hotels, "اطلاعیه شماره 1", "23 اسفند 1399"),
                Banner(2, 1, R.drawable.hotels, "اطلاعیه شماره 2", "3 فروردین 1400"),
                Banner(3, 1, R.drawable.news_two, "اطلاعیه شماره 3", "23 فروردین 1400"),
                Banner(4, 1, R.drawable.news_three, "اطلاعیه شماره 4", "28 فروردین 1400"),
                Banner(5, 1, R.drawable.news_four, "اطلاعیه شماره 5", "10 اردیبهشت 1400")
            )
        })
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
        //        Initialize
        populateList()
        allBannerItems.value = banners
        initHomeAdapter()
    }
}