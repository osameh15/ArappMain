package ir.arapp.arappmain.viewmodel;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Services.NavigateFragment;
import ir.arapp.arappmain.Util.Services.SnackBarMessage;

public class RegisterValidateViewModel extends AndroidViewModel
{
    //region Variable
    public String validate = "";
    public String timer = "01:59";
    public String buttonText = getApplication().getResources().getString(R.string.submit_continue);
    public SnackBarMessage snackBarMessage = null;
    public NavigateFragment navigateFragment = null;
    //endregion

    public RegisterValidateViewModel(@NonNull Application application)
    {
        super(application);
    }

    //region Methods
    //check input
    public void checkPinView()
    {
        if (validate.length() > 0)
        {

        }
    }
    //validate code
    public void validateCodeNumber()
    {

    }
    //edit phone number
    public void editPhoneNumber()
    {

    }
    //endregion
}
