package com.example.appweather.view;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.appweather.R;
import com.example.appweather.model.object.WeatherModel;
import com.example.appweather.repository.WeatherRepository;
import com.example.appweather.viewmodel.WeatherViewModel;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    WeatherViewModel weatherViewModel;
    WeatherRepository weatherRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        mapView();
        observerData();
    }

    private void init() {
        img = findViewById(R.id.img);

        weatherRepository = new WeatherRepository();
    }

    private void mapView() {
        weatherViewModel = new WeatherViewModel(getApplication(), weatherRepository);
    }

    private void observerData() {
        weatherViewModel.getWeather("86183a23377ed034aef7aad102f43d64", "metric", "hanoi")
                .observe(this, new Observer<WeatherModel>() {
                    @Override
                    public void onChanged(WeatherModel weatherModel) {
                        if (weatherModel!=null) {
                            Log.d("bbb", "onChanged: " + weatherModel.getName());
                            Log.d("bbb", "onChanged: " + weatherModel.getCod());
                            Log.d("bbb", "onChanged: " + weatherModel.getMessage());

                            try {
                                Log.d("bbb", "onResponse: " + new SimpleDateFormat("yyyyMMdd").parse(String.valueOf(weatherModel.getDt())));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }
}