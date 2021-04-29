package ir.arapp.arappmain.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Arrays;

import ir.arapp.arappmain.R;

@SuppressLint("StaticFieldLeak")
public class EditServiceViewModel extends AndroidViewModel
{
//    region Variables
    Context context;
//    endregion

//    Constructor
    public EditServiceViewModel(@NonNull Application application)
    {
        super(application);
//        Hooks
        context = application.getApplicationContext();
//        Initialize
        init();
    }

//    region Methods
//    Initialize
    private void init()
    {
    }
//    endregion
}
