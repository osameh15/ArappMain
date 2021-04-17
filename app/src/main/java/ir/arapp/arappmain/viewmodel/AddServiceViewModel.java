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
public class AddServiceViewModel extends AndroidViewModel
{
//    region Variables
    Context context;
    public ArrayList<String> category;
    ArrayList<String> openingYear;
    MutableLiveData<ArrayList<String>> allCategory;
    MutableLiveData<ArrayList<String>> allOpeningYear;
//    endregion

//    Constructor
    public AddServiceViewModel(@NonNull Application application)
    {
        super(application);
//        Hooks
        context = application.getApplicationContext();
        category = new ArrayList<>();
        openingYear = new ArrayList<>();
        allCategory = new MutableLiveData<>();
        allOpeningYear = new MutableLiveData<>();
//        Initialize
        init();
    }

//    region Methods
//    Initialize
    private void init()
    {
        category.addAll(Arrays.asList(context.getResources().getStringArray(R.array.category_list)));
        openingYear.addAll(Arrays.asList(context.getResources().getStringArray(R.array.year_list)));
        allCategory.setValue(category);
        allOpeningYear.setValue(openingYear);
    }
//    Return Methods
    public MutableLiveData<ArrayList<String>> getAllCategory()
    {
        return allCategory;
    }
    public MutableLiveData<ArrayList<String>> getAllOpeningYear()
    {
        return allOpeningYear;
    }
//    endregion
}
