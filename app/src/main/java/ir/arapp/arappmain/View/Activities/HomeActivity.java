package ir.arapp.arappmain.View.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ir.arapp.arappmain.R;
import ir.arapp.arappmain.databinding.ActivityHomeBinding;
import nl.joery.animatedbottombar.AnimatedBottomBar;

public class HomeActivity extends AppCompatActivity
{

//    region Variables
    ActivityHomeBinding activityHomeBinding;
    NavHostFragment navHostFragment;
    NavController navController;
    AppBarConfiguration appBarConfiguration;
//    endregion

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
//        Hooks
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.mainFragment);
//        Action bar
        setSupportActionBar(activityHomeBinding.homeToolbar);
        getSupportActionBar().hide();
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
    public boolean onSupportNavigateUp()
    {
        navController.navigateUp();
        return true;
    }
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
