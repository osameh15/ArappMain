package ir.arapp.arappmain.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ir.arapp.arappmain.R
import ir.arapp.arappmain.model.Notification
import java.util.*

class NotificationViewModel(application: Application) : AndroidViewModel(application) {
    //    region Variables
    var notificationArrayList: ArrayList<Notification>

    //    Get all notification items
    //    endregion
    var allNotificationList: MutableLiveData<ArrayList<Notification>>

    //    region Methods
    //    Initialize function and variables
    private fun init() {
        populateList()
        allNotificationList.value = notificationArrayList
    }

    //    Set data to notification list
    private fun populateList() {
//        TODO connect to server
        notificationArrayList.add(
            Notification(
                1, "آراپ", "امتیاز بده، بهترین را انتخاب کن",
                "اپلیکیشن آراپ از اول مهرماه سال 1400 مهمان گرم خانه های شما خواهد بود. منتظر حمایت گرم شما خواهیم بود",
                R.drawable.icon, R.drawable.arapp_default, "10 اسفند 1399"
            )
        )
        notificationArrayList.add(
            Notification(
                1, "بروزرسانی!", "آپدیت شگفتانه رسید",
                "نسخه جدید آراپ با امکانات شگفت انگیز از راه رسید. از هم اکنون می توانید اقدام به بروزرسانی اپ کنید",
                R.drawable.icon, R.drawable.arapp_default, "8 آبان 1400"
            )
        )
        notificationArrayList.add(
            Notification(
                1, "آراپ کوین", "سورپرایز شو",
                "با ثبت نظرات و امتیازات خود شانس خود را برای برنده شدن در قرعه کشی 10 دستگاه خودروی ملی امتحان کنید. با آراپ کوین همچنین می توانید از امکانات سایر اپلیکیشن های ایرانی و تخفیفات ویژه همراه شوید",
                R.drawable.icon, R.drawable.arapp_default, "23 آذر 1400"
            )
        )
    }

    //    endregion
    //    Constructor
    init {
        //        Hooks
        notificationArrayList = ArrayList()
        allNotificationList = MutableLiveData()
        //        Initialize
        init()
    }
}