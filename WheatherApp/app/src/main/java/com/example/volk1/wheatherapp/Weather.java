package com.example.volk1.wheatherapp;

public class Weather {

    private String city;
    private String temperature;
    private String icon;
    private int condition;

    public String getCity() {
        return city;
    }

    public String getTemperature() {
        return temperature + "°";
    }

    public String getIcon() {
        return icon;
    }
}
