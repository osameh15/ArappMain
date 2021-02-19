package ir.arapp.arappmain.View.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Services.HideShowKeyboard;

public class AuthActivity extends AppCompatActivity
{

//    region Variable
    private HideShowKeyboard hideShowKeyboard;
//    endregion

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
//        Inflate layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

//        Hooks
        hideShowKeyboard = new HideShowKeyboard(getApplicationContext(), this.findViewById(android.R.id.content));
    }

//    region methods
//    On back pressed override
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        hideShowKeyboard.hideKeyboardFrom(true);
    }
//    endregion
}