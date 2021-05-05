package ir.arapp.arappmain.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import ir.arapp.arappmain.R;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;

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
//    Initialize
    private void init()
    {
        eduList.addAll(Arrays.asList(context.getResources().getStringArray(R.array.education_list)));
        provinceList.addAll(Arrays.asList(context.getResources().getStringArray(R.array.province_list)));
        allEduList.setValue(eduList);
        allProvinceList.setValue(provinceList);
    }
//    Set City Lists
    public void setCityList(int cityCode)
    {
        if (cityCode == 0)
        {
            cityList.clear();
            cityList.add("ابتدا استان را انتخاب نمایید");
            allCityList.setValue(cityList);
        }
        else if (cityCode == 1)
        {
            cityList.clear();
            cityList.addAll(Arrays.asList(context.getResources().getStringArray(R.array.city_list_tehran)));
            allCityList.setValue(cityList);
        }
        else if(cityCode == 2)
        {
            cityList.clear();
            cityList.addAll(Arrays.asList(context.getResources().getStringArray(R.array.city_list_gilan)));
            allCityList.setValue(cityList);
        }
    }
//    Return Methods
    public MutableLiveData<ArrayList<String>> getAllEduList()
    {
        return allEduList;
    }
    public MutableLiveData<ArrayList<String>> getAllProvinceList()
    {
        return allProvinceList;
    }
    public MutableLiveData<ArrayList<String>> getAllCityList()
    {
        return allCityList;
    }
//    endregion
}
