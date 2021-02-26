package ir.arapp.arappmain.Util.Services;

import android.content.Context;
import android.view.View;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class SnackBarToast
{
//    region Variable
    View view;
//    endregion

//    Constructor
    public SnackBarToast(View view)
    {
        this.view = view;
    }

//    region Methods
//    Snack bar short time display (1.5 sec)
    public void snackBarShortTime(String message)
    {
        Snackbar snackbar = Snackbar.make(view, message, BaseTransientBottomBar.LENGTH_SHORT);
        snackbar.setDuration(2000);
        snackbar.show();
    }
//    Snack bar short time display anchor view (1.5 sec)
    public void snackBarShortTime(String message, View view)
    {
        Snackbar snackbar = Snackbar.make(view, message, BaseTransientBottomBar.LENGTH_SHORT);
        snackbar.setAnchorView(view);
        snackbar.setDuration(2000);
        snackbar.show();
    }
//    Snack bar long time display (3 sec)
    public void snackBarLongTime(String message)
    {
        Snackbar snackbar = Snackbar.make(view, message, BaseTransientBottomBar.LENGTH_LONG);
        snackbar.setDuration(3500);
        snackbar.show();
    }
//    Snack bar long time display with anchor view (3 sec)
    public void snackBarLongTime(String message, View view)
    {
        Snackbar snackbar = Snackbar.make(view, message, BaseTransientBottomBar.LENGTH_LONG);
        snackbar.setDuration(3500);
        snackbar.show();
    }
//    endregion
}
