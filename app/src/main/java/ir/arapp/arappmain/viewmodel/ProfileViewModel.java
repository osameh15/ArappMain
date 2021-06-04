package ir.arapp.arappmain.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import ir.arapp.arappmain.model.Profile;
import ir.arapp.arappmain.R;
import ir.arapp.arappmain.util.services.FragmentManager;
import ir.arapp.arappmain.util.services.SnackBarMessage;

public class ProfileViewModel extends AndroidViewModel
{

//    region Variable
    ArrayList<Profile> profileArrayList;
    MutableLiveData<ArrayList<Profile>> allProfileItems;
    public SnackBarMessage snackBarMessage;
    public FragmentManager fragmentManager;
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
        profileArrayList.add(new Profile(1, R.drawable.edit_service, "افزودن سرویس"));
        profileArrayList.add(new Profile(2, R.drawable.add_service, "افزودن تبلیغات"));
        profileArrayList.add(new Profile(3, R.drawable.my_service, "سرویس های من"));
        profileArrayList.add(new Profile(4, R.drawable.rezerve, "رزرو سرویس"));
        profileArrayList.add(new Profile(5, R.drawable.rates, "امتیازات من"));
        profileArrayList.add(new Profile(6, R.drawable.comments, "نظرات من"));
        profileArrayList.add(new Profile(7, R.drawable.reduction, "تخفیفات"));
        profileArrayList.add(new Profile(8, R.drawable.log_out, "خروج از حساب کاربری"));
    }
//    Get all profile items
    public MutableLiveData<ArrayList<Profile>> getAllProfileItems()
    {
        return allProfileItems;
    }
//    Edit Profile User
    public void editUserButton()
    {
        fragmentManager.navigateToFragment("editUser");
    }
//    Arapp Coin and navigate to fragment
    public void coinButton()
    {
        snackBarMessage.onSuccess("آراپ کوین");
    }
//    Change User profile avatar
    public void changePictureButton()
    {
        fragmentManager.setFunction("image");
    }
//    App setting
    public void setting()
    {
        fragmentManager.navigateToFragment("setting");
    }
//    endregion
}
