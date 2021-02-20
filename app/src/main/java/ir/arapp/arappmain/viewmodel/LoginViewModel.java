package ir.arapp.arappmain.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import java.util.Objects;
import ir.arapp.arappmain.Util.Services.NavigateFragment;
import ir.arapp.arappmain.Util.Services.SnackBarMessage;

public class LoginViewModel extends AndroidViewModel
{

//    region Variable
    public MutableLiveData<String> phone;
    public MutableLiveData<String> password;
    public SnackBarMessage snackBarMessage;
    public NavigateFragment navigateFragment;
//    endregion

//    Constructor
    public LoginViewModel(@NonNull Application application)
    {
        super(application);
//        Hooks
        phone = new MutableLiveData<>();
        password = new MutableLiveData<>();
//        set Data
        phone.setValue("");
        password.setValue("");
    }

//    region Methods
//    On Cleared
    @Override
    protected void onCleared()
    {
        super.onCleared();
    }
//    Sign Up button
    public void signUpButton()
    {
        navigateFragment.navigateToFragment("signUp");
    }
//    Forget Pass button
    public void forgetPassButton()
    {
        navigateFragment.navigateToFragment("forgetPass");
    }
//    Login Button Click listener
    public void onButtonClick()
    {
        if (Objects.requireNonNull(phone.getValue()).isEmpty() && Objects.requireNonNull(password.getValue()).isEmpty())
        {
            snackBarMessage.onFailure("ابتدا شماره موبایل و رمزعبور را وارد نمایید");
            return;
        }
        else if (phone.getValue().isEmpty())
        {
            snackBarMessage.onFailure("شماره موبایل را وارد نمایید");
            return;
        }
        else if (Objects.requireNonNull(password.getValue()).isEmpty())
        {
            snackBarMessage.onFailure("رمزعبور را وارد نمایید");
            return;
        }
        navigateFragment.navigateToFragment("home");
    }
//    endregion
}
