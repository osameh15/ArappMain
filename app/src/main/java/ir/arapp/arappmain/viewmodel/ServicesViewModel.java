package ir.arapp.arappmain.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import ir.arapp.arappmain.model.Service;
import ir.arapp.arappmain.R;

public class ServicesViewModel extends AndroidViewModel
{
//    region Variable
    ArrayList<Service> myServices;
    MutableLiveData<ArrayList<Service>> allMyServices;
//    endregion

//    Constructor
    public ServicesViewModel(@NonNull Application application)
    {
        super(application);
//        Hooks
        myServices = new ArrayList<>();
        allMyServices = new MutableLiveData<>();
//        Initialize
        init();
    }

//    region Methods
//    Initialize function and variables
    private void init()
    {
        populateList();
        allMyServices.setValue(myServices);
    }
//    Set data to category array list
    private void populateList()
    {
//        TODO connect to server
        myServices.add(new Service(1, 1, R.drawable.news_one, "رستوران خاوران", "چشم دل باز کن که جان بینی"
                , "هرچه خدا بخواهد", 0, 0,
                "تهران، صادقیه", "دقایقی قبل", "", ""));
        myServices.add(new Service(2, 1, R.drawable.news_two, "رستوران ایران زمین", "چشم دل باز کن که جان بینی"
                , "هرچه خدا بخواهد", 3.86, 10,
                "تهران، صادقیه", "4 روز پیش", "", ""));
        myServices.add(new Service(3, 1, R.drawable.news_three, "رستوران عمو ژوپیتو", "چشم دل باز کن که جان بینی"
                , "هرچه خدا بخواهد", 4.9, 22,
                "تهران، صادقیه", "هفته پیش", "", ""));
        myServices.add(new Service(4, 1, R.drawable.news_four, "فست فود فرزانه", "چشم دل باز کن که جان بینی"
                , "هرچه خدا بخواهد", 2.62, 23,
                "تهران، صادقیه", "3 هفته پیش", "", ""));
        myServices.add(new Service(5, 1, R.drawable.hotels, "هتل دایی بهزاد بجز بهروز", "چشم دل باز کن که جان بینی"
                , "هرچه خدا بخواهد", 1.56, 89,
                "تهران، صادقیه", "10 فروردین 1400", "", ""));
        myServices.add(new Service(1, 1, R.drawable.news_one, "رستوران خاوران", "چشم دل باز کن که جان بینی"
                , "هرچه خدا بخواهد", 0, 0,
                "تهران، صادقیه", "دقایقی قبل", "", ""));
        myServices.add(new Service(2, 1, R.drawable.news_two, "رستوران ایران زمین", "چشم دل باز کن که جان بینی"
                , "هرچه خدا بخواهد", 3.86, 10,
                "تهران، صادقیه", "4 روز پیش", "", ""));
        myServices.add(new Service(3, 1, R.drawable.news_three, "رستوران عمو ژوپیتو", "چشم دل باز کن که جان بینی"
                , "هرچه خدا بخواهد", 4.9, 22,
                "تهران، صادقیه", "هفته پیش", "", ""));
        myServices.add(new Service(4, 1, R.drawable.news_four, "فست فود فرزانه", "چشم دل باز کن که جان بینی"
                , "هرچه خدا بخواهد", 2.62, 23,
                "تهران، صادقیه", "3 هفته پیش", "", ""));
        myServices.add(new Service(5, 1, R.drawable.hotels, "هتل دایی بهزاد بجز بهروز", "چشم دل باز کن که جان بینی"
                , "هرچه خدا بخواهد", 1.56, 89,
                "تهران، صادقیه", "10 فروردین 1400", "", ""));
    }
    //    Get all Category items
    public MutableLiveData<ArrayList<Service>> getAllCategoryItems()
    {
        return allMyServices;
    }
//    endregion
}
