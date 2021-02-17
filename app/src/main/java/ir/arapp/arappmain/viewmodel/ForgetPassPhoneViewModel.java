package ir.arapp.arappmain.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ir.arapp.arappmain.Util.Services.NavigateFragment;
import ir.arapp.arappmain.Util.Services.SnackBarMessage;

public class ForgetPassPhoneViewModel extends AndroidViewModel
{

//    region Variable
    public MutableLiveData<String> phone;
    public SnackBarMessage snackBarMessage;
    public NavigateFragment navigateFragment;
//    endregion

//    Constructor
    public ForgetPassPhoneViewModel(@NonNull Application application)
    {
        super(application);
//        Hooks
        phone = new MutableLiveData<>();
//        set data
        phone.setValue("");
    }

//    region Methods
//    On Cleared
    @Override
    protected void onCleared()
    {
        super.onCleared();
    }
//    on Button click listener
    public void onButtonClick()
    {
        if (phone.getValue().isEmpty())
        {
            snackBarMessage.onFailure("شماره موبایل را وارد نمایید");
            return;
        }
        navigateFragment.navigateToFragment("validate");
    }
//    endregion
}
