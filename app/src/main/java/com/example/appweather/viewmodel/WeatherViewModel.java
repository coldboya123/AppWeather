package com.example.appweather.viewmodel;

import android.app.Application;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appweather.model.object.WeatherModel;
import com.example.appweather.repository.WeatherRepository;
import com.example.appweather.service.RetrofitClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherViewModel extends AndroidViewModel {

    private MutableLiveData<WeatherModel> weatherModelLiveData;
    private WeatherRepository weatherRepository;



    public WeatherViewModel(@NonNull Application application , WeatherRepository weatherRepository) {
        super(application);
        this.weatherRepository= weatherRepository;
    }

    public LiveData<WeatherModel> getWeather(String appid, String units, String q){
        if (weatherModelLiveData == null){
            weatherModelLiveData = new MutableLiveData<>();
            loadWeather(appid, units, q);
        }
        return weatherModelLiveData;
    }

    private void loadWeather(String appid, String units, String q){
        weatherRepository.fetchWeather(appid, units, q)
                .thenAccept(weatherModel -> weatherModelLiveData.setValue(weatherModel))
                .exceptionally(new Function<Throwable, Void>() {
                    @Override
                    public Void apply(Throwable throwable) {
                        Log.d("BBB", throwable.getMessage().toString());
                        return null;
                    }
                });
    }
}
