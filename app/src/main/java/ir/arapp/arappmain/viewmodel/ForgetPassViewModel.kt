package ir.arapp.arappmain.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.os.CountDownTimer
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ir.arapp.arappmain.R
import ir.arapp.arappmain.model.base.*
import ir.arapp.arappmain.util.server.RetrofitClient
import ir.arapp.arappmain.util.services.FragmentManager
import ir.arapp.arappmain.util.services.SnackBarMessage
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.regex.Pattern

class ForgetPassViewModel(application: Application) : AndroidViewModel(application) {
    @JvmField
    var phone: MutableLiveData<String>
    @JvmField
    var validate: MutableLiveData<String>
    @JvmField
    var flag: MutableLiveData<Boolean>
    private val checkTime // check current time is start();
            : MutableLiveData<Boolean>
    var currentTime: MutableLiveData<Long>
    @JvmField
    var time: MutableLiveData<String>
    @JvmField
    var timeColor: MutableLiveData<Int>
    private var countDownTimer: CountDownTimer? = null
    @JvmField
    var password: MutableLiveData<String>
    @JvmField
    var cnfPassword: MutableLiveData<String>
    var snackBarMessage: SnackBarMessage? = null
    var fragmentManager: FragmentManager? = null

    //    region method
    //    Initialize Methods
    private fun init() {
        phone.value = ""
        validate.value = ""
        checkTime.value = false
        currentTime.value = 0L
        flag.value = false
        password.value = ""
        cnfPassword.value = ""
        time.value = ""
        timeColor.value =
            getApplication<Application>().resources.getColor(R.color.colorAccentDark)
    }

    //    On Cleared
    override fun onCleared() {
        super.onCleared()
        checkTime.value?.let {
            countDownTimer!!.cancel()
        }
    }

    //    on Button click listener
    fun onPhoneButtonClick(view: View?) {
        phone.value?.let {
            if (it.isEmpty()){
                snackBarMessage!!.onFailure("شماره موبایل را وارد نمایید")
                return
            }
        }
        //        Start Countdown timer
        if (currentTime.value == 0L) {
            if (flag.value == true) {
                resendCode(view)
            } else {
                startCountDownTimer()
            }
        }
        //       to do on response. connect to server and send sms code
        RetrofitClient.api.forgetRequest(RegisterBody(phone.value!!)).enqueue(object :Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    fragmentManager!!.navigateToFragment("validate")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }
        })

    }

    //    validate code
    fun validateCodeNumber(view: View?) {
//       to do connect to server and check validate code
        if (Objects.requireNonNull(validate.value) == "232323") {
            fragmentManager!!.navigateToFragment("forgetPass")
        } else {
            if (validate.value!!.length == 0) {
                fragmentManager!!.setFunction("error")
                snackBarMessage!!.onFailure("ابتدا کد ارسال شده را وارد نمایید")
            } else {
                RetrofitClient.api.verifyUser(Verify(7,validate.value!!.toInt())).enqueue(object:
                    Callback<ResponseModel<GetToken>>{
                    override fun onResponse(
                        call: Call<ResponseModel<GetToken>>,
                        response: Response<ResponseModel<GetToken>>
                    ) {

                    }

                    override fun onFailure(call: Call<ResponseModel<GetToken>>, t: Throwable) {

                    }
                })
//                fragmentManager!!.setFunction("error")
//                snackBarMessage!!.onFailure("کد وارد شده صحیح نیست")
            }
        }
    }

    //    resend Code
    fun resendCode(view: View?) {
//       to do send validate code again
        flag.value = false
        validate.value = ""
        fragmentManager!!.setFunction("resend")
        startCountDownTimer()
    }

    //    Countdown timer
    fun startCountDownTimer() {
        checkTime.value = true
        countDownTimer = object : CountDownTimer(120000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                currentTime.value = millisUntilFinished / 1000
                setTimer(currentTime)
            }

            override fun onFinish() {
                currentTime.value = 0L
                setTimer(currentTime)
                fragmentManager!!.setFunction("editPhone")
                flag.value = true
                checkTime.value = false
            }
        }
        countDownTimer?.start()
    }

    //    Timer color and text
    private fun setTimer(s: MutableLiveData<Long>) {
        val min = s.value!! / 60
        val sec = s.value!! % 60
        @SuppressLint("DefaultLocale") val timeToFormat = String.format("%02d:%02d", min, sec)
        time.value = timeToFormat
        if (s.value!! < 10) {
            timeColor.setValue(getApplication<Application>().resources.getColor(R.color.notificationColorRed))
        } else {
            timeColor.setValue(getApplication<Application>().resources.getColor(R.color.colorAccentDark))
        }
    }

    //    edit phone number
    fun editPhoneNumber(view: View?) {
        fragmentManager!!.navigateToFragment("phone")
    }

    //    check password and confirm
    fun checkPassword(view: View?) {
        if (password.value!!.isEmpty()) {
            snackBarMessage!!.onFailure("رمز عبور را وارد نمایید")
            return
        } else if (password.value!!.length < 6) {
            snackBarMessage!!.onFailure("رمزعبور حداقل باید دارای 6 کاراکتر باشد")
            return
        } else if (!PASSWORD_PATTERN.matcher(password.value).matches()) {
            snackBarMessage!!.onSuccess("رمزعبور باید ترکیبی از عدد و حروف بزرگ و کوچک باشد")
            return
        } else if (password.value != cnfPassword.value) {
            snackBarMessage!!.onFailure("رمزعبور های وارد شده یکسان نیستند")
            return
        }
        //       to do connect to sever and submit the last changes
        snackBarMessage!!.onSuccess("رمز عبور شما با موفقیت تغییر کرد")
        fragmentManager!!.navigateToFragment("login")
    } //    endregion

    companion object {
        //    region Variable
        //    Password pattern
        private val PASSWORD_PATTERN = Pattern.compile(
            "^" +
                    "(?=.*[0-9])" +  //at least 1 digit
                    "(?=.*[a-z])" +  //at least 1 lower case letter
                    "(?=.*[A-Z])" +  //at least 1 upper case letter
                    //                    "(?=.*[@#$%&+=)])" +            //at least 1 special character
                    "(?=\\S+$)" +  //no white space
                    ".{6,}" +  //at least 6 character
                    "$"
        )
    }

    //    endregion
    //    Constructor
    init {
        //        Hooks
        phone = MutableLiveData()
        validate = MutableLiveData()
        checkTime = MutableLiveData()
        currentTime = MutableLiveData()
        flag = MutableLiveData()
        password = MutableLiveData()
        cnfPassword = MutableLiveData()
        time = MutableLiveData()
        timeColor = MutableLiveData()
        //        set data
        init()
    }
}