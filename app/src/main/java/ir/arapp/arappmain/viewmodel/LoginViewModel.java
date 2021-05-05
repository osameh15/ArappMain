package ir.arapp.arappmain.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import java.util.Objects;

import ir.arapp.arappmain.Util.Services.FragmentManager;
import ir.arapp.arappmain.Util.Services.SnackBarMessage;

public class LoginViewModel extends AndroidViewModel
{

//    region Variable
    public MutableLiveData<String> phone;
    public MutableLiveData<String> password;
    public SnackBarMessage snackBarMessage;
    public FragmentManager fragmentManager;
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
    public void signUpButton(View view)
    {
        fragmentManager.navigateToFragment("signUp");
    }
//    Forget Pass button
    public void forgetPassButton(View view)
    {
        fragmentManager.navigateToFragment("forgetPass");
    }
//    Login Button Click listener
    public void onButtonClick(View view)
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
//        Todo connect to server
        fragmentManager.navigateToFragment("home");
    }
//    endregion
}
