package ir.arapp.arappmain.viewmodel;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Services.CheckPinView;
import ir.arapp.arappmain.Util.Services.NavigateFragment;
import ir.arapp.arappmain.Util.Services.SnackBarMessage;

public class RegisterValidateViewModel extends AndroidViewModel
{
    //region Variable
    public String validate = "";
    public MutableLiveData<Boolean> flag;
    public MutableLiveData<Long> currentTime;
    private CountDownTimer countDownTimer;
    public SnackBarMessage snackBarMessage;
    public NavigateFragment navigateFragment;
    public CheckPinView checkPinView;
    //endregion

    //Constructor
    public RegisterValidateViewModel(@NonNull Application application)
    {
        super(application);
        //Hooks
        currentTime = new MutableLiveData<>();
        flag = new MutableLiveData<>();
        flag.setValue(false);
        snackBarMessage = null;
        navigateFragment = null;
        checkPinView = null;
        startCountDownTimer();
    }

    //region Methods
    //On Cleared
    @Override
    public void onCleared()
    {
        super.onCleared();
        countDownTimer.cancel();
    }
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
                checkPinView.checkPin("error");
                snackBarMessage.onFailure("ابتدا کد ارسال شده را وارد نمایید");
            }
            else
            {
                checkPinView.checkPin("error");
                snackBarMessage.onFailure("کد وارد شده صحیح نیست");
            }
        }
    }
    //resend Code
    public void resendCode()
    {
        flag.setValue(false);
        validate = "";
        checkPinView.checkPin("resend");
        startCountDownTimer();
    }
    //Countdown timer
    public void startCountDownTimer()
    {
        countDownTimer = new CountDownTimer(80000, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                currentTime.setValue(millisUntilFinished/1000);
            }
            @Override
            public void onFinish()
            {
                currentTime.setValue(0L);
                flag.setValue(true);
            }
        };
        countDownTimer.start();
    }
    //edit phone number
    public void editPhoneNumber()
    {
        navigateFragment.navigateToFragment("phoneRegister");
    }
    //endregion
}
