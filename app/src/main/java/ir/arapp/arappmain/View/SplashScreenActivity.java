package ir.arapp.arappmain.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import ir.arapp.arappmain.R;
import ir.arapp.arappmain.ViewModel.SplashScreenViewModel;
import ir.arapp.arappmain.databinding.ActivitySplashScreenBinding;

public class SplashScreenActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }
}