package ir.arapp.arappmain.util.server

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientOld private constructor() {
    private val retrofit: Retrofit

    //    endregion
    val api: Api
        get() = retrofit.create(Api::class.java)

    companion object {
        //    region Variable
        private const val BASE_URL = "http://arappOfficial.com/"
        private var mInstance: RetrofitClientOld? = null

        @get:Synchronized
        val instance: RetrofitClientOld?
            get() {
                if (mInstance == null) {
                    mInstance = RetrofitClientOld()
                }
                return mInstance
            }
    }

    //    endregion
    //    region Retrofit client
    init {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}