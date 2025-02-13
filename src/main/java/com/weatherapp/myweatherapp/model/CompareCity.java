package com.weatherapp.myweatherapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompareCity {
    @JsonProperty("city")
    private String city;

    @JsonProperty("description")
    private String description;

    @JsonProperty("sunrise")
    private String sunrise;

    @JsonProperty("sunset")
    private String sunset;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
}