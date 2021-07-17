package ir.arapp.arappmain.view.activities

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.util.Base64
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import ir.arapp.arappmain.R
import ir.arapp.arappmain.databinding.ActivitySplashScreenBinding
import ir.arapp.arappmain.model.AllServices
import ir.arapp.arappmain.model.base.GetService
import ir.arapp.arappmain.model.base.ResponseModel
import ir.arapp.arappmain.model.base.SendService
import ir.arapp.arappmain.util.server.Api
import ir.arapp.arappmain.util.services.SessionManager
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream
import kotlin.math.log


class SplashScreenActivity : AppCompatActivity() {
    //    region Variables
    private var _activitySplashScreenBinding: ActivitySplashScreenBinding? = null
    private val activitySplashScreenBinding get() = _activitySplashScreenBinding!!
    private var flag = false
    private var sessionManager: SessionManager? = null

    //    endregion
    override fun onCreate(savedInstanceState: Bundle?) {

        var retrofit = Retrofit.Builder().baseUrl(Api.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
        var api = retrofit.create(Api::class.java)

//        api.getServiceById(11).enqueue(object:Callback<ResponseModel<GetService>>{
//            override fun onResponse(
//                call: Call<ResponseModel<GetService>>,
//                response: Response<ResponseModel<GetService>>
//            ) {
//                Log.i("ResponseTAG", "get service onResponse: ${response.isSuccessful}")
//                Log.i("ResponseTAG", "get service onResponse: ${response.errorBody()?.string()}")
//            }
//
//            override fun onFailure(call: Call<ResponseModel<GetService>>, t: Throwable) {
//                Log.i("ResponseTAG", "get service onFailure: ${t.message}")
//            }
//        })

//        api.getAllServices().enqueue(object : Callback<AllServices> {
//            override fun onResponse(call: Call<AllServices>, response: Response<AllServices>) {
//                response.body()?.let {
//                    Log.i("ResponseTAG", " $it")
//                }
//            }
//
//            override fun onFailure(call: Call<AllServices>, t: Throwable) {
//                Log.i("ResponseTAG", "onFailure ${t.message}")
//            }
//        })


//        api.deleteServiceById(10).enqueue(object : Callback<ResponseBody> {
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                if (response.isSuccessful)
//                    Log.i(
//                        "ResponseTAG",
//                        " remove service successfull:   ${response.body()?.string()}"
//                    )
//                else Log.i(
//                    "ResponseTAG",
//                    "remove service: ${response.code()}  ${response.errorBody()?.string()}"
//                )
//            }
//
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                Log.i("ResponseTAG", " remove service onFailure:   ${t.message}")
//            }
//        })
        var bitmap =
            ResourcesCompat.getDrawable(resources, R.drawable.add_service, null)?.toBitmap()
        bitmap?.let { bitmap ->
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
            val encoded: String = Base64.encodeToString(byteArray, Base64.DEFAULT)
            var gson = GsonBuilder().setLenient().create()
            var service = gson.toJson(SendService(encoded, "addService451"))
            Log.i("ResponseTAG", "service: ${service}")
//            api.updateService(2, SendService(encoded, "test$33"))
//                .enqueue(object : Callback<SendService> {
//                    override fun onResponse(
//                        call: Call<SendService>,
//                        response: Response<SendService>
//                    ) {
//                        response.let {
//                            if (it.isSuccessful)
//                                Log.i("ResponseTAG", "add Service:   ${it.body()}")
//                            else Log.i(
//                                "ResponseTAG",
//                                "add Service: ${it.code()}  ${it.errorBody()?.string()}"
//                            )
//                        }
//                    }
//
//                    override fun onFailure(call: Call<SendService>, t: Throwable) {
//                        Log.i("ResponseTAG", "add Service onFailure:   ${t.message}")
//                    }
//                })


//            (3..6).forEach {
//                api.addNewService(SendService(encoded, "test$2"))
//                    .enqueue(object : Callback<SendService> {
//                        override fun onResponse(
//                            call: Call<SendService>,
//                            response: Response<SendService>
//                        ) {
//                            response.let {
//                                if (it.isSuccessful)
//                                    Log.i("ResponseTAG", "add Service:   ${it.body()}")
//                                else Log.i(
//                                    "ResponseTAG",
//                                    "add Service: ${it.code()}  ${it.errorBody()?.string()}"
//                                )
//                            }
//                        }
//                        override fun onFailure(call: Call<SendService>, t: Throwable) {
//                            Log.i("ResponseTAG", "add Service onFailure:   ${t.message}")
//                        }
//                    })
//            }

        }


//       Inflate layout
        super.onCreate(savedInstanceState)
        _activitySplashScreenBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)

//        Set Fullscreen
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        //        Hooks
        sessionManager = SessionManager(this)
        //        Try again click listener
        activitySplashScreenBinding.tryAgain.setOnClickListener { view -> loading() }
        //        Check User logged in
        flag = sessionManager!!.isLoggedIn
        //        Check net connection
        checkInternet()
    }

    //    region methods
    //    Check Internet Connection
    private fun checkInternet() {
        if (sessionManager!!.checkConnection()) {
//            Set timer to go to next activity
            val SPLASH_SCREEN = 2400
            if (flag) {
                Handler().postDelayed({ goToHomeActivity() }, SPLASH_SCREEN.toLong())
            } else {
                Handler().postDelayed({ goToRegLogActivity() }, SPLASH_SCREEN.toLong())
            }
        } else {
            activitySplashScreenBinding!!.tryAgainLinearLayout.visibility = View.VISIBLE
            activitySplashScreenBinding!!.tryAgain.visibility = View.VISIBLE
        }
    }

    //    GoTo another activities
    private fun goToRegLogActivity() {
        val regLogActivity = Intent(this@SplashScreenActivity, AuthActivity::class.java)
        startActivity(regLogActivity)
        finish()
    }

    private fun goToHomeActivity() {
        val homeActivity = Intent(this@SplashScreenActivity, HomeActivity::class.java)
        startActivity(homeActivity)
        finish()
    }

    //    Try again connection
    private fun loading() {
        val TRY_AGAIN = 1000
        activitySplashScreenBinding!!.tryAgain.startAnimation()
        Handler().postDelayed({ tryAgain() }, TRY_AGAIN.toLong())
    }

    private fun tryAgain() {
        if (sessionManager!!.checkConnection()) {
//            Check User logged in
            if (flag) {
                goToHomeActivity()
            } else {
                goToRegLogActivity()
            }
        } else {
            activitySplashScreenBinding!!.tryAgain.revertAnimation()
        }
    } //    endregion
}