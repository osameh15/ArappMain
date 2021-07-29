package ir.arapp.arappmain.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ir.arapp.arappmain.model.base.GetAllServices
import ir.arapp.arappmain.model.base.GetServiceData
import ir.arapp.arappmain.model.base.PostServiceData
import ir.arapp.arappmain.model.base.ResponseModel
import ir.arapp.arappmain.util.server.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyServicesViewModel(application: Application) :AndroidViewModel(application) {
    fun update() {
        RetrofitClient.api.getMyServices().enqueue(object: Callback<ResponseModel<GetAllServices>> {
            override fun onResponse(
                call: Call<ResponseModel<GetAllServices>>,
                response: Response<ResponseModel<GetAllServices>>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        it.result?.let {
                            it.services?.let {
                                services.value = it
                            }
                        }
                    }
                }

            }

            override fun onFailure(call: Call<ResponseModel<GetAllServices>>, t: Throwable) {

            }
        })
    }
    var services = MutableLiveData<ArrayList<GetServiceData>>()
    init {
        update()
    }
}