package ir.arapp.arappmain.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import ir.arapp.arappmain.Model.User;
import ir.arapp.arappmain.Util.Services.NavigateFragment;
import ir.arapp.arappmain.Util.Services.SnackBarMessage;

public class LoginViewModel extends AndroidViewModel
{
    //region Variable
    private User user;
    public String phone = "";
    public String password = "";
    public SnackBarMessage snackBarMessage = null;
    public NavigateFragment navigateFragment = null;

    //endregion

    public LoginViewModel(@NonNull Application application)
    {
        super(application);
    }

    //region Methods
    //Sign Up button
    public void signUpButton()
    {
        navigateFragment.navigateToFragment("signUp");
    }
    //Sign Up button
    public void forgetPassButton()
    {
        navigateFragment.navigateToFragment("forgetPass");
    }
    //Login Button Click listener
    public void onButtonClick()
    {
        if (phone.isEmpty() && password.isEmpty())
        {
            snackBarMessage.onFailure("ابتدا شماره موبایل و رمزعبور را وارد نمایید");
            return;
        }
        if (phone.isEmpty())
        {
            snackBarMessage.onFailure("شماره موبایل را وارد نمایید");
            return;
        }
        if (password.isEmpty())
        {
            snackBarMessage.onFailure("رمزعبور را وارد نمایید");
            return;
        }
        navigateFragment.navigateToFragment("home");
    }
    //endregion
}
