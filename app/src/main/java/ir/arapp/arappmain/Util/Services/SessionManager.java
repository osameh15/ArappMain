package ir.arapp.arappmain.Util.Services;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class SessionManager
{
    //region variables
    //log tag
    private final static String TAG = SessionManager.class.getSimpleName();
    //Context
    private final Context _context;
    //Shared preference
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    //shared preference filename
    private static final String PREF_NAME = "arapp";
    private static final String KEY_IS_LOGGED_IN= "isLoggedIn";
    private static final String KEY_VERSION_NAME = "versionName";
    //endregion

    //Constructor
    @SuppressLint("CommitPrefEdits")
    public SessionManager(Context _context)
    {
        this._context = _context;
        //Shared preference
        int PRIVATE_MODE = 0;
        sharedPreferences = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }
    //region login manager
    //Set login and session modified
    public void setLogin(boolean isLoggedIn)
    {
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        //commit change ...
        editor.commit();

        Log.d(TAG, "user login session modified!");
    }
    // get login session modified ...
    public boolean isLoggedIn()
    {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }
    //endregion
    //region net connection
    public boolean checkConnection()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return null != networkInfo;
    }
    //endregion
    //region Version
    //Check version name...
    public String getVersionName(Context _context, Class cls)
    {
        try
        {
            ComponentName componentName = new ComponentName(_context, cls);
            PackageInfo packageInfo = _context.getPackageManager().getPackageInfo(componentName.getPackageName(), 0);
            return "Version: " + packageInfo.versionName;
        }
        catch (Exception ex)
        {
            return null;
        }
    }
    //set pop up dialog(recently changed)only once time after each app update...
    public void setStoredVersionName(String versionName)
    {
        editor.putString(KEY_VERSION_NAME, versionName);
        //commit change
        editor.commit();

        Log.d(TAG, "Version name session modified!");
    }
    //get stored version name ...
    public String getStoredVersionName()
    {
        return sharedPreferences.getString(KEY_VERSION_NAME, "");
    }
    //endregion
}
