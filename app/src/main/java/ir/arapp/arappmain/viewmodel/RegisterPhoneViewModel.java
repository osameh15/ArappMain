package ir.arapp.arappmain.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ir.arapp.arappmain.Util.Services.NavigateFragment;
import ir.arapp.arappmain.Util.Services.SnackBarMessage;

public class RegisterPhoneViewModel extends AndroidViewModel
{

//    region Variable
    public MutableLiveData<String> phone;
    public SnackBarMessage snackBarMessage = null;
    public NavigateFragment navigateFragment = null;
//    endregion

//    constructor
    public RegisterPhoneViewModel(@NonNull Application application)
    {
        super(application);
//        Hooks
        phone = new MutableLiveData<>();
//        set data
        phone.setValue("");
    }

//    region methods
//    On Cleared
    @Override
    protected void onCleared()
    {
        super.onCleared();
    }
//    Submit phone Button
    public void onButtonClick()
    {
        if (phone.getValue().isEmpty())
        {
            snackBarMessage.onFailure("شماره موبایل را وارد نمایید");
            return;
        }
        if (!phone.getValue().startsWith("09") || phone.getValue().startsWith("094") || phone.getValue().startsWith("095") ||
                phone.getValue().startsWith("096") || phone.getValue().startsWith("097") || phone.getValue().startsWith("098") ||
                phone.getValue().length() != 11)
        {
            snackBarMessage.onFailure("شماره موبایل با فرمت درست وارد نشده است");
            return;
        }
        navigateFragment.navigateToFragment("validate");
    }
//    Login Button
    public void loginButton()
    {
        navigateFragment.navigateToFragment("login");
    }
//    endregion
}
