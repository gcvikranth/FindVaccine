package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import service.CoWINAPIService;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public abstract  class BaseClient {

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .cache(null)
            .build();

    final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://cdn-api.co-vin.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build();

    SimpleDateFormat simpleDateFormat =new SimpleDateFormat("dd MMM yyyy HH:mm:ss");

    public CoWINAPIService apiService = retrofit.create(CoWINAPIService.class);

}
