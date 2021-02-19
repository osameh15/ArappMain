package ir.arapp.arappmain.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class ForgetPassViewModel extends AndroidViewModel
{

//    region Variable
    public MutableLiveData<String> password;
    public MutableLiveData<String> cnfPassword;
//    endregion

//    Constructor
    public ForgetPassViewModel(@NonNull Application application)
    {
        super(application);
//        Hooks
        password = new MutableLiveData<>();
        cnfPassword = new MutableLiveData<>();
//        set data
        password.setValue("");
        cnfPassword.setValue("");
    }

//    region method
//    endregion
}
