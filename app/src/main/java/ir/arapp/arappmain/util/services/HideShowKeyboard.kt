package ir.arapp.arappmain.util.services

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

class HideShowKeyboard     //    endregion
//    Constructor
    (//    region Variable
    private val context: Context, private val view: View
) {
    //    region methods
    //    Method for close or show input method(keyboard) ...
    fun hideKeyboardFrom(flag: Boolean) {
        val inputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        if (flag) {
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        } else {
            inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    } //    endregion
}