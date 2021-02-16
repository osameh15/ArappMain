package ir.arapp.arappmain.viewmodel;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Services.CheckPinView;
import ir.arapp.arappmain.Util.Services.NavigateFragment;
import ir.arapp.arappmain.Util.Services.SnackBarMessage;

public class RegisterValidateViewModel extends AndroidViewModel
{
    //region Variable
    public String validate = "";
    public String timer = "01:59";
    public int timerColor = getApplication().getResources().getColor(R.color.colorAccentDark);
    public String buttonText = getApplication().getResources().getString(R.string.submit_continue);
    public SnackBarMessage snackBarMessage = null;
    public NavigateFragment navigateFragment = null;
    public CheckPinView checkPinView = null;
    private CountDownTimer countDownTimer;
    //endregion

    public RegisterValidateViewModel(@NonNull Application application)
    {
        super(application);
    }

    //region Methods
    //validate code
    public void validateCodeNumber()
    {
        if (validate.equals("232323"))
        {
            navigateFragment.navigateToFragment("register");
        }
        else
        {
            if (validate.length() == 0)
            {
                checkPinView.checkPin();
                snackBarMessage.onFailure("ابتدا کد ارسال شده را وارد نمایید");
            }
            else
            {
                checkPinView.checkPin();
                snackBarMessage.onFailure("کد وارد شده صحیح نیست");
            }
        }
    }
    //edit phone number
    public void editPhoneNumber()
    {
        navigateFragment.navigateToFragment("phoneRegister");
    }
    //endregion
}
