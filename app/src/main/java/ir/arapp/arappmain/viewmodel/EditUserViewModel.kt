package ir.arapp.arappmain.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ir.arapp.arappmain.R
import java.util.*

@SuppressLint("StaticFieldLeak")
class EditUserViewModel(application: Application) : AndroidViewModel(application) {
    //    region Variables
    var context: Context
    var eduList: ArrayList<String>
    var provinceList: ArrayList<String>
    var cityList: ArrayList<String>

    //    Return Methods
    var allEduList: MutableLiveData<ArrayList<String>>
    var allProvinceList: MutableLiveData<ArrayList<String>>

    //    endregion
    var allCityList: MutableLiveData<ArrayList<String>>

    //    region Methods
    //    Initialize
    private fun init() {
        eduList.addAll(Arrays.asList(*context.resources.getStringArray(R.array.education_list)))
        provinceList.addAll(Arrays.asList(*context.resources.getStringArray(R.array.province_list)))
        allEduList.value = eduList
        allProvinceList.value = provinceList
    }

    //    Set City Lists
    fun setCityList(cityCode: Int) {
        if (cityCode == 0) {
            cityList.clear()
            cityList.add("ابتدا استان را انتخاب نمایید")
            allCityList.setValue(cityList)
        } else if (cityCode == 1) {
            cityList.clear()
            cityList.addAll(Arrays.asList(*context.resources.getStringArray(R.array.city_list_tehran)))
            allCityList.setValue(cityList)
        } else if (cityCode == 2) {
            cityList.clear()
            cityList.addAll(Arrays.asList(*context.resources.getStringArray(R.array.city_list_gilan)))
            allCityList.value = cityList
        }
    }

    //    endregion
    //    Constructor
    init {
        //        Hooks
        context = application.applicationContext
        eduList = ArrayList()
        provinceList = ArrayList()
        cityList = ArrayList()
        allEduList = MutableLiveData()
        allProvinceList = MutableLiveData()
        allCityList = MutableLiveData()
        //        Initialize
        init()
    }
}