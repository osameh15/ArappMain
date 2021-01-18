package ir.arapp.arappmain.View.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import com.marozzi.roundbutton.RoundButton;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;
import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Services.SessionManager;

public class SplashScreenActivity extends AppCompatActivity
{
    //region Variables
    private Boolean flag = false;
    LottieAnimationView splashLogo;
    LinearLayout tryAgainLinearLayout;
    RoundButton tryAgainButton;
    //Session manager
    private SessionManager sessionManager;
    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Set Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Hooks
        splashLogo = findViewById(R.id.splashLogo);
        tryAgainLinearLayout = findViewById(R.id.tryAgainLinearLayout);
        tryAgainButton = findViewById(R.id.tryAgain);
        sessionManager = new SessionManager(this);

        //Try again click listener
        tryAgainButton.setOnClickListener(view -> loading());

        //Check User logged in
        flag = sessionManager.isLoggedIn();

        //Check net connection
        checkInternet();
    }

    //Check Internet Connection
    private void checkInternet()
    {
        if (sessionManager.checkConnection())
        {
            //set timer to go to next activity
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
            tryAgainLinearLayout.setVisibility(View.VISIBLE);
            tryAgainButton.setVisibility(View.VISIBLE);
        }
    }

    //region goTo another activities
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
    //endregion

    //region try again connection
    private void loading()
    {
        int TRY_AGAIN = 1000;
        tryAgainButton.startAnimation();
        new Handler().postDelayed(this::tryAgain, TRY_AGAIN);
    }
    private void tryAgain()
    {
        if (sessionManager.checkConnection())
        {
            //Check User logged in
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
            tryAgainButton.revertAnimation();
        }
    }
    //endregion
}