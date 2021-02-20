package ir.arapp.arappmain.viewmodel;

import android.app.Application;
import android.os.CountDownTimer;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.Objects;
import java.util.regex.Pattern;

import ir.arapp.arappmain.Util.Services.CheckPinView;
import ir.arapp.arappmain.Util.Services.NavigateFragment;
import ir.arapp.arappmain.Util.Services.SnackBarMessage;

public class ForgetPassViewModel extends AndroidViewModel
{

//    region Variable
    private static final Pattern PASSWORD_PATTERN = Pattern.compile (
                    "^" +
                    "(?=.*[0-9])" +                         //at least 1 digit
                    "(?=.*[a-z])" +                         //at least 1 lower case letter
                    "(?=.*[A-Z])" +                        //at least 1 upper case letter
//                    "(?=.*[@#$%&+=)])" +            //at least 1 special character
                    "(?=\\S+$)" +                         //no white space
                    ".{6,}" +                                   //at least 6 character
                    "$");
    public MutableLiveData<String> phone;
    public MutableLiveData<String> validate;
    public MutableLiveData<Boolean> flag;
    public MutableLiveData<Long> currentTime;
    private CountDownTimer countDownTimer;
    public MutableLiveData<String> password;
    public MutableLiveData<String> cnfPassword;
    public SnackBarMessage snackBarMessage;
    public NavigateFragment navigateFragment;
    public CheckPinView checkPinView;
//    endregion

//    Constructor
    public ForgetPassViewModel(@NonNull Application application)
    {
        super(application);
//        Hooks
        phone = new MutableLiveData<>();
        validate = new MutableLiveData<>();
        currentTime = new MutableLiveData<>();
        flag = new MutableLiveData<>();
        password = new MutableLiveData<>();
        cnfPassword = new MutableLiveData<>();
//        set data
        phone.setValue("");
        validate.setValue("");
        currentTime.setValue(0L);
        flag.setValue(false);
        password.setValue("");
        cnfPassword.setValue("");
    }

//    region method
//    On Cleared
    @Override
    protected void onCleared()
    {
        super.onCleared();
        countDownTimer.cancel();
    }
//    on Button click listener
    public void onPhoneButtonClick()
    {
        if (Objects.requireNonNull(phone.getValue()).isEmpty())
        {
            snackBarMessage.onFailure("شماره موبایل را وارد نمایید");
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
//    validate code
    public void validateCodeNumber()
    {
        if (Objects.requireNonNull(validate.getValue()).equals("232323"))
        {
            navigateFragment.navigateToFragment("forgetPass");
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
        navigateFragment.navigateToFragment("phone");
    }
//    check password and confirm
    public void checkPassword()
    {
        if (Objects.requireNonNull(password.getValue()).isEmpty())
        {
            snackBarMessage.onFailure("رمز عبور را وارد نمایید");
            return;
        }
        else if (password.getValue().length() < 6)
        {
            snackBarMessage.onFailure("رمزعبور حداقل باید دارای 6 کاراکتر باشد");
            return;
        }
        else if (!PASSWORD_PATTERN.matcher(password.getValue()).matches())
        {
            snackBarMessage.onFailure("رمزعبور باید ترکیبی از عدد و حروف بزرگ و کوچک باشد");
            return;
        }
        else if (!password.getValue().equals(cnfPassword.getValue()))
        {
            snackBarMessage.onFailure("رمزعبور های وارد شده یکسان نیستند");
            return;
        }
        snackBarMessage.onSuccess("رمز عبور شما با موفقیت تغییر کرد");
        navigateFragment.navigateToFragment("login");
    }
//    endregion
}
