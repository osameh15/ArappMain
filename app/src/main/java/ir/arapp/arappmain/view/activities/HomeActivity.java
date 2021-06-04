package ir.arapp.arappmain.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.marozzi.roundbutton.RoundButton;

import java.util.Objects;
import ir.arapp.arappmain.R;
import ir.arapp.arappmain.util.services.NavigationManager;
import ir.arapp.arappmain.util.services.SessionManager;
import ir.arapp.arappmain.util.services.SnackBarToast;
import ir.arapp.arappmain.databinding.ActivityHomeBinding;
import nl.joery.animatedbottombar.AnimatedBottomBar;

public class HomeActivity extends AppCompatActivity implements NavigationManager
{

//    region Variables
    private static final float END_SCALE = 0.9f;
    public ActivityHomeBinding activityHomeBinding;
    private NavController navController;
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
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.mainFragment);
        snackBarToast = new SnackBarToast(activityHomeBinding.getRoot());
        sessionManager = new SessionManager(this);
//        Action bar
        setSupportActionBar(activityHomeBinding.homeToolbar);
        Objects.requireNonNull(getSupportActionBar()).hide();
//        Nav Controller for Bottom Navigation
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
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
//        Inflate
        getMenuInflater().inflate(R.menu.bottom_navigation, menu);
//        Handle replace fragment
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
                shareApp();
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
                showAboutUsDialog();
            }
            else if (itemId == R.id.nav_contactUS)
            {
                showContactUsDialog();
            }
            else if (itemId == R.id.nav_question)
            {
                snackBarToast.snackBarShortTime("سوالات متداول", activityHomeBinding.bottomNavigationView);
                sessionManager.setLogin(false);
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
//                For Scale layout when drawer opened
/*                final float offsetScale = 1 - diffScaledOffset;
                activityHomeBinding.mainFragment.setScaleX(offsetScale);
                activityHomeBinding.mainFragment.setScaleY(offsetScale);*/
//                 Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = activityHomeBinding.homeLayout.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffsetDiff - xOffset;
                activityHomeBinding.homeLayout.setTranslationX(xTranslation);
            }
        });
    }
//    endregion
//        Bottom bar manager to handle bottom navigation
    private void bottomBarManager()
    {
        activityHomeBinding.bottomNavigationView.setBadgeAtTabIndex(2, new AnimatedBottomBar.Badge());
        /* Set Badge Count to bottom navigation
        new AnimatedBottomBar.Badge(
        "23",
        getResources().getColor(R.color.notificationColorRed),
        getResources().getColor(R.color.lightBack),
        12)); */
    }
//    region Dialogs
//    Recently Update Dialogs
    private void showRecentlyUpdatesDialog()
    {
        Dialog dialog = new Dialog(this);
//        Set layout inflater
        dialog.setContentView(R.layout.custom_recently_update_dialog);
//        Hooks
        ImageView close = dialog.findViewById(R.id.close_dialog);
        TextView textView = dialog.findViewById(R.id.recentlyChangesText);
//        Set action
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setText(getResources().getString(R.string.update));
        close.setOnClickListener(view -> dialog.dismiss());
//        Set background and show dialog
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
//    About us dialog
    private void showAboutUsDialog()
    {
        Dialog dialog = new Dialog(this);
//        Set layout inflater
        dialog.setContentView(R.layout.custom_about_us_dialog);
//        Hooks
        ImageView close = dialog.findViewById(R.id.close_dialog);
//        Set action
        close.setOnClickListener(view-> dialog.dismiss());
//        Set background and show dialog
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
//    Contact us dialog
    @SuppressLint("QueryPermissionsNeeded")
    private void showContactUsDialog()
    {
        Dialog dialog = new Dialog(this);
//        Set layout inflater
        dialog.setContentView(R.layout.custom_contact_us_dialog);
//        Hooks
        ImageView close = dialog.findViewById(R.id.close_dialog);
        RoundButton call = dialog.findViewById(R.id.call);
        RoundButton mail = dialog.findViewById(R.id.mail);
//        Set action
        close.setOnClickListener(view -> dialog.dismiss());
        call.setOnClickListener(view ->
        {
            Uri number = Uri.parse("tel:+989112834604");
            Intent dial = new Intent(Intent.ACTION_DIAL, number);
            try
            {
                startActivity(dial);
            }
            catch (android.content.ActivityNotFoundException ex)
            {
                snackBarToast.snackBarShortTime("هیچ کلاینتی برای تماس یافت نشد!");
            }
        });
        mail.setOnClickListener(view ->
        {
            Intent email = new Intent(Intent.ACTION_SEND);
            email.setType("message/rfc822");
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{"Sajjad.Haghzad@gmail.com"});
            email.putExtra(Intent.EXTRA_SUBJECT, "گزارش مشکلات/ارتباط با ما");
            email.putExtra(Intent.EXTRA_TEXT, "");
            try
            {
                startActivity(Intent.createChooser(email, "ارسال ایمیل از طریق:"));
            }
            catch (android.content.ActivityNotFoundException ex)
            {
                snackBarToast.snackBarShortTime("هیچ کلاینتی برای ارسال ایمیل یافت نشد!");
            }
        });
//        Set background and show dialog
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
//    endregion
//    Share app to another one
    private void shareApp()
    {
        String message = "لینک دانلود آراپ از کافه بازار:"+"\n"+"https://arappOfficial.com";
        Intent share = new Intent(Intent.ACTION_SEND);
        share.putExtra(Intent.EXTRA_TEXT, message);
        share.setType("text/plain");
//        Choose app to send link
        try
        {
            startActivity(Intent.createChooser(share, "اشتراک گذاری از طریق:"));
        }
        catch (android.content.ActivityNotFoundException ex)
        {
            snackBarToast.snackBarShortTime("هیچ کلاینتی یافت نشد!");
        }
    }
//    Navigation Manager and handle it
//    Lock or unlock drawer navigation
    @Override
    public void setDrawerLocked(boolean shouldLock)
    {
        if (shouldLock)
        {
            activityHomeBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
        else
        {
            activityHomeBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }
//    Open or close Drawer Navigation
    @Override
    public void openDrawer()
    {
        activityHomeBinding.drawerLayout.openDrawer(GravityCompat.END);
    }
//    visible or gone visible bottom navigation
    @Override
    public void bottomNavigationVisibility(boolean setVisible)
    {
        if (setVisible)
        {
            activityHomeBinding.bottomNavigationView.setVisibility(View.VISIBLE);
        }
        else
        {
            activityHomeBinding.bottomNavigationView.setVisibility(View.GONE);
        }
    }
//    endregion
}
