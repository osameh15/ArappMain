package ir.arapp.arappmain.view.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import ir.arapp.arappmain.R
import ir.arapp.arappmain.databinding.ActivitySplashScreenBinding
import ir.arapp.arappmain.model.base.*
import ir.arapp.arappmain.util.server.RetrofitClient
import ir.arapp.arappmain.util.services.SessionManager


class SplashScreenActivity : AppCompatActivity() {
    //    region Variables
    private var _activitySplashScreenBinding: ActivitySplashScreenBinding? = null
    private val activitySplashScreenBinding get() = _activitySplashScreenBinding!!
    private var flag = false
    private var sessionManager: SessionManager? = null

    //    endregion
    override fun onCreate(savedInstanceState: Bundle?) {
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
        RetrofitClient.login(sessionManager!!.getPhoneNumber(this)!!,sessionManager!!.getPassword(this)!!)
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