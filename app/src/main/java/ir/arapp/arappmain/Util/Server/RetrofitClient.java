package ir.arapp.arappmain.Util.Server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient
{
    private static final String BASE_URL = "http://cafestudy.ir/public/";
    private static RetrofitClient mInstance;
    private final Retrofit retrofit;

    private RetrofitClient()
    {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static synchronized RetrofitClient getInstance()
    {
        if (mInstance == null)
        {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public Api getApi()
    {
        return retrofit.create(Api.class);
    }
}
