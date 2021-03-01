package ir.arapp.arappmain.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.CountDownTimer;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.Objects;
import java.util.regex.Pattern;

import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Services.FragmentManager;
import ir.arapp.arappmain.Util.Services.SnackBarMessage;

public class ForgetPassViewModel extends AndroidViewModel
{

//    region Variable
//    Password pattern
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
    private final MutableLiveData<Boolean> checkTime;     // check current time is start();
    public MutableLiveData<Long> currentTime;
    public MutableLiveData<String> time;
    public MutableLiveData<Integer> timeColor;
    private CountDownTimer countDownTimer;
    public MutableLiveData<String> password;
    public MutableLiveData<String> cnfPassword;
    public SnackBarMessage snackBarMessage;
    public FragmentManager fragmentManager;
//    endregion

//    Constructor
    public ForgetPassViewModel(@NonNull Application application)
    {
        super(application);
//        Hooks
        phone = new MutableLiveData<>();
        validate = new MutableLiveData<>();
        checkTime = new MutableLiveData<>();
        currentTime = new MutableLiveData<>();
        flag = new MutableLiveData<>();
        password = new MutableLiveData<>();
        cnfPassword = new MutableLiveData<>();
        time = new MutableLiveData<>();
        timeColor = new MutableLiveData<>();
//        set data
        phone.setValue("");
        validate.setValue("");
        checkTime.setValue(false);
        currentTime.setValue(0L);
        flag.setValue(false);
        password.setValue("");
        cnfPassword.setValue("");
        time.setValue("");
        timeColor.setValue(getApplication().getResources().getColor(R.color.colorAccentDark));
    }

//    region method
//    On Cleared
    @Override
    protected void onCleared()
    {
        super.onCleared();
        if (Objects.requireNonNull(checkTime.getValue()))
        {
            countDownTimer.cancel();
        }
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
        if (Objects.equals(currentTime.getValue(), 0L))
        {
            if (Objects.equals(flag.getValue(), true))
            {
                resendCode();
            }
            else
            {
                startCountDownTimer();
            }
        }
//        Todo on response. connect to server and send sms code
        fragmentManager.navigateToFragment("validate");
    }
//    validate code
    public void validateCodeNumber()
    {
//        Todo connect to server and check validate code
        if (Objects.requireNonNull(validate.getValue()).equals("232323"))
        {
            fragmentManager.navigateToFragment("forgetPass");
        }
        else
        {
            if (validate.getValue().length() == 0)
            {
                fragmentManager.setFunction("error");
                snackBarMessage.onFailure("ابتدا کد ارسال شده را وارد نمایید");
            }
            else
            {
                fragmentManager.setFunction("error");
                snackBarMessage.onFailure("کد وارد شده صحیح نیست");
            }
        }
    }
//    resend Code
    public void resendCode()
    {
//        Todo send validate code again
        flag.setValue(false);
        validate.setValue("");
        fragmentManager.setFunction("resend");
        startCountDownTimer();
    }
//    Countdown timer
    public void startCountDownTimer()
    {
        checkTime.setValue(true);
        countDownTimer = new CountDownTimer(120000, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                currentTime.setValue(millisUntilFinished/1000);
                setTimer(currentTime);
            }
            @Override
            public void onFinish()
            {
                currentTime.setValue(0L);
                flag.setValue(true);
                checkTime.setValue(false);
            }
        };
        countDownTimer.start();
    }
//    Timer color and text
    private void setTimer(MutableLiveData<Long> s)
    {
        long min = Objects.requireNonNull(s.getValue()) /60;
        long sec = s.getValue()%60;
        @SuppressLint("DefaultLocale") String timeToFormat = String.format("%02d:%02d", min, sec);
        time.setValue(timeToFormat);
        if (s.getValue() < 10)
        {
            timeColor.setValue(getApplication().getResources().getColor(R.color.notificationColorRed));
        }
    }
//    edit phone number
    public void editPhoneNumber()
    {
        fragmentManager.navigateToFragment("phone");
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
            snackBarMessage.onSuccess("رمزعبور باید ترکیبی از عدد و حروف بزرگ و کوچک باشد");
            return;
        }
        else if (!password.getValue().equals(cnfPassword.getValue()))
        {
            snackBarMessage.onFailure("رمزعبور های وارد شده یکسان نیستند");
            return;
        }
//        Todo connect to sever and submit the last changes
        snackBarMessage.onSuccess("رمز عبور شما با موفقیت تغییر کرد");
        fragmentManager.navigateToFragment("login");
    }
//    endregion
}
