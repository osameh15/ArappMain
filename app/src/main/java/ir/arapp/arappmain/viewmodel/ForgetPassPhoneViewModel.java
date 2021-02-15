package ir.arapp.arappmain.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import ir.arapp.arappmain.Util.Services.NavigateFragment;
import ir.arapp.arappmain.Util.Services.SnackBarMessage;

public class ForgetPassPhoneViewModel extends AndroidViewModel
{
    //region Variable
    public String phone = "";
    public SnackBarMessage snackBarMessage;
    public NavigateFragment navigateFragment;
    //endregion

    public ForgetPassPhoneViewModel(@NonNull Application application)
    {
        super(application);
    }

    //region Methods
    public void onButtonClick()
    {
        if (phone.isEmpty())
        {
            snackBarMessage.onFailure("شماره موبایل را وارد نمایید");
            return;
        }
        navigateFragment.navigateToFragment("validate");
    }
    //endregion
}
