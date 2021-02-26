package ir.arapp.arappmain.View.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Services.SessionManager;
import ir.arapp.arappmain.Util.Services.SnackBarToast;
import ir.arapp.arappmain.databinding.ActivityHomeBinding;
import nl.joery.animatedbottombar.AnimatedBottomBar;

public class HomeActivity extends AppCompatActivity
{

//    region Variables
    ActivityHomeBinding activityHomeBinding;
    NavHostFragment navHostFragment;
    NavController navController;
    AppBarConfiguration appBarConfiguration;
    private SnackBarToast snackBarToast;
    private long backPressedTime;
//    endregion

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
//        Hooks
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.mainFragment);
        snackBarToast = new SnackBarToast(activityHomeBinding.getRoot());
//        Action bar
        setSupportActionBar(activityHomeBinding.homeToolbar);
        Objects.requireNonNull(getSupportActionBar()).hide();
//        Nav Controller for Bottom Navigation
        navController = navHostFragment.getNavController();
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        Bottom navigation Manager
        bottomBarManager();
    }

//    region Methods

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
//        Handle replace fragment
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.bottom_navigation, menu);
        activityHomeBinding.bottomNavigationView.setupWithNavController(menu, navController);
        return true;
    }
    @Override
    public void onBackPressed()
    {
        if (Objects.equals(Objects.requireNonNull(navController.getCurrentDestination()).getLabel(), "صفحه اصلی"))
        {
            snackBarToast.snackBarShortTime("جهت خروج از برنامه، بازگشت را مجددا فشار دهید!", activityHomeBinding.bottomNavigationView);
            if (backPressedTime + 2000 > System.currentTimeMillis())
            {
                finishAffinity();
            }
            backPressedTime = System.currentTimeMillis();
        }
        else
        {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onSupportNavigateUp()
    {
        navController.navigateUp();
        return true;
    }
    //    Bottom bar manager to handle bottom navigation
    private void bottomBarManager()
    {
        activityHomeBinding.bottomNavigationView.setBadgeAtTabIndex(2, new AnimatedBottomBar.Badge());
    }
//    endregion
}

/* Set Badge Count to bottom navigation
new AnimatedBottomBar.Badge(
        "23",
        getResources().getColor(R.color.notificationColorRed),
        getResources().getColor(R.color.lightBack),
        12));*/
