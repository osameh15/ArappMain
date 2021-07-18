package ir.arapp.arappmain.util.server

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {
    companion object {
        private val retrofit: Retrofit = Retrofit.Builder().baseUrl(Api.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
        val api = retrofit.create(Api::class.java)

    }

    init {

//        api.getServiceById(11).enqueue(object:Callback<ResponseModel<GetService>>{
//            override fun onResponse(
//                call: Call<ResponseModel<GetService>>,
//                response: Response<ResponseModel<GetService>>
//            ) {
//                Log.i("ResponseTAG", "get service onResponse: ${response.isSuccessful}")
//                Log.i("ResponseTAG", "get service onResponse: ${response.errorBody()?.string()}")
//            }
//
//            override fun onFailure(call: Call<ResponseModel<GetService>>, t: Throwable) {
//                Log.i("ResponseTAG", "get service onFailure: ${t.message}")
//            }
//        })
//        api.resendUser(RegisterResult(6)).enqueue(object:Callback<ResponseModel<RegisterResult>>{
//            override fun onResponse(
//                call: Call<ResponseModel<RegisterResult>>,
//                response: Response<ResponseModel<RegisterResult>>
//            ) {
//                Log.i("ResponseTAG", "get service onResponse: ${response.isSuccessful}")
//                Log.i("ResponseTAG", "get service onResponse: ${response.errorBody()?.string()}")
//            }
//
//            override fun onFailure(call: Call<ResponseModel<RegisterResult>>, t: Throwable) {
//                Log.i("ResponseTAG", "get service onFailure: ${t.message}")
//            }
//        })

//        api.verifyUser(Verify(6, 23453)).enqueue(object : Callback<ResponseBody> {
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                Log.i("ResponseTAG", "get service onResponse: ${response.isSuccessful}")
//                Log.i("ResponseTAG", "get service onResponse: ${response.errorBody()?.string()}")
//            }
//
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                Log.i("ResponseTAG", "get service onFailure: ${t.message}")
//            }
//        })


//        api.registerUser(registerBody = RegisterBody("09361051762"))
//            .enqueue(object : Callback<ResponseModel<RegisterResult>> {
//                override fun onResponse(
//                    call: Call<ResponseModel<RegisterResult>>,
//                    response: Response<ResponseModel<RegisterResult>>
//                ) {
//                    if (response.isSuccessful)
//                        Log.i(
//                            "ResponseTAG",
//                            " remove service successfull:   ${response.body()}"
//                        )
//                    else Log.i(
//                        "ResponseTAG",
//                        "remove service: ${response.code()}  ${response.errorBody()?.string()}"
//                    )
//                }
//
//                override fun onFailure(call: Call<ResponseModel<RegisterResult>>, t: Throwable) {
//                    Log.i("ResponseTAG", " remove service onFailure:   ${t.message}")
//                }
//            })
//        api.getAllServices().enqueue(object : Callback<ResponseModel<GetAllServices>> {
//            override fun onResponse(call: Call<ResponseModel<GetAllServices>>, response: Response<ResponseModel<GetAllServices>>) {
//                response.body()?.let {
//                    Log.i("ResponseTAG", " $it")
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseModel<GetAllServices>>, t: Throwable) {
//                Log.i("ResponseTAG", "onFailure ${t.message}")
//            }
//        })

//        api.getAllCategory().enqueue(object:Callback<ResponseModel<GetAllCategories>>{
//            override fun onResponse(
//                call: Call<ResponseModel<GetAllCategories>>,
//                response: Response<ResponseModel<GetAllCategories>>
//            ) {
//                if (response.isSuccessful)
//                    Log.i(
//                        "ResponseTAG",
//                        " remove service successfull:   ${response.body()}"
//                    )
//                else Log.i(
//                    "ResponseTAG",
//                    "remove service: ${response.code()}  ${response.errorBody()?.string()}"
//                )
//            }
//
//            override fun onFailure(call: Call<ResponseModel<GetAllCategories>>, t: Throwable) {
//                Log.i("ResponseTAG", " remove service onFailure:   ${t.message}")
//            }
//        })


//        api.deleteServiceById(10).enqueue(object : Callback<ResponseBody> {
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                if (response.isSuccessful)
//                    Log.i(
//                        "ResponseTAG",
//                        " remove service successfull:   ${response.body()?.string()}"
//                    )
//                else Log.i(
//                    "ResponseTAG",
//                    "remove service: ${response.code()}  ${response.errorBody()?.string()}"
//                )
//            }
//
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                Log.i("ResponseTAG", " remove service onFailure:   ${t.message}")
//            }
//        })
//        var bitmap =
//            ResourcesCompat.getDrawable(resources, R.drawable.add_service, null)?.toBitmap()
//        bitmap?.let { bitmap ->
//            val byteArrayOutputStream = ByteArrayOutputStream()
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
//            val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
//            val encoded: String = Base64.encodeToString(byteArray, Base64.DEFAULT)
//            var gson = GsonBuilder().setLenient().create()
//            var service = gson.toJson(SendService(encoded, "addService451"))
//            Log.i("ResponseTAG", "service: ${service}")
//            api.updateService(2, SendService(encoded, "test$33"))
//                .enqueue(object : Callback<SendService> {
//                    override fun onResponse(
//                        call: Call<SendService>,
//                        response: Response<SendService>
//                    ) {
//                        response.let {
//                            if (it.isSuccessful)
//                                Log.i("ResponseTAG", "add Service:   ${it.body()}")
//                            else Log.i(
//                                "ResponseTAG",
//                                "add Service: ${it.code()}  ${it.errorBody()?.string()}"
//                            )
//                        }
//                    }
//
//                    override fun onFailure(call: Call<SendService>, t: Throwable) {
//                        Log.i("ResponseTAG", "add Service onFailure:   ${t.message}")
//                    }
//                })


//            (3..6).forEach {
//                api.addNewService(SendService(encoded, "test$2"))
//                    .enqueue(object : Callback<SendService> {
//                        override fun onResponse(
//                            call: Call<SendService>,
//                            response: Response<SendService>
//                        ) {
//                            response.let {
//                                if (it.isSuccessful)
//                                    Log.i("ResponseTAG", "add Service:   ${it.body()}")
//                                else Log.i(
//                                    "ResponseTAG",
//                                    "add Service: ${it.code()}  ${it.errorBody()?.string()}"
//                                )
//                            }
//                        }
//                        override fun onFailure(call: Call<SendService>, t: Throwable) {
//                            Log.i("ResponseTAG", "add Service onFailure:   ${t.message}")
//                        }
//                    })
//            }

    }
}