package ir.arapp.arappmain.View.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Objects;
import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Services.SessionManager;
import ir.arapp.arappmain.Util.Services.SnackBarToast;
import ir.arapp.arappmain.databinding.ActivityHomeBinding;
import nl.joery.animatedbottombar.AnimatedBottomBar;

public class HomeActivity extends AppCompatActivity
{

//    region Variables
    private static final float END_SCALE = 0.7f;
    ActivityHomeBinding activityHomeBinding;
    NavHostFragment navHostFragment;
    NavController navController;
    AppBarConfiguration appBarConfiguration;
    private SnackBarToast snackBarToast;
    private long backPressedTime;
    private String versionName;
    private SessionManager sessionManager;
//    endregion

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
//        Hooks
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.mainFragment);
        snackBarToast = new SnackBarToast(activityHomeBinding.getRoot());
        sessionManager = new SessionManager(this);
//        Action bar
        setSupportActionBar(activityHomeBinding.homeToolbar);
        Objects.requireNonNull(getSupportActionBar()).hide();
//        Nav Controller for Bottom Navigation
        navController = navHostFragment.getNavController();
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        Navigation drawer manager
        navigationDrawerManager();
//        Bottom navigation Manager
        bottomBarManager();
//        Check app version every time on lunch home screen
        versionName = sessionManager.getVersionName(this, HomeActivity.class);
        checkAppVersion();
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
        if (activityHomeBinding.drawerLayout.isDrawerVisible(GravityCompat.END))
        {
            activityHomeBinding.drawerLayout.closeDrawer(GravityCompat.END);
        }
        else
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
    }
    @Override
    public boolean onSupportNavigateUp()
    {
        navController.navigateUp();
        return true;
    }
//    Check application version in lunch app at home screen every time
    private void checkAppVersion()
    {
        String storedVersionName = sessionManager.getStoredVersionName();
        if (storedVersionName.equals(""))
        {
            sessionManager.setStoredVersionName(versionName);
        }
        else if (!storedVersionName.equals(versionName))
        {
            showRecentlyUpdatesDialog();
        }
        sessionManager.setStoredVersionName(versionName);
    }
//    region Navigation drawer
//   Navigation Drawer Manager and animated drawer layout
    private void navigationDrawerManager()
    {
        activityHomeBinding.navigationDrawer.bringToFront();
        activityHomeBinding.navigationDrawer.setNavigationItemSelectedListener(item ->
        {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_faves)
            {
                snackBarToast.snackBarShortTime("نشان شده ها", activityHomeBinding.bottomNavigationView);
            }
            else if (itemId == R.id.nav_newsReceive)
            {
                snackBarToast.snackBarShortTime("دریافت اعلان", activityHomeBinding.bottomNavigationView);
            }
            else if (itemId == R.id.nav_inviteFriends)
            {
                snackBarToast.snackBarShortTime("پیشنهاد به دوستان", activityHomeBinding.bottomNavigationView);
            }
            else if (itemId == R.id.nav_rating)
            {
                snackBarToast.snackBarShortTime("امتیاز به آراپ", activityHomeBinding.bottomNavigationView);
            }
            else if (itemId == R.id.nav_recentUpdates)
            {
                showRecentlyUpdatesDialog();
            }
            else if (itemId == R.id.nav_aboutUs)
            {
                snackBarToast.snackBarShortTime("درباره ما", activityHomeBinding.bottomNavigationView);
            }
            else if (itemId == R.id.nav_contactUS)
            {
                snackBarToast.snackBarShortTime("ارتباط با ما", activityHomeBinding.bottomNavigationView);
            }
            else if (itemId == R.id.nav_question)
            {
                snackBarToast.snackBarShortTime("سوالات متداول", activityHomeBinding.bottomNavigationView);
            }
            activityHomeBinding.drawerLayout.closeDrawer(GravityCompat.END);
            return true;
        });
        animatedDrawerLayout();
    }
    private void animatedDrawerLayout()
    {
        activityHomeBinding.drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener()
        {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset)
            {
//                 Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                activityHomeBinding.mainFragment.setScaleX(offsetScale);
                activityHomeBinding.mainFragment.setScaleY(offsetScale);
//                 Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = activityHomeBinding.mainFragment.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffsetDiff - xOffset;
                activityHomeBinding.mainFragment.setTranslationX(xTranslation);
            }
        });
    }
//    endregion
//        Bottom bar manager to handle bottom navigation
    private void bottomBarManager()
    {
        activityHomeBinding.bottomNavigationView.setBadgeAtTabIndex(2, new AnimatedBottomBar.Badge());
    }
//    region Dialogs
//    Recently Update Dialogs
    private void showRecentlyUpdatesDialog()
    {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_recently_update_dialog);
        ImageView close = dialog.findViewById(R.id.close_dialog);
        TextView textView = dialog.findViewById(R.id.recentlyChangesText);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setText(getResources().getString(R.string.update));
        close.setOnClickListener(view -> dialog.dismiss());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
//    About us dialog
    private void showAboutUsDialog()
    {
        Dialog dialog = new Dialog(this);
    }
//    Contact us dialog
    private void showContactUsDialog()
    {
        Dialog dialog = new Dialog(this);
    }
//    endregion
//    endregion
}

/* Set Badge Count to bottom navigation
new AnimatedBottomBar.Badge(
        "23",
        getResources().getColor(R.color.notificationColorRed),
        getResources().getColor(R.color.lightBack),
        12));*/
