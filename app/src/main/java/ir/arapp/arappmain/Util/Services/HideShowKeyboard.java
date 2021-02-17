package ir.arapp.arappmain.Util.Services;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class HideShowKeyboard
{

//    region Variable
    private final Context context;
    private final View view;
//    endregion

//    Constructor
    public HideShowKeyboard(Context context, View view)
    {
        this.context = context;
        this.view = view;
    }
//    region methods
//    Method for close or show input method(keyboard) ...
    public void hideKeyboardFrom(boolean flag)
    {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (flag)
        {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        else
        {
            inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }
//    endregion
}
