package ir.arapp.arappmain.view.activities


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.arapp.arappmain.R
import ir.arapp.arappmain.util.services.HideShowKeyboard


class AuthActivity : AppCompatActivity() {
    //    region Variable
    private var hideShowKeyboard: HideShowKeyboard? = null

    //    endregion
    override fun onCreate(savedInstanceState: Bundle?) {
//        Inflate layout
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

//        Hooks
        hideShowKeyboard = HideShowKeyboard(applicationContext, findViewById(android.R.id.content))
    }

    //    region methods
    //    On back pressed override
    override fun onBackPressed() {
        super.onBackPressed()
        hideShowKeyboard!!.hideKeyboardFrom(true)
    } //    endregion
}