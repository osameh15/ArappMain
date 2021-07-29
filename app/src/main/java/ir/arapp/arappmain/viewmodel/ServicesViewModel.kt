package ir.arapp.arappmain.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ir.arapp.arappmain.R
import ir.arapp.arappmain.model.base.GetAllServices
import ir.arapp.arappmain.model.base.ResponseModel
import ir.arapp.arappmain.model.base.Service
import ir.arapp.arappmain.util.server.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ServicesViewModel(application: Application) : AndroidViewModel(application) {
    //    region Variable
    var myServices: ArrayList<Service>

    //    Get all Category items
    //    endregion
    var allCategoryItems: MutableLiveData<ArrayList<Service>>

    //    region Methods
    //    Initialize function and variables
    private fun init() {
        populateList()

        allCategoryItems.value = myServices
    }

//        Set data to category array list
    private fun populateList() {
//
        myServices.add(
            Service(
                1,
                1,
                R.drawable.news_one,
                "رستوران خاوران",
                "چشم دل باز کن که جان بینی",
                "هرچه خدا بخواهد",
                0.0,
                0,
                "تهران، صادقیه",
                "دقایقی قبل",
                "",
                ""
            )
        )
        myServices.add(
            Service(
                2,
                1,
                R.drawable.news_two,
                "رستوران ایران زمین",
                "چشم دل باز کن که جان بینی",
                "هرچه خدا بخواهد",
                3.86,
                10,
                "تهران، صادقیه",
                "4 روز پیش",
                "",
                ""
            )
        )
        myServices.add(
            Service(
                3,
                1,
                R.drawable.news_three,
                "رستوران عمو ژوپیتو",
                "چشم دل باز کن که جان بینی",
                "هرچه خدا بخواهد",
                4.9,
                22,
                "تهران، صادقیه",
                "هفته پیش",
                "",
                ""
            )
        )
        myServices.add(
            Service(
                4,
                1,
                R.drawable.news_four,
                "فست فود فرزانه",
                "چشم دل باز کن که جان بینی",
                "هرچه خدا بخواهد",
                2.62,
                23,
                "تهران، صادقیه",
                "3 هفته پیش",
                "",
                ""
            )
        )
        myServices.add(
            Service(
                5,
                1,
                R.drawable.hotels,
                "هتل دایی بهزاد بجز بهروز",
                "چشم دل باز کن که جان بینی",
                "هرچه خدا بخواهد",
                1.56,
                89,
                "تهران، صادقیه",
                "10 فروردین 1400",
                "",
                ""
            )
        )
        myServices.add(
            Service(
                1,
                1,
                R.drawable.news_one,
                "رستوران خاوران",
                "چشم دل باز کن که جان بینی",
                "هرچه خدا بخواهد",
                0.0,
                0,
                "تهران، صادقیه",
                "دقایقی قبل",
                "",
                ""
            )
        )
        myServices.add(
            Service(
                2,
                1,
                R.drawable.news_two,
                "رستوران ایران زمین",
                "چشم دل باز کن که جان بینی",
                "هرچه خدا بخواهد",
                3.86,
                10,
                "تهران، صادقیه",
                "4 روز پیش",
                "",
                ""
            )
        )
        myServices.add(
            Service(
                3,
                1,
                R.drawable.news_three,
                "رستوران عمو ژوپیتو",
                "چشم دل باز کن که جان بینی",
                "هرچه خدا بخواهد",
                4.9,
                22,
                "تهران، صادقیه",
                "هفته پیش",
                "",
                ""
            )
        )
        myServices.add(
            Service(
                4,
                1,
                R.drawable.news_four,
                "فست فود فرزانه",
                "چشم دل باز کن که جان بینی",
                "هرچه خدا بخواهد",
                2.62,
                23,
                "تهران، صادقیه",
                "3 هفته پیش",
                "",
                ""
            )
        )
        myServices.add(
            Service(
                5,
                1,
                R.drawable.hotels,
                "هتل دایی بهزاد بجز بهروز",
                "چشم دل باز کن که جان بینی",
                "هرچه خدا بخواهد",
                1.56,
                89,
                "تهران، صادقیه",
                "10 فروردین 1400",
                "",
                ""
            )
        )
    }

    //    endregion
    //    Constructor
    init {
        //        Hooks
        myServices = ArrayList()
        allCategoryItems = MutableLiveData()
        //        Initialize
        init()
    }
}