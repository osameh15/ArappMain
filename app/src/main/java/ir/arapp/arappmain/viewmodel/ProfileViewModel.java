package ir.arapp.arappmain.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import ir.arapp.arappmain.Model.Profile;
import ir.arapp.arappmain.R;

public class ProfileViewModel extends AndroidViewModel
{

//    region Variable
    ArrayList<Profile> profileArrayList;
    MutableLiveData<ArrayList<Profile>> allProfileItems;
//    endregion

//    Constructor
    public ProfileViewModel(@NonNull Application application)
    {
        super(application);
//        Hooks
        profileArrayList = new ArrayList<>();
        allProfileItems = new MutableLiveData<>();
//        Initialize
        init();
    }

//    region Methods
//    Initialize function and variables
    private void init()
    {
        populateList();
        allProfileItems.setValue(profileArrayList);
    }
//    Set data to category array list
    private void populateList()
    {
        profileArrayList.add(new Profile(1, R.drawable.icon, "افزودن سرویس"));
        profileArrayList.add(new Profile(2, R.drawable.icon, "ویرایش سرویس"));
        profileArrayList.add(new Profile(3, R.drawable.icon, "سرویس های من"));
        profileArrayList.add(new Profile(4, R.drawable.icon, "رزرو سرویس"));
        profileArrayList.add(new Profile(5, R.drawable.icon, "امتیازات من"));
        profileArrayList.add(new Profile(6, R.drawable.icon, "نظرات من"));
        profileArrayList.add(new Profile(7, R.drawable.icon, "تخفیفات"));
        profileArrayList.add(new Profile(8, R.drawable.icon, "خروج از حساب کاربری"));
    }
//    Get all profile items
    public MutableLiveData<ArrayList<Profile>> getAllProfileItems()
    {
        return allProfileItems;
    }
//    endregion
}
