package ir.arapp.arappmain.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ir.arapp.arappmain.R
import java.util.*

@SuppressLint("StaticFieldLeak")
class AddServiceViewModel(application: Application) : AndroidViewModel(application) {
    //    region Variables
    var context: Context
    var category: ArrayList<String>
    var openingYear: ArrayList<String>

    //    Return Methods
    var allCategory: MutableLiveData<ArrayList<String>>

    //    endregion
    var allOpeningYear: MutableLiveData<ArrayList<String>>

    //    region Methods
    //    Initialize
    private fun init() {
        category.addAll(Arrays.asList(*context.resources.getStringArray(R.array.category_list)))
        openingYear.addAll(Arrays.asList(*context.resources.getStringArray(R.array.year_list)))
        allCategory.value = category
        allOpeningYear.value = openingYear
    }

    //    endregion
    //    Constructor
    init {
        //        Hooks
        context = application.applicationContext
        category = ArrayList()
        openingYear = ArrayList()
        allCategory = MutableLiveData()
        allOpeningYear = MutableLiveData()
        //        Initialize
        init()
    }
}