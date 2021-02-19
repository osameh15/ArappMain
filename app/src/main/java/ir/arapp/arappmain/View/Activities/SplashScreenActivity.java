package ir.arapp.arappmain.View.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Services.SessionManager;
import ir.arapp.arappmain.databinding.ActivitySplashScreenBinding;

public class SplashScreenActivity extends AppCompatActivity
{

//    region Variables
    private ActivitySplashScreenBinding activitySplashScreenBinding;
    private Boolean flag = false;
    private SessionManager sessionManager;
//    endregion

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
//       Inflate layout
        super.onCreate(savedInstanceState);
        activitySplashScreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen);

//        Set Fullscreen
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//        Hooks
        sessionManager = new SessionManager(this);
//        Try again click listener
        activitySplashScreenBinding.tryAgain.setOnClickListener(view -> loading());
//        Check User logged in
        flag = sessionManager.isLoggedIn();
//        Check net connection
        checkInternet();
    }

//    region methods
//    Check Internet Connection
    private void checkInternet()
    {
        if (sessionManager.checkConnection())
        {
//            Set timer to go to next activity
            int SPLASH_SCREEN = 2400;

            if (flag)
            {
                new Handler().postDelayed(this::goToHomeActivity, SPLASH_SCREEN);
            }
            else
            {
                new Handler().postDelayed(this::goToRegLogActivity, SPLASH_SCREEN);
            }
        }
        else
        {
            activitySplashScreenBinding.tryAgainLinearLayout.setVisibility(View.VISIBLE);
            activitySplashScreenBinding.tryAgain.setVisibility(View.VISIBLE);
        }
    }
//    GoTo another activities
    private void goToRegLogActivity()
    {
        Intent regLogActivity = new Intent(SplashScreenActivity.this, AuthActivity.class);
        startActivity(regLogActivity);
        finish();
    }
    private void goToHomeActivity()
    {
        Intent homeActivity = new Intent(SplashScreenActivity.this, HomeActivity.class);
        startActivity(homeActivity);
        finish();
    }
//    Try again connection
    private void loading()
    {
        int TRY_AGAIN = 1000;
        activitySplashScreenBinding.tryAgain.startAnimation();
        new Handler().postDelayed(this::tryAgain, TRY_AGAIN);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void tryAgain()
    {
        if (sessionManager.checkConnection())
        {
//            Check User logged in
            if (flag)
            {
                goToHomeActivity();
            }
            else
            {
                goToRegLogActivity();
            }
        }
        else
        {
            activitySplashScreenBinding.tryAgain.revertAnimation();
        }
    }
//    endregion
}