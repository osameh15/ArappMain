package ir.arapp.arappmain.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ir.arapp.arappmain.util.services.FragmentManager
import ir.arapp.arappmain.util.services.SnackBarMessage
import java.util.*

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    //    region Variable
    @JvmField
    var phone: MutableLiveData<String>
    @JvmField
    var password: MutableLiveData<String>
    var snackBarMessage: SnackBarMessage? = null
    var fragmentManager: FragmentManager? = null

    //    region Methods
    //    On Cleared
    override fun onCleared() {
        super.onCleared()
    }

    //    Sign Up button
    fun signUpButton(view: View?) {
        fragmentManager!!.navigateToFragment("signUp")
    }

    //    Forget Pass button
    fun forgetPassButton(view: View?) {
        fragmentManager!!.navigateToFragment("forgetPass")
    }

    //    Login Button Click listener
    fun onButtonClick(view: View?) {
        if (phone.value!!.isEmpty() && password.value!!
                .isEmpty()
        ) {
            snackBarMessage!!.onFailure("ابتدا شماره موبایل و رمزعبور را وارد نمایید")
            return
        } else if (phone.value!!.isEmpty()) {
            snackBarMessage!!.onFailure("شماره موبایل را وارد نمایید")
            return
        } else if (password.value!!.isEmpty()) {
            snackBarMessage!!.onFailure("رمزعبور را وارد نمایید")
            return
        }
        //        Todo connect to server
        fragmentManager!!.navigateToFragment("home")
    } //    endregion

    //    endregion
    //    Constructor
    init {
        //        Hooks
        phone = MutableLiveData()
        password = MutableLiveData()
        //        set Data
        phone.value = ""
        password.value = ""
    }
}