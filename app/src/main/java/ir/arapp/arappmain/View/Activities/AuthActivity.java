package ir.arapp.arappmain.View.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import ir.arapp.arappmain.R;

public class AuthActivity extends AppCompatActivity
{

    //region Variable
    private static final int PERMISSION_CODE = 1001;
    FragmentContainerView fragmentContainerView;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        //Hooks
        fragmentContainerView = findViewById(R.id.authFragment);
    }

    //handle result of runtime permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if (requestCode == PERMISSION_CODE)
        {
            if (grantResults.length > 0)
            {
                boolean storagePermission = grantResults[0] != PackageManager.PERMISSION_GRANTED;
                boolean locationPermission = grantResults[1] != PackageManager.PERMISSION_GRANTED;
                if (storagePermission && locationPermission)
                {
                    //permission was denied
                        Snackbar snackbar = Snackbar.make(fragmentContainerView,
                                "اجازه دسترسی داده نشد!",
                                BaseTransientBottomBar.LENGTH_LONG);
                        snackbar.setDuration(2500);
                        snackbar.show();
                }
                else if (storagePermission)
                {
                    //permission was denied
                    Snackbar snackbar = Snackbar.make(fragmentContainerView,
                            "اجازه دسترسی به کارت حافظه داده نشد!",
                            BaseTransientBottomBar.LENGTH_LONG);
                    snackbar.setDuration(2500);
                    snackbar.show();
                }
                else if (locationPermission)
                {
                    //permission was denied
                    Snackbar snackbar = Snackbar.make(fragmentContainerView,
                            "اجازه دسترسی به موقعیت مکانی داده نشد!",
                            BaseTransientBottomBar.LENGTH_LONG);
                    snackbar.setDuration(2500);
                    snackbar.show();
                }
            }
        }
    }
}