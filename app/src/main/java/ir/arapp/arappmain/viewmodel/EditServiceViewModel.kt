package ir.arapp.arappmain.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ir.arapp.arappmain.model.base.GetServiceData
import ir.arapp.arappmain.model.base.UpdateServiceData
import ir.arapp.arappmain.model.base.UpdateServiceWithImage
import ir.arapp.arappmain.util.server.RetrofitClient
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.net.URL


@SuppressLint("StaticFieldLeak")
class EditServiceViewModel(application: Application) : AndroidViewModel(application) {
    //    region Variables
    var context: Context
    var id = MutableLiveData<Int>()
    var title = MutableLiveData<String>()
    var summary = MutableLiveData<String>()
    var birth = MutableLiveData<Int>()
    var description = MutableLiveData<String>()
    var address = MutableLiveData<String>()
    var startTime = MutableLiveData<String>()
    var endTime = MutableLiveData<String>()
    var categoryId = MutableLiveData<Int>()
    var imageUrl = MutableLiveData<String>()

    //    region Methods
    //    Initialize
    private fun init() {} //    endregion
    fun setValues(serviceData: GetServiceData) {
        title.value = serviceData.title
        address.value = serviceData.address
        summary.value = serviceData.summry
        description.value = serviceData.description
        startTime.value = serviceData.startTime
        endTime.value = serviceData.endTime
        categoryId.value = serviceData.categoryId
        birth.value = serviceData.birth
        id.value = serviceData.id
        imageUrl.value = serviceData.pictureUrl
    }

    fun convertToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
        val encoded: String =
            Base64.encodeToString(byteArray, Base64.DEFAULT)
        return encoded
    }

    @SuppressLint("CheckResult")
    fun updateService(view: View?) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val url = URL(imageUrl.value)
                val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                val base64 = convertToBase64(image!!)
                val postServiceData = UpdateServiceWithImage().apply {
                    address = this@EditServiceViewModel.address.value!!
                    birth = this@EditServiceViewModel.birth.value!!
                    categoryId = this@EditServiceViewModel.categoryId.value!!
                    description = this@EditServiceViewModel.description.value!!
                    summary = this@EditServiceViewModel.summary.value!!
                    startTime = this@EditServiceViewModel.startTime.value!!
                    endTime = this@EditServiceViewModel.endTime.value!!
                    pictureBase64 = base64
                }
                RetrofitClient.api.updateServiceWithImage(id.value!!, postServiceData)
                    .enqueue(object : Callback<ResponseBody> {
                        override fun onResponse(
                            call: Call<ResponseBody>,
                            response: Response<ResponseBody>
                        ) {
                            Log.i("TAGsdfsdfsd", "onResponse: ")
                        }

                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            Log.i("TAGsdfsdfsd", "onResponse: ")
                        }
                    })

            } catch (e: IOException) {
                val postServiceData = UpdateServiceData().apply {
                    address = this@EditServiceViewModel.address.value!!
                    birth = this@EditServiceViewModel.birth.value!!
                    categoryId = this@EditServiceViewModel.categoryId.value!!
                    description = this@EditServiceViewModel.description.value!!
                    summary = this@EditServiceViewModel.summary.value!!
                    startTime = this@EditServiceViewModel.startTime.value!!
                    endTime = this@EditServiceViewModel.endTime.value!!
                }
                RetrofitClient.api.updateService(id.value!!, postServiceData)
                    .enqueue(object : Callback<ResponseBody> {
                        override fun onResponse(
                            call: Call<ResponseBody>,
                            response: Response<ResponseBody>
                        ) {
                            Log.i("TAGsdfsdfsd", "onResponse: ")
                        }

                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            Log.i("TAGsdfsdfsd", "onResponse: ")
                        }
                    })
            }
        }
    }
    init {
        //        Hooks
        context = application.applicationContext
        //        Initialize
        init()
    }
}