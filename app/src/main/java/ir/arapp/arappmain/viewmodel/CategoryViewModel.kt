package ir.arapp.arappmain.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ir.arapp.arappmain.R
import ir.arapp.arappmain.model.base.Category
import java.util.*

class CategoryViewModel(application: Application) : AndroidViewModel(application) {
    //    region Variable
    var categories: ArrayList<Category>

    //    Get all Category items
    //    endregion
    var allCategoryItems: MutableLiveData<ArrayList<Category>>

    //    region Methods
    //    Initialize function and variables
    private fun init() {
        populateList()
        allCategoryItems.value = categories
    }

    //    Set data to category array list
    private fun populateList() {
//        TODO connect to server
        categories.add(Category(1, "رستوران", R.drawable.restaurant))
        categories.add(Category(2, "فست فود", R.drawable.fast_food))
        categories.add(Category(3, "کافه", R.drawable.cafe))
        categories.add(Category(4, "هتل", R.drawable.hotels))
        categories.add(Category(5, "مسافرخانه", R.drawable.rest_room))
        categories.add(Category(6, "مراکز خرید", R.drawable.shops))
    }

    //    endregion
    //    Constructor
    init {
        //        Hooks
        categories = ArrayList()
        allCategoryItems = MutableLiveData()
        //        Initialize
        init()
    }
}