package ir.arapp.arappmain.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ir.arapp.arappmain.R
import ir.arapp.arappmain.model.Profile
import ir.arapp.arappmain.util.services.FragmentManager
import ir.arapp.arappmain.util.services.SnackBarMessage
import java.util.*

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    //    region Variable
    var profileArrayList: ArrayList<Profile>

    //    Get all profile items
    var allProfileItems: MutableLiveData<ArrayList<Profile>>
    var snackBarMessage: SnackBarMessage? = null
    var fragmentManager: FragmentManager? = null

    //    region Methods
    //    Initialize function and variables
    private fun init() {
        populateList()
        allProfileItems.value = profileArrayList
    }

    //    Set data to category array list
    private fun populateList() {
        profileArrayList.add(Profile(1, R.drawable.edit_service, "افزودن سرویس"))
        profileArrayList.add(Profile(2, R.drawable.add_service, "افزودن تبلیغات"))
        profileArrayList.add(Profile(3, R.drawable.my_service, "سرویس های من"))
        profileArrayList.add(Profile(4, R.drawable.rezerve, "رزرو سرویس"))
        profileArrayList.add(Profile(5, R.drawable.rates, "امتیازات من"))
        profileArrayList.add(Profile(6, R.drawable.comments, "نظرات من"))
        profileArrayList.add(Profile(7, R.drawable.reduction, "تخفیفات"))
        profileArrayList.add(Profile(8, R.drawable.log_out, "خروج از حساب کاربری"))
    }

    //    Edit Profile User
    fun editUserButton() {
        fragmentManager!!.navigateToFragment("editUser")
    }

    //    Arapp Coin and navigate to fragment
    fun coinButton() {
        snackBarMessage!!.onSuccess("آراپ کوین")
    }

    //    Change User profile avatar
    fun changePictureButton() {
        fragmentManager!!.setFunction("image")
    }

    //    App setting
    fun setting() {
        fragmentManager!!.navigateToFragment("setting")
    } //    endregion

    //    endregion
    //    Constructor
    init {
        //        Hooks
        profileArrayList = ArrayList()
        allProfileItems = MutableLiveData()
        //        Initialize
        init()
    }
}