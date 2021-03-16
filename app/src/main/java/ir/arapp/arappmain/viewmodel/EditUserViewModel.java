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
public class EditUserViewModel extends AndroidViewModel
{
//    region Variables
    Context context;
    ArrayList<String> eduList;
    ArrayList<String> provinceList;
    ArrayList<String> cityList;
    MutableLiveData<ArrayList<String>> allEduList;
    MutableLiveData<ArrayList<String>> allProvinceList;
    MutableLiveData<ArrayList<String>> allCityList;
//    endregion

//    Constructor
    public EditUserViewModel(@NonNull Application application)
    {
        super(application);
//        Hooks
        context = application.getApplicationContext();
        eduList = new ArrayList<>();
        provinceList = new ArrayList<>();
        cityList = new ArrayList<>();
        allEduList = new MutableLiveData<>();
        allProvinceList = new MutableLiveData<>();
        allCityList = new MutableLiveData<>();
//        Initialize
        init();
    }

//    region Methods
    private void init()
    {
        eduList.addAll(Arrays.asList(context.getResources().getStringArray(R.array.education_list)));
        provinceList.addAll(Arrays.asList(context.getResources().getStringArray(R.array.province_list)));
    }
//    endregion
}
