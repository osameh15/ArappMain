package ir.arapp.arappmain.View.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;
import ir.arapp.arappmain.R;

public class SplashScreenActivity extends AppCompatActivity
{
    //Variables
    LottieAnimationView splashLogo;
    LottieAnimationView WiFi;
    CircularProgressButton tryAgainButton;
    TextView tryAgainText;
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
    }
}