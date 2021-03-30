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
    public ArrayList<String> eduList;
    public ArrayList<String> provinceList;
    public ArrayList<String> cityList;
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
//        Initialize
        init();
    }

//    region Methods
    private void init()
    {
        eduList.addAll(Arrays.asList(context.getResources().getStringArray(R.array.education_list)));
        provinceList.addAll(Arrays.asList(context.getResources().getStringArray(R.array.province_list)));
    }
    public void setCityList(int cityCode)
    {
        if (cityCode == 0)
        {
            cityList.clear();
            cityList.add("ابتدا استان را انتخاب نمایید");
        }
        else if (cityCode == 1)
        {
            cityList.clear();
            cityList.addAll(Arrays.asList(context.getResources().getStringArray(R.array.city_list_tehran)));
        }
        else if(cityCode == 2)
        {
            cityList.clear();
            cityList.addAll(Arrays.asList(context.getResources().getStringArray(R.array.city_list_gilan)));
        }
    }
//    endregion
}
