package ir.arapp.arappmain.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ir.arapp.arappmain.model.base.LoginMobile
import ir.arapp.arappmain.model.base.LoginToken
import ir.arapp.arappmain.model.base.ResponseModel
import ir.arapp.arappmain.util.server.RetrofitClient
import ir.arapp.arappmain.util.services.FragmentManager
import ir.arapp.arappmain.util.services.SessionManager
import ir.arapp.arappmain.util.services.SnackBarMessage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
    fun onLoginButtonClick(view: View?) {
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
        Log.i("TAG123123", "onResponse body: start call ${phone.value!!} , ${password.value!!}")
        RetrofitClient.api.loginUser(LoginMobile(phone.value!!,password.value!!)).enqueue(object :
            Callback<ResponseModel<List<LoginToken>>>{
            override fun onResponse(
                call: Call<ResponseModel<List<LoginToken>>>,
                response: Response<ResponseModel<List<LoginToken>>>
            ) {
                Log.i("TAG123123", "onResponse body: ${response.body()?.toString()}")
                Log.i("TAG123123", "onResponse errorBody : ${response.errorBody()?.string()}")
                if (response.isSuccessful){
                    SessionManager(view!!.context).setUserInfo(phone.value!!,password.value!!)
                    fragmentManager!!.navigateToFragment("home")
                }
            }

            override fun onFailure(call: Call<ResponseModel<List<LoginToken>>>, t: Throwable) {
                Log.i("TAG123123", "onFailure message: ${t.message}")
            }
        })

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