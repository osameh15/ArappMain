package ir.arapp.arappmain.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.CountDownTimer;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import java.util.Objects;
import java.util.regex.Pattern;

import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Services.FragmentManager;
import ir.arapp.arappmain.Util.Services.SnackBarMessage;

public class RegisterViewModel extends AndroidViewModel
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
    private final MutableLiveData<Boolean> checkTime;          // check current time is start();
    public MutableLiveData<Long> currentTime;
    public MutableLiveData<String> time;
    public MutableLiveData<Integer> timeColor;
    public MutableLiveData<String> name;
    public MutableLiveData<String> password;
    public MutableLiveData<String> cnfPassword;
    public MutableLiveData<Boolean> checkBox;
    public MutableLiveData<String> service;
    private CountDownTimer countDownTimer;
    public SnackBarMessage snackBarMessage;
    public FragmentManager fragmentManager;
//    endregion

//    Constructor
    public RegisterViewModel(@NonNull Application application)
    {
        super(application);
//        Hooks
        phone = new MutableLiveData<>();
        validate = new MutableLiveData<>();
        flag = new MutableLiveData<>();
        checkTime = new MutableLiveData<>();
        currentTime = new MutableLiveData<>();
        time = new MutableLiveData<>();
        timeColor = new MutableLiveData<>();
        name = new MutableLiveData<>();
        password = new MutableLiveData<>();
        cnfPassword = new MutableLiveData<>();
        checkBox = new MutableLiveData<>();
        service = new MutableLiveData<>();
//        Initialize
        init();
    }

//    region method
//    Initialize methods
    private void init()
    {
        phone.setValue("");
        flag.setValue(false);
        checkTime.setValue(false);
        currentTime.setValue(0L);
        validate.setValue("");
        time.setValue("");
        timeColor.setValue(getApplication().getResources().getColor(R.color.colorAccentDark));
        name.setValue("");
        password.setValue("");
        cnfPassword.setValue("");
        checkBox.setValue(false);
        service.setValue("");
    }
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
//    Submit phone Button
    public void onPhoneButtonClick(View view)
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
//        Todo connect to server and send sms validation code
//        Start Countdown timer
        if (Objects.equals(currentTime.getValue(), 0L))
        {
            if (Objects.equals(flag.getValue(), true))
            {
                resendCode(view);
            }
            else
            {
                startCountDownTimer();
            }
        }
        fragmentManager.navigateToFragment("validate");
    }
//    Login Button
    public void loginButton()
    {
        fragmentManager.navigateToFragment("login");
    }
//    validate code
    public void validateCodeNumber(View view)
    {
//        Todo check validate code and connect to server
        if (Objects.requireNonNull(validate.getValue()).equals("232323"))
        {
            fragmentManager.navigateToFragment("register");
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
    public void resendCode(View view)
    {
//        Todo connect to server and send validation code again
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
                setTimer(currentTime);
                fragmentManager.setFunction("editPhone");
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
        else
        {
            timeColor.setValue(getApplication().getResources().getColor(R.color.colorAccentDark));
        }
    }
//    edit phone number
    public void editPhoneNumber(View view)
    {
        fragmentManager.navigateToFragment("phoneRegister");
    }
//    Check accept application law
    public void setCheckBox(View view)
    {
        if (Objects.equals(checkBox.getValue(), false))
        {
            checkBox.setValue(true);
        }
        else if (Objects.equals(checkBox.getValue(), true))
        {
            checkBox.setValue(false);
        }
    }
    public void readPrivacy(View view)
    {
        fragmentManager.setFunction("privacy");
    }
//    Ob Register Button Click
    public void onRegisterButtonClick(View view)
    {
        if (Objects.requireNonNull(name.getValue()).isEmpty() &&
                Objects.requireNonNull(password.getValue()).isEmpty() &&
                Objects.requireNonNull(cnfPassword.getValue()).isEmpty() &&
                Objects.requireNonNull(service.getValue()).isEmpty())
        {
            snackBarMessage.onFailure("ابتدا مشخصات خود را تکمیل نمایید");
        }
        else if (name.getValue().isEmpty())
        {
            snackBarMessage.onFailure("نام خود را وارد نمایید");
        }
        else if (Objects.requireNonNull(password.getValue()).isEmpty())
        {
            snackBarMessage.onFailure("رمز عبور را وارد نمایید");
        }
        else if (Objects.requireNonNull(service.getValue()).isEmpty())
        {
            snackBarMessage.onFailure("نوع سرویس خود را مشخص نمایید");
        }
        else if (checkPassword(view))
        {
//        Todo connect to server and store user details
            fragmentManager.navigateToFragment("home");
        }
    }
//    Check type of service
    public void setService(View view)
    {
        service.setValue("سرویس دهنده");
    }
    public void getService(View view)
    {
        service.setValue("سرویس گیرنده");
    }
//    check password and confirm
    private boolean checkPassword(View view)
    {
        if (Objects.requireNonNull(password.getValue()).length() < 6)
        {
            snackBarMessage.onFailure("رمزعبور حداقل باید دارای 6 کاراکتر باشد");
            return false;
        }
        else if (!PASSWORD_PATTERN.matcher(password.getValue()).matches())
        {
            snackBarMessage.onSuccess("رمزعبور باید ترکیبی از عدد و حروف بزرگ و کوچک باشد");
            return false;
        }
        else if (!password.getValue().equals(cnfPassword.getValue()))
        {
            snackBarMessage.onFailure("رمزعبور های وارد شده یکسان نیستند");
            return false;
        }
        return true;
    }
//    endregion
}
