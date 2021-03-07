package ir.arapp.arappmain.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import ir.arapp.arappmain.Model.Notification;
import ir.arapp.arappmain.R;

public class NotificationViewModel extends AndroidViewModel
{
//    region Variables
    ArrayList<Notification> notificationArrayList;
    MutableLiveData<ArrayList<Notification>> allNotificationList;
//    endregion

//    Constructor
    public NotificationViewModel(@NonNull Application application)
    {
        super(application);
//        Hooks
        notificationArrayList = new ArrayList<>();
        allNotificationList = new MutableLiveData<>();
//        Initialize
        init();
    }

//    region Methods
    private void init()
    {
        populateList();
        allNotificationList.setValue(notificationArrayList);
    }
//    Set data in notification list
    private void populateList()
    {
        notificationArrayList.add(new Notification(1, "آراپ", "امتیاز بده، بهترین را انتخاب کن",
                "اپلیکیشن آراپ از اول مهرماه سال 1400 مهمان گرم خانه های شما خواهد بود. منتظر حمایت گرم شما خواهیم بود",
                R.drawable.icon, R.drawable.arapp_default, "10 اسفند 1399"));
        notificationArrayList.add(new Notification(1, "بروزرسانی!", "آپدیت شگفتانه رسید",
                "نسخه جدید آراپ با امکانات شگفت انگیز از راه رسید. از هم اکنون می توانید اقدام به بروزرسانی اپ کنید",
                R.drawable.icon, R.drawable.arapp_default, "8 آبان 1400"));
        notificationArrayList.add(new Notification(1, "آراپ کوین", "سورپرایز شو",
                "با ثبت نظرات و امتیازات خود شانس خود را برای برنده شدن در قرعه کشی 10 دستگاه خودروی ملی امتحان کنید. با آراپ کوین همچنین می توانید از امکانات سایر اپلیکیشن های ایرانی و تخفیفات ویژه همراه شوید",
                R.drawable.icon, R.drawable.arapp_default, "23 آذر 1400"));
    }
//    Get all notification items
    public MutableLiveData<ArrayList<Notification>> getAllNotificationList()
    {
        return allNotificationList;
    }
//    endregion
}
