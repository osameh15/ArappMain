package ir.arapp.arappmain.util.server

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import ir.arapp.arappmain.model.base.LoginMobile
import ir.arapp.arappmain.model.base.LoginToken
import ir.arapp.arappmain.model.base.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        fun login(phoneNumber: String, password: String) {
            api.loginUser(LoginMobile(phoneNumber , password))
                .enqueue(object : Callback<ResponseModel<List<LoginToken>>> {
                    override fun onResponse(
                        call: Call<ResponseModel<List<LoginToken>>>,
                        response: Response<ResponseModel<List<LoginToken>>>
                    ) {
                        response.body()?.result?.let {
                            if (it.isNotEmpty()){
                                it.get(0).loginToken?.let {
                                    Api.userToken = "Bearer $it"
                                }
                            }
                        }
                    }

                    override fun onFailure(
                        call: Call<ResponseModel<List<LoginToken>>>,
                        t: Throwable
                    ) {

                    }
                })
        }
        private val retrofit: Retrofit = Retrofit.Builder().baseUrl(Api.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
        val api = retrofit.create(Api::class.java)

    }
}