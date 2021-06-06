package ir.arapp.arappmain.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel

@SuppressLint("StaticFieldLeak")
class EditServiceViewModel(application: Application) : AndroidViewModel(application) {
    //    region Variables
    var context: Context

    //    region Methods
    //    Initialize
    private fun init() {} //    endregion

    //    endregion
    //    Constructor
    init {
        //        Hooks
        context = application.applicationContext
        //        Initialize
        init()
    }
}