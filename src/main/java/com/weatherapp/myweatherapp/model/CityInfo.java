package com.weatherapp.myweatherapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CityInfo {

  @JsonProperty("address")
  String address;

  @JsonProperty("description")
  String description;

  @JsonProperty("currentConditions")
  CurrentConditions currentConditions;

  @JsonProperty("days")
  List<Days> days;

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public CurrentConditions getCurrentConditions() {
    return currentConditions;
  }

  public void setCurrentConditions(CurrentConditions currentConditions) {
    this.currentConditions = currentConditions;
  }

  public List<Days> getDays() {
    return days;
  }

  public void setDays(List<Days> days) {
    this.days = days;
  }

  public static class CurrentConditions {
    @JsonProperty("temp")
    String currentTemperature;

    @JsonProperty("sunrise")
    String sunrise;

    @JsonProperty("sunset")
    String sunset;

    @JsonProperty("feelslike")
    String feelslike;

    @JsonProperty("humidity")
    String humidity;

    @JsonProperty("conditions")
    String conditions;

    public CurrentConditions() {
    }

    public CurrentConditions(String sunrise, String sunset) {
      this.sunrise = sunrise;
      this.sunset = sunset;
    }

    public CurrentConditions(String conditions) {
      this.conditions = conditions;
    }

    public String getCurrentTemperature() {
      return currentTemperature;
    }

    public void setCurrentTemperature(String currentTemperature) {
      this.currentTemperature = currentTemperature;
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

    public String getFeelslike() {
      return feelslike;
    }

    public void setFeelslike(String feelslike) {
      this.feelslike = feelslike;
    }

    public String getHumidity() {
      return humidity;
    }

    public void setHumidity(String humidity) {
      this.humidity = humidity;
    }

    public String getConditions() {
      return conditions;
    }

    public void setConditions(String conditions) {
      this.conditions = conditions;
    }
  }

  public static class Days {

    @JsonProperty("datetime")
    String date;

    @JsonProperty("temp")
    String currentTemperature;

    @JsonProperty("tempmax")
    String maxTemperature;

    @JsonProperty("tempmin")
    String minTemperature;

    @JsonProperty("conditions")
    String conditions;

    @JsonProperty("description")
    String description;

    public String getDate() {
      return date;
    }

    public void setDate(String date) {
      this.date = date;
    }

    public String getCurrentTemperature() {
      return currentTemperature;
    }

    public void setCurrentTemperature(String currentTemperature) {
      this.currentTemperature = currentTemperature;
    }

    public String getMaxTemperature() {
      return maxTemperature;
    }

    public void setMaxTemperature(String maxTemperature) {
      this.maxTemperature = maxTemperature;
    }

    public String getMinTemperature() {
      return minTemperature;
    }

    public void setMinTemperature(String minTemperature) {
      this.minTemperature = minTemperature;
    }

    public String getConditions() {
      return conditions;
    }

    public void setConditions(String conditions) {
      this.conditions = conditions;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }
  }
}
