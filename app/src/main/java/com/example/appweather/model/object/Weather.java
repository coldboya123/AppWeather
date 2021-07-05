package com.example.appweather.model.object;

import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("main")
    private String main;
    @SerializedName("icon")
    private String icon;

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
