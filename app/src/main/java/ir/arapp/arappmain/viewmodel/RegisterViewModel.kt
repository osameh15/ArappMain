package ir.arapp.arappmain.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ir.arapp.arappmain.R
import ir.arapp.arappmain.model.base.*
import ir.arapp.arappmain.util.server.RetrofitClient
import ir.arapp.arappmain.util.services.FragmentManager
import ir.arapp.arappmain.util.services.SnackBarMessage
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import java.util.*
import java.util.regex.Pattern
import javax.security.auth.callback.Callback

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    var id: MutableLiveData<Int> = MutableLiveData(0)
    var token:MutableLiveData<String> = MutableLiveData("")
    var email:MutableLiveData<String> = MutableLiveData("")

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

    @JvmField
    var name: MutableLiveData<String>

    @JvmField
    var password: MutableLiveData<String>

    @JvmField
    var cnfPassword: MutableLiveData<String>

    @JvmField
    var checkBox: MutableLiveData<Boolean>
    var service: MutableLiveData<String>
    private var countDownTimer: CountDownTimer? = null
    var snackBarMessage: SnackBarMessage? = null
    var fragmentManager: FragmentManager? = null

    //    region method
    //    Initialize methods
    private fun init() {
        phone.value = ""
        flag.value = false
        checkTime.value = false
        currentTime.value = 0L
        validate.value = ""
        time.value = ""
        timeColor.value =
            getApplication<Application>().resources.getColor(R.color.colorAccentDark)
        name.value = ""
        password.value = ""
        cnfPassword.value = ""
        checkBox.value = false
        service.value = ""
    }

    //    On Cleared
    override fun onCleared() {
        super.onCleared()
        if (checkTime.value!!) {
            countDownTimer!!.cancel()
        }
    }

    //    Submit phone Button
    fun onPhoneButtonClick(view: View?) {
        if (phone.value!!.isEmpty()) {
            snackBarMessage!!.onFailure("شماره موبایل را وارد نمایید")
            return
        } else if (!phone.value!!.startsWith("09") || phone.value!!
                .startsWith("094") || phone.value!!.startsWith("095") ||
            phone.value!!.startsWith("096") || phone.value!!
                .startsWith("097") || phone.value!!.startsWith("098") || phone.value!!.length != 11
        ) {
            snackBarMessage!!.onFailure("شماره موبایل با فرمت درست وارد نشده است")
            return
        }
        phone.value?.let {
            RetrofitClient.api.registerUser(RegisterBody(it))
                .enqueue(object : retrofit2.Callback<ResponseModel<RegisterResult>> {
                    override fun onResponse(
                        call: Call<ResponseModel<RegisterResult>>,
                        response: Response<ResponseModel<RegisterResult>>
                    ) {
                        if (response.isSuccessful){
                            response.body()?.result?.id?.let {
                                id.value = it
                                fragmentManager!!.navigateToFragment("validate")
                            }
                        }

                    }

                    override fun onFailure(
                        call: Call<ResponseModel<RegisterResult>>,
                        t: Throwable
                    ) {

                    }
                })
        }

//        Start Countdown timer
        if (currentTime.value == 0L) {
            if (flag.value == true) {
                resendCode(view)
            } else {
                startCountDownTimer()
            }
        }

    }

    //    Login Button
    fun loginButton() {
        fragmentManager!!.navigateToFragment("login")
    }

    //    validate code
    fun validateCodeNumber(view: View?) {
        if (Objects.requireNonNull(validate.value)!!.toInt() > 1) {
            RetrofitClient.api.verifyUser(Verify(id.value!!, validate.value!!.toInt())).enqueue(object :
                retrofit2.Callback<ResponseModel<GetToken>> {
                override fun onResponse(
                    call: Call<ResponseModel<GetToken>>,
                    response: Response<ResponseModel<GetToken>>
                ) {
                    if (response.isSuccessful){
                        response.body()?.result?.token?.let {
                            token.value = it
                            fragmentManager!!.navigateToFragment("register")
                        }

                    }

                }

                override fun onFailure(call: Call<ResponseModel<GetToken>>, t: Throwable) {

                }
            })

        } else {
            if (validate.value!!.length == 0) {
                fragmentManager!!.setFunction("error")
                snackBarMessage!!.onFailure("ابتدا کد ارسال شده را وارد نمایید")
            } else {
                fragmentManager!!.setFunction("error")
                snackBarMessage!!.onFailure("کد وارد شده صحیح نیست")
            }
        }
    }

    //    resend Code
    fun resendCode(view: View?) {
//        Todo connect to server and send validation code again
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
        fragmentManager!!.navigateToFragment("phoneRegister")
    }

    //    Check accept application law
    fun setCheckBox(view: View?) {
        if (checkBox.value == false) {
            checkBox.setValue(true)
        } else if (checkBox.value == true) {
            checkBox.value = false
        }
    }

    fun readPrivacy(view: View?) {
        fragmentManager!!.setFunction("privacy")
    }

    //    Ob Register Button Click
    fun onRegisterButtonClick(view: View) {
        if (name.value!!.isEmpty() &&
            password.value!!.isEmpty() &&
            cnfPassword.value!!.isEmpty() &&
            service.value!!.isEmpty()
        ) {
            snackBarMessage!!.onFailure("ابتدا مشخصات خود را تکمیل نمایید")
        } else if (name.value!!.isEmpty()) {
            snackBarMessage!!.onFailure("نام خود را وارد نمایید")
        } else if (password.value!!.isEmpty()) {
            snackBarMessage!!.onFailure("رمز عبور را وارد نمایید")
        } else if (service.value!!.isEmpty()) {
            snackBarMessage!!.onFailure("نوع سرویس خود را مشخص نمایید")
        } else if (checkPassword(view)) {
            RetrofitClient.api.setUserInfo(
                UserInfo(
                    name.value!!,
                    email.value!!,
                    service.value!!,
                    password.value!!,
                    cnfPassword.value!!,
                    token.value!!
                )
            )
                .enqueue(object : retrofit2.Callback<ResponseModel<LoginToken>> {
                    override fun onResponse(
                        call: Call<ResponseModel<LoginToken>>,
                        response: Response<ResponseModel<LoginToken>>
                    ) {
                        if (response.isSuccessful){
                            fragmentManager!!.navigateToFragment("home")
                        } else{

                        }
                    }

                    override fun onFailure(call: Call<ResponseModel<LoginToken>>, t: Throwable) {

                    }
                })
        }
    }

    //    Check type of service
    fun setService(view: View?) {
        service.value = "provider"
    }

    fun getService(view: View?) {
        service.value = "receiver"
    }

    //    check password and confirm
    private fun checkPassword(view: View): Boolean {
        if (password.value!!.length < 6) {
            snackBarMessage!!.onFailure("رمزعبور حداقل باید دارای 6 کاراکتر باشد")
            return false
        } else if (!PASSWORD_PATTERN.matcher(password.value).matches()) {
            snackBarMessage!!.onSuccess("رمزعبور باید ترکیبی از عدد و حروف بزرگ و کوچک باشد")
            return false
        } else if (password.value != cnfPassword.value) {
            snackBarMessage!!.onFailure("رمزعبور های وارد شده یکسان نیستند")
            return false
        }
        return true
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
        flag = MutableLiveData()
        checkTime = MutableLiveData()
        currentTime = MutableLiveData()
        time = MutableLiveData()
        timeColor = MutableLiveData()
        name = MutableLiveData()
        password = MutableLiveData()
        cnfPassword = MutableLiveData()
        checkBox = MutableLiveData()
        service = MutableLiveData()
        //        Initialize
        init()
    }
}