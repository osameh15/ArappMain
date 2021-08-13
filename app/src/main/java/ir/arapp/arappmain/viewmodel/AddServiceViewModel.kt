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
import ir.arapp.arappmain.R
import ir.arapp.arappmain.model.base.Category
import ir.arapp.arappmain.model.base.GetAllCategories
import ir.arapp.arappmain.model.base.PostServiceData
import ir.arapp.arappmain.model.base.ResponseModel
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

    var category_id: MutableLiveData<Int> = MutableLiveData()

    var birth = MutableLiveData<Int>()
    //snakeBar
    var snackBarMessage: SnackBarMessage? = null

    //fragmentManager
    var fragmentManager: FragmentManager? = null


    fun onAddServiceButtonClick(view: View?) {

        var array = arrayOf(R.drawable.restaurant_1,R.drawable.restaurant_2,R.drawable.restaurant_3,R.drawable.coffe_1,R.drawable.coffe_2,R.drawable.coffe_3,R.drawable.hotel_1,R.drawable.hotel_2,R.drawable.hotel_3)
        var random = kotlin.random.Random(java.util.Calendar.getInstance().timeInMillis)
        var bitmap =
            ResourcesCompat.getDrawable(view!!.resources, array[random.nextInt(from = 0,until = array.lastIndex)], null)?.toBitmap()
        image.value = bitmap!!
        val postServiceData = PostServiceData()
        postServiceData.title = title?.value
        postServiceData.summary = summary?.value
        postServiceData.description = description?.value
        postServiceData.address = address?.value
        postServiceData.categoryId = category_id.value!!
        postServiceData.startTime = startTime?.value
        postServiceData.endTime = endTime?.value
        postServiceData.birth = birth.value!!
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
    var allCategory: MutableLiveData<ArrayList<Category>>

    //    endregion
    var allOpeningYear: MutableLiveData<ArrayList<String>>

    //    region Methods
    //    Initialize
    private fun init() {
//        category.addAll(Arrays.asList(*context.resources.getStringArray(R.array.category_list)))

        openingYear.addAll(Arrays.asList(*context.resources.getStringArray(R.array.year_list)))
//        allCategory.value = category
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

    fun update() {
        RetrofitClient.api.getAllCategory().enqueue(object:Callback<ResponseModel<GetAllCategories>>{
            override fun onResponse(
                call: Call<ResponseModel<GetAllCategories>>,
                response: Response<ResponseModel<GetAllCategories>>
            ) {
                response.body()?.let {
                    it.result?.let {
                        it.categories?.let {
                            allCategory.value = it as ArrayList<Category>
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseModel<GetAllCategories>>, t: Throwable) {

            }
        })
    }
}