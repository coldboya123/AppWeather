package com.example.appweather.model.object;

import com.google.gson.annotations.SerializedName;

public class Main {
    @SerializedName("temp")
    private int temp;

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
}
