package com.example.appweather.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appweather.model.ApiRequest;
import com.example.appweather.model.object.Weather;
import com.example.appweather.model.object.WeatherModel;
import com.example.appweather.service.RetrofitClient;

import java.util.concurrent.CompletableFuture;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableConverter;
import io.reactivex.rxjava3.core.Flowable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {
    ApiRequest apiRequest;

    public WeatherRepository(){
        apiRequest = RetrofitClient.getInstance().getApiRequest();
    }

    public CompletableFuture<WeatherModel> fetchWeather(String appid, String units ,String q){
        CompletableFuture<WeatherModel> complete = new CompletableFuture<>();

        Call<WeatherModel> weatherData = RetrofitClient.getInstance().getApiRequest().getWeather(appid, units, q);
        weatherData.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if (response.body() != null){
                    complete.complete(response.body());
                }
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
              complete.completeExceptionally(t);
            }
        });
        return complete;
    }

}
