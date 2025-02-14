package com.weatherapp.myweatherapp.service;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.model.CompareCity;
import com.weatherapp.myweatherapp.repository.VisualcrossingRepository;
import com.weatherapp.myweatherapp.exception.InvalidCityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.time.Duration;

@Service
public class WeatherService {

  @Autowired
  VisualcrossingRepository weatherRepo;

  public CityInfo forecastByCity(String city) {
    try {
      CityInfo cityInfo = weatherRepo.getByCity(city);
      if (cityInfo == null || cityInfo.getCurrentConditions() == null) {
        throw new InvalidCityException("Invalid location parameter value: " + city);
      }
      return cityInfo;
    } catch (Exception e) {
      throw new InvalidCityException("Invalid location parameter value: " + city);
    }
  }
  
  private CityInfo getCityInfo(String city) {
    try {
      CityInfo cityInfo = weatherRepo.getByCity(city);
      if (cityInfo == null || cityInfo.getCurrentConditions() == null) {
        throw new InvalidCityException("Invalid location parameter value: " + city);
      }
      return cityInfo;
    } catch (Exception e) {
      throw new InvalidCityException("Error fetching weather data for city: " + city);
    }
  }

  public CompareCity compareDaylightHours(String city1, String city2) {
    CityInfo city1Info = getCityInfo(city1);
    CityInfo city2Info = getCityInfo(city2);

    LocalTime sunrise1 = LocalTime.parse(city1Info.getCurrentConditions().getSunrise());
    LocalTime sunset1 = LocalTime.parse(city1Info.getCurrentConditions().getSunset());
    LocalTime sunrise2 = LocalTime.parse(city2Info.getCurrentConditions().getSunrise());
    LocalTime sunset2 = LocalTime.parse(city2Info.getCurrentConditions().getSunset());

    Duration daylight1 = Duration.between(sunrise1, sunset1);
    Duration daylight2 = Duration.between(sunrise2, sunset2);

    CompareCity result = new CompareCity();
    if (daylight1.compareTo(daylight2) > 0) {
      result.setCity(city1);
      result.setDescription(city1 + " has longer daylight hours: " + formatDuration(daylight1));
    } else {
      result.setCity(city2);
      result.setDescription(city2 + " has longer daylight hours: " + formatDuration(daylight2));
    }
    return result;
  }

  public CompareCity compareRain(String city1, String city2) {
    CityInfo city1Info = getCityInfo(city1);
    CityInfo city2Info = getCityInfo(city2);

    boolean isRaining1 = city1Info.getCurrentConditions().getConditions().toLowerCase().contains("rain");
    boolean isRaining2 = city2Info.getCurrentConditions().getConditions().toLowerCase().contains("rain");

    CompareCity result = new CompareCity();
    if (isRaining1 && isRaining2) {
      result.setCity(city1 + " and " + city2);
      result.setDescription("It is currently raining in both cities");
    } else if (isRaining1) {
      result.setCity(city1);
      result.setDescription("It is currently raining in " + city1);
    } else if (isRaining2) {
      result.setCity(city2);
      result.setDescription("It is currently raining in " + city2);
    } else {
      result.setCity("None");
      result.setDescription("It is not raining in either city");
    }
    return result;
  }

  private String formatDuration(Duration duration) {
    long hours = duration.toHours();
    long minutes = duration.toMinutesPart();
    return String.format("%d hours and %d minutes", hours, minutes);
  }
}
