package ir.arapp.arappmain.util.services

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.util.Log

class SessionManager @SuppressLint("CommitPrefEdits") constructor(  //    Context
    private val _context: Context
) {
    //    Shared preference
    private val sharedPreferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    //    region methods
    //    Check Net connection
    fun checkConnection(): Boolean {
        val connectivityManager =
            _context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return null != networkInfo
    }

    //    region login manager
    //    Set login and session modified
    fun setLogin(isLoggedIn: Boolean) {
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn)
        //        Commit change ...
        editor.commit()
        Log.d(TAG, "user login session modified!")
    }

    //     Get login session modified ...
    val isLoggedIn: Boolean
        get() = sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)

    //    endregion
    //    region Version
    //    Check version name...
    fun getVersionName(_context: Context, cls: Class<*>?): String? {
        return try {
            val componentName = ComponentName(_context, cls!!)
            val packageInfo = _context.packageManager.getPackageInfo(componentName.packageName, 0)
            "Version: " + packageInfo.versionName
        } catch (ex: Exception) {
            null
        }
    }//        Commit change

    //    Set pop up dialog(recently changed)only once time after each app update...
    //    Get stored version name ...
    //    endregion
    var storedVersionName: String?
        get() = sharedPreferences.getString(KEY_VERSION_NAME, "")
        set(versionName) {
            editor.putString(KEY_VERSION_NAME, versionName)
            //        Commit change
            editor.commit()
            Log.d(TAG, "Version name session modified!")
        }

    //    endregion
    companion object {
        //    region variables
        //    Log tag
        private val TAG = SessionManager::class.java.simpleName

        //    Shared preference filename
        private const val PREF_NAME = "arapp"
        private const val KEY_IS_LOGGED_IN = "isLoggedIn"
        private const val KEY_VERSION_NAME = "versionName"
    }

    //    endregion
    //    Constructor
    init {
        //        Shared preference
        val PRIVATE_MODE = 0
        sharedPreferences = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = sharedPreferences.edit()
    }
}