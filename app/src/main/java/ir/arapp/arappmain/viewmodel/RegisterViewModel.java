package ir.arapp.arappmain.viewmodel;

import android.app.Application;
import android.os.CountDownTimer;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import java.util.Objects;
import ir.arapp.arappmain.Util.Services.CheckPinView;
import ir.arapp.arappmain.Util.Services.NavigateFragment;
import ir.arapp.arappmain.Util.Services.SnackBarMessage;

public class RegisterViewModel extends AndroidViewModel
{

//    region Variable
    public MutableLiveData<String> phone;
    public MutableLiveData<String> validate;
    public MutableLiveData<Boolean> flag;
    public MutableLiveData<Long> currentTime;
    private CountDownTimer countDownTimer;
    public SnackBarMessage snackBarMessage;
    public NavigateFragment navigateFragment;
    public CheckPinView checkPinView;
//    endregion

//    Constructor
    public RegisterViewModel(@NonNull Application application)
    {
        super(application);
//        Hooks
        phone = new MutableLiveData<>();
        validate = new MutableLiveData<>();
        currentTime = new MutableLiveData<>();
        flag = new MutableLiveData<>();
//        set data
        phone.setValue("");
        currentTime.setValue(0L);
        validate.setValue("");
        flag.setValue(false);
    }

//    region method
//    On Cleared
    @Override
    protected void onCleared()
    {
        super.onCleared();
        countDownTimer.cancel();
    }
//    Submit phone Button
    public void onPhoneButtonClick()
    {
        if (Objects.requireNonNull(phone.getValue()).isEmpty())
        {
            snackBarMessage.onFailure("شماره موبایل را وارد نمایید");
            return;
        }
        else if (!phone.getValue().startsWith("09") || phone.getValue().startsWith("094") || phone.getValue().startsWith("095") ||
                phone.getValue().startsWith("096") || phone.getValue().startsWith("097") || phone.getValue().startsWith("098") ||
                phone.getValue().length() != 11)
        {
            snackBarMessage.onFailure("شماره موبایل با فرمت درست وارد نشده است");
            return;
        }
//        Start Countdown timer
        if (currentTime.getValue().equals(0L))
        {
            if (flag.getValue().equals(true))
            {
                resendCode();
            }
            else
            {
                startCountDownTimer();
            }
        }
        navigateFragment.navigateToFragment("validate");
    }
//    Login Button
    public void loginButton()
    {
        navigateFragment.navigateToFragment("login");
    }
//    validate code
    public void validateCodeNumber()
    {
        if (Objects.requireNonNull(validate.getValue()).equals("232323"))
        {
            navigateFragment.navigateToFragment("register");
        }
        else
        {
            if (validate.getValue().length() == 0)
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
//    resend Code
    public void resendCode()
    {
        flag.setValue(false);
        validate.setValue("");
        checkPinView.checkPin("resend");
        startCountDownTimer();
    }
//    Countdown timer
    public void startCountDownTimer()
    {
        countDownTimer = new CountDownTimer(120000, 1000)
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
//    edit phone number
    public void editPhoneNumber()
    {
        navigateFragment.navigateToFragment("phoneRegister");
    }
//    endregion
}
