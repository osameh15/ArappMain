package ir.arapp.arappmain.viewmodel;

import androidx.lifecycle.ViewModel;

import ir.arapp.arappmain.Util.Services.NavigateFragment;
import ir.arapp.arappmain.Util.Services.SnackBarMessage;

public class RegisterPhoneViewModel extends ViewModel
{
    //region Variable
    public String phone = "";
    public SnackBarMessage snackBarMessage = null;
    public NavigateFragment navigateFragment = null;
    //endregion
    //region methods
    //Submit phone Button
    public void onButtonClick()
    {
        if (phone.isEmpty())
        {
            snackBarMessage.onFailure("شماره موبایل را وارد نمایید");
            return;
        }
        if (!phone.startsWith("09") || phone.startsWith("094") || phone.startsWith("095") ||
                phone.startsWith("096") || phone.startsWith("097") || phone.startsWith("098") || phone.length() != 11)
        {
            snackBarMessage.onFailure("شماره موبایل با فرمت درست وارد نشده است");
            return;
        }
        navigateFragment.navigateToFragment("validate");
    }
    //Login Button
    public void loginButton()
    {
        navigateFragment.navigateToFragment("login");
    }
    //endregion
}
