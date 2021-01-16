package ir.arapp.arappmain.View.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;
import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Services.SessionManager;

public class SplashScreenActivity extends AppCompatActivity
{
    //region Variables
    LottieAnimationView splashLogo;
    LottieAnimationView WiFi;
    CircularProgressButton tryAgainButton;
    TextView tryAgainText;
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
        WiFi = findViewById(R.id.wifiLogo);
        tryAgainButton = findViewById(R.id.tryAgain);
        tryAgainText = findViewById(R.id.tryAgainText);
        sessionManager = new SessionManager(this);

        //Set background drawable for try button ...
        tryAgainButton.setBackgroundResource(R.drawable.button_back);

        //Check net connection
        checkInternet();
    }

    //Check Internet Connection
    private void checkInternet()
    {
        if (sessionManager.checkConnection())
        {
            //set timer to go to next activity
            int SPLASH_SCREEN = 2100;
            //Check User logged in
            if (sessionManager.isLoggedIn())
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
            WiFi.setVisibility(View.VISIBLE);
            tryAgainButton.setVisibility(View.VISIBLE);
            tryAgainText.setVisibility(View.VISIBLE);
        }
    }

    private void goToRegLogActivity()
    {
        Intent regLogActivity = new Intent(SplashScreenActivity.this, AuthActivity.class);
        startActivity(regLogActivity);
        finish();
    }

    private void goToHomeActivity()
    {
    }
}