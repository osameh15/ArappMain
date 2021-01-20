package ir.arapp.arappmain.View.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import com.airbnb.lottie.LottieAnimationView;
import com.marozzi.roundbutton.RoundButton;
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
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

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
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void goToRegLogActivity()
    {
        Intent regLogActivity = new Intent(SplashScreenActivity.this, AuthActivity.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(splashLogo, "logo");
        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(SplashScreenActivity.this, pairs);
        startActivity(regLogActivity, activityOptions.toBundle());
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
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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