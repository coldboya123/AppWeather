package com.example.appweather.model;

import com.example.appweather.model.object.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {

    @GET("data/2.5/weather")
    Call<WeatherModel> getWeather(@Query("appid") String appid,
                                  @Query("units") String units,
                                  @Query("q") String q);
}
