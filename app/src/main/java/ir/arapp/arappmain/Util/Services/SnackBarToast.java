package ir.arapp.arappmain.Util.Services;

import android.content.Context;
import android.view.View;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class SnackBarToast
{
    //region Variable
    View view;
    //endregion

    //Constructor
    public SnackBarToast(View view)
    {
        this.view = view;
    }
    //region Methods
    public void snackBarShortTime(String message)
    {
        Snackbar snackbar = Snackbar.make(view, message, BaseTransientBottomBar.LENGTH_SHORT);
        snackbar.setDuration(1500);
        snackbar.show();
    }
    public void snackBarLongTime(String message)
    {
        Snackbar snackbar = Snackbar.make(view, message, BaseTransientBottomBar.LENGTH_LONG);
        snackbar.setDuration(3000);
        snackbar.show();
    }
    //endregion
}
