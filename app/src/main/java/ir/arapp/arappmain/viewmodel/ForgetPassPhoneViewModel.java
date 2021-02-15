package ir.arapp.arappmain.viewmodel;

import androidx.lifecycle.ViewModel;

import ir.arapp.arappmain.Util.Services.NavigateFragment;
import ir.arapp.arappmain.Util.Services.SnackBarMessage;

public class ForgetPassPhoneViewModel extends ViewModel
{
    //region Variable
    public String phone = "";
    public SnackBarMessage snackBarMessage;
    public NavigateFragment navigateFragment;
    //endregion
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
