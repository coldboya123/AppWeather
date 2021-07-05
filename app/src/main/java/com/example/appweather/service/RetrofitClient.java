package com.example.appweather.service;

import com.example.appweather.model.ApiRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static RetrofitClient instance = null;
    private static Retrofit retrofit = null;
    private static ApiRequest apiRequest = null;

    private RetrofitClient(){
        retrofit = createRetrofit();
        apiRequest = retrofit.create(ApiRequest.class);
    }

    public static RetrofitClient getInstance(){
        if (instance==null){
            instance = new RetrofitClient();
        }
        return instance;
    }

    public Retrofit createRetrofit() {
        Retrofit retrofit = null;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                .build();

        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }
    public Retrofit getRetrofit(){
        return retrofit;
    }

    public ApiRequest getApiRequest(){
        return apiRequest;
    }
}
