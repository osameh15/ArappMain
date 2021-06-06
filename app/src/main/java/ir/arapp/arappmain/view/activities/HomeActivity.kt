package ir.arapp.arappmain.view.activities

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.SimpleDrawerListener
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.marozzi.roundbutton.RoundButton
import ir.arapp.arappmain.R
import ir.arapp.arappmain.databinding.ActivityHomeBinding
import ir.arapp.arappmain.util.services.NavigationManager
import ir.arapp.arappmain.util.services.SessionManager
import ir.arapp.arappmain.util.services.SnackBarToast
import nl.joery.animatedbottombar.AnimatedBottomBar
import java.util.*

class HomeActivity : AppCompatActivity(), NavigationManager {
    var _activityHomeBinding: ActivityHomeBinding? = null
    val activityHomeBinding get() = _activityHomeBinding!!
    private var navController: NavController? = null
    private var snackBarToast: SnackBarToast? = null
    private var backPressedTime: Long = 0
    private var versionName: String? = null
    private var sessionManager: SessionManager? = null

    //    endregion
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        //        Hooks
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainFragment) as NavHostFragment?
        snackBarToast = SnackBarToast(activityHomeBinding.getRoot())
        sessionManager = SessionManager(this)
        //        Action bar
        setSupportActionBar(activityHomeBinding.homeToolbar)
        Objects.requireNonNull(supportActionBar)!!.hide()
        assert(navHostFragment != null)
        navController = navHostFragment!!.navController
        val appBarConfiguration = AppBarConfiguration.Builder(navController!!.graph).build()
        NavigationUI.setupActionBarWithNavController(this, navController!!, appBarConfiguration)
        //        Navigation drawer manager
        navigationDrawerManager()
        //        Bottom navigation Manager
        bottomBarManager()
        //        Check app version every time on lunch home screen
        versionName = sessionManager!!.getVersionName(this, HomeActivity::class.java)
        checkAppVersion()
    }

    //    region Methods
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        Inflate
        menuInflater.inflate(R.menu.bottom_navigation, menu)
        //        Handle replace fragment
        activityHomeBinding!!.bottomNavigationView.setupWithNavController(menu, navController!!)
        return true
    }

    override fun onBackPressed() {
        if (activityHomeBinding!!.drawerLayout.isDrawerVisible(GravityCompat.END)) {
            activityHomeBinding!!.drawerLayout.closeDrawer(GravityCompat.END)
        } else {
            if (Objects.requireNonNull(navController!!.currentDestination)!!.label == "صفحه اصلی") {
                snackBarToast!!.snackBarShortTime(
                    "جهت خروج از برنامه، بازگشت را مجددا فشار دهید!",
                    activityHomeBinding!!.bottomNavigationView
                )
                if (backPressedTime + 2000 > System.currentTimeMillis()) {
                    finishAffinity()
                }
                backPressedTime = System.currentTimeMillis()
            } else {
                super.onBackPressed()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        navController!!.navigateUp()
        return true
    }

    //    Check application version in lunch app at home screen every time
    private fun checkAppVersion() {
        val storedVersionName = sessionManager!!.storedVersionName
        if (storedVersionName == "") {
            sessionManager!!.storedVersionName = versionName
        } else if (storedVersionName != versionName) {
            showRecentlyUpdatesDialog()
        }
        sessionManager!!.storedVersionName = versionName
    }

    //    region Navigation drawer
    //   Navigation Drawer Manager and animated drawer layout
    private fun navigationDrawerManager() {
        activityHomeBinding!!.navigationDrawer.bringToFront()
        activityHomeBinding!!.navigationDrawer.setNavigationItemSelectedListener { item ->
            val itemId = item.itemId
            if (itemId == R.id.nav_faves) {
                snackBarToast!!.snackBarShortTime(
                    "نشان شده ها",
                    activityHomeBinding!!.bottomNavigationView
                )
            } else if (itemId == R.id.nav_newsReceive) {
                snackBarToast!!.snackBarShortTime(
                    "دریافت اعلان",
                    activityHomeBinding!!.bottomNavigationView
                )
            } else if (itemId == R.id.nav_inviteFriends) {
                shareApp()
            } else if (itemId == R.id.nav_rating) {
                snackBarToast!!.snackBarShortTime(
                    "امتیاز به آراپ",
                    activityHomeBinding!!.bottomNavigationView
                )
            } else if (itemId == R.id.nav_recentUpdates) {
                showRecentlyUpdatesDialog()
            } else if (itemId == R.id.nav_aboutUs) {
                showAboutUsDialog()
            } else if (itemId == R.id.nav_contactUS) {
                showContactUsDialog()
            } else if (itemId == R.id.nav_question) {
                snackBarToast!!.snackBarShortTime(
                    "سوالات متداول",
                    activityHomeBinding!!.bottomNavigationView
                )
                sessionManager!!.setLogin(false)
            }
            activityHomeBinding!!.drawerLayout.closeDrawer(GravityCompat.END)
            true
        }
        animatedDrawerLayout()
    }

    private fun animatedDrawerLayout() {
        activityHomeBinding!!.drawerLayout.addDrawerListener(object : SimpleDrawerListener() {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
//                 Scale the View based on current slide offset
                val diffScaledOffset = slideOffset * (1 - END_SCALE)
                //                For Scale layout when drawer opened
/*                final float offsetScale = 1 - diffScaledOffset;
                activityHomeBinding.mainFragment.setScaleX(offsetScale);
                activityHomeBinding.mainFragment.setScaleY(offsetScale);*/
//                 Translate the View, accounting for the scaled width
                val xOffset = drawerView.width * slideOffset
                val xOffsetDiff = activityHomeBinding!!.homeLayout.width * diffScaledOffset / 2
                val xTranslation = xOffsetDiff - xOffset
                activityHomeBinding!!.homeLayout.translationX = xTranslation
            }
        })
    }

    //    endregion
    //        Bottom bar manager to handle bottom navigation
    private fun bottomBarManager() {
        activityHomeBinding.bottomNavigationView.setBadgeAtTabIndex(2, AnimatedBottomBar.Badge())
        /* Set Badge Count to bottom navigation
        new AnimatedBottomBar.Badge(
        "23",
        getResources().getColor(R.color.notificationColorRed),
        getResources().getColor(R.color.lightBack),
        12)); */
    }

    //    region Dialogs
    //    Recently Update Dialogs
    private fun showRecentlyUpdatesDialog() {
        val dialog = Dialog(this)
        //        Set layout inflater
        dialog.setContentView(R.layout.custom_recently_update_dialog)
        //        Hooks
        val close = dialog.findViewById<ImageView>(R.id.close_dialog)
        val textView = dialog.findViewById<TextView>(R.id.recentlyChangesText)
        //        Set action
        textView.movementMethod = ScrollingMovementMethod()
        textView.text = resources.getString(R.string.update)
        close.setOnClickListener { view: View? -> dialog.dismiss() }
        //        Set background and show dialog
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    //    About us dialog
    private fun showAboutUsDialog() {
        val dialog = Dialog(this)
        //        Set layout inflater
        dialog.setContentView(R.layout.custom_about_us_dialog)
        //        Hooks
        val close = dialog.findViewById<ImageView>(R.id.close_dialog)
        //        Set action
        close.setOnClickListener { view: View? -> dialog.dismiss() }
        //        Set background and show dialog
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    //    Contact us dialog
    @SuppressLint("QueryPermissionsNeeded")
    private fun showContactUsDialog() {
        val dialog = Dialog(this)
        //        Set layout inflater
        dialog.setContentView(R.layout.custom_contact_us_dialog)
        //        Hooks
        val close = dialog.findViewById<ImageView>(R.id.close_dialog)
        val call: RoundButton = dialog.findViewById(R.id.call)
        val mail: RoundButton = dialog.findViewById(R.id.mail)
        //        Set action
        close.setOnClickListener { view: View? -> dialog.dismiss() }
        call.setOnClickListener { view: View? ->
            val number = Uri.parse("tel:+989112834604")
            val dial = Intent(Intent.ACTION_DIAL, number)
            try {
                startActivity(dial)
            } catch (ex: ActivityNotFoundException) {
                snackBarToast!!.snackBarShortTime("هیچ کلاینتی برای تماس یافت نشد!")
            }
        }
        mail.setOnClickListener { view: View? ->
            val email = Intent(Intent.ACTION_SEND)
            email.type = "message/rfc822"
            email.putExtra(Intent.EXTRA_EMAIL, arrayOf("Sajjad.Haghzad@gmail.com"))
            email.putExtra(Intent.EXTRA_SUBJECT, "گزارش مشکلات/ارتباط با ما")
            email.putExtra(Intent.EXTRA_TEXT, "")
            try {
                startActivity(Intent.createChooser(email, "ارسال ایمیل از طریق:"))
            } catch (ex: ActivityNotFoundException) {
                snackBarToast!!.snackBarShortTime("هیچ کلاینتی برای ارسال ایمیل یافت نشد!")
            }
        }
        //        Set background and show dialog
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    //    endregion
    //    Share app to another one
    private fun shareApp() {
        val message = """
             لینک دانلود آراپ از کافه بازار:
             https://arappOfficial.com
             """.trimIndent()
        val share = Intent(Intent.ACTION_SEND)
        share.putExtra(Intent.EXTRA_TEXT, message)
        share.type = "text/plain"
        //        Choose app to send link
        try {
            startActivity(Intent.createChooser(share, "اشتراک گذاری از طریق:"))
        } catch (ex: ActivityNotFoundException) {
            snackBarToast!!.snackBarShortTime("هیچ کلاینتی یافت نشد!")
        }
    }

    //    Navigation Manager and handle it
    //    Lock or unlock drawer navigation
    override fun setDrawerLocked(shouldLock: Boolean) {
        if (shouldLock) {
            activityHomeBinding!!.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        } else {
            activityHomeBinding!!.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        }
    }

    //    Open or close Drawer Navigation
    override fun openDrawer() {
        activityHomeBinding!!.drawerLayout.openDrawer(GravityCompat.END)
    }

    //    visible or gone visible bottom navigation
    override fun bottomNavigationVisibility(setVisible: Boolean) {
        if (setVisible) {
            activityHomeBinding!!.bottomNavigationView.visibility = View.VISIBLE
        } else {
            activityHomeBinding!!.bottomNavigationView.visibility = View.GONE
        }
    } //    endregion

    companion object {
        //    region Variables
        private const val END_SCALE = 0.9f
    }
}