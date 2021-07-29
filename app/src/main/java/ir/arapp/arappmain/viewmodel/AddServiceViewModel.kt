package ir.arapp.arappmain.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.util.Base64
import android.util.Log
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar
import ir.arapp.arappmain.R
import ir.arapp.arappmain.model.base.PostServiceData
import ir.arapp.arappmain.util.server.RetrofitClient
import ir.arapp.arappmain.util.services.FragmentManager
import ir.arapp.arappmain.util.services.SnackBarMessage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.util.*

@SuppressLint("StaticFieldLeak")
class AddServiceViewModel(application: Application) : AndroidViewModel(application) {
    //    region Variables
    var context: Context
    var category: ArrayList<String>
    var openingYear: ArrayList<String>


    @JvmField
    var startTime: MutableLiveData<String> = MutableLiveData()

    @JvmField
    var endTime: MutableLiveData<String> = MutableLiveData()

    @JvmField
    var title: MutableLiveData<String> = MutableLiveData()

    @JvmField
    var address: MutableLiveData<String> = MutableLiveData()

    @JvmField
    var description: MutableLiveData<String> = MutableLiveData()

    @JvmField
    var summary: MutableLiveData<String> = MutableLiveData()
    var image: MutableLiveData<Bitmap> = MutableLiveData()


    //snakeBar
    var snackBarMessage: SnackBarMessage? = null

    //fragmentManager
    var fragmentManager: FragmentManager? = null


    fun onAddServiceButtonClick(view: View?) {
        var postServiceData = PostServiceData()
        postServiceData.title = title?.value
        postServiceData.summary = summary?.value
        postServiceData.description = description?.value
        postServiceData.address = address?.value
        postServiceData.categoryId = 1
        postServiceData.startTime = startTime?.value
        postServiceData.endTime = endTime?.value
        postServiceData.birth = 12
        image.value?.let { bitmap ->
            postServiceData.pictureBase64 = convertToBase64(bitmap)
        }
        RetrofitClient.api.addNewService(postServiceData)
            .enqueue(object : Callback<PostServiceData> {
                override fun onResponse(
                    call: Call<PostServiceData>,
                    response: Response<PostServiceData>
                ) {
                    Log.i("TAG123123", "onResponse body: ${response.body()?.toString()}")
                    Log.i("TAG123123", "onResponse errorBody : ${response.errorBody()?.string()}")
                    if (response.isSuccessful) {
                        snackBarMessage?.onSuccess(response.message())
                    }
                }

                override fun onFailure(call: Call<PostServiceData>, t: Throwable) {
                    Log.i("TAG123123", "onFailure message: ${t.message}")
                }

            })

    }

    //    Return Methods
    var allCategory: MutableLiveData<ArrayList<String>>

    //    endregion
    var allOpeningYear: MutableLiveData<ArrayList<String>>

    //    region Methods
    //    Initialize
    private fun init() {
        category.addAll(Arrays.asList(*context.resources.getStringArray(R.array.category_list)))
        openingYear.addAll(Arrays.asList(*context.resources.getStringArray(R.array.year_list)))
        allCategory.value = category
        allOpeningYear.value = openingYear
    }

    //    endregion
    //    Constructor
    init {
        //        Hooks
        context = application.applicationContext
        category = ArrayList()
        openingYear = ArrayList()
        allCategory = MutableLiveData()
        allOpeningYear = MutableLiveData()
        //        Initialize
        init()
    }

    fun convertToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
        val encoded: String =
            Base64.encodeToString(byteArray, Base64.DEFAULT)
        return encoded
    }
}