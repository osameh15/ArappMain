package ir.arapp.arappmain.util.services

import android.view.View
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class SnackBarToast     //    endregion
//    Constructor
    (  //    region Variable
    var view: View
) {
    //    region Methods
    //    Snack bar short time display (1.5 sec)
    fun snackBarShortTime(message: String?) {
        val snackbar = Snackbar.make(view, message!!, BaseTransientBottomBar.LENGTH_SHORT)
        snackbar.duration = 2000
        snackbar.show()
    }

    //    Snack bar short time display anchor view (1.5 sec)
    fun snackBarShortTime(message: String?, view: View?) {
        val snackbar = Snackbar.make(view!!, message!!, BaseTransientBottomBar.LENGTH_SHORT)
        snackbar.anchorView = view
        snackbar.duration = 2000
        snackbar.show()
    }

    //    Snack bar long time display (3 sec)
    fun snackBarLongTime(message: String?) {
        val snackbar = Snackbar.make(view, message!!, BaseTransientBottomBar.LENGTH_LONG)
        snackbar.duration = 3500
        snackbar.show()
    }

    //    Snack bar long time display with anchor view (3 sec)
    fun snackBarLongTime(message: String?, view: View?) {
        val snackbar = Snackbar.make(view!!, message!!, BaseTransientBottomBar.LENGTH_LONG)
        snackbar.anchorView = view
        snackbar.duration = 3500
        snackbar.show()
    } //    endregion
}