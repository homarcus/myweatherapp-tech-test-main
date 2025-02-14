package com.weatherapp.myweatherapp.controller;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.model.CompareCity;
import com.weatherapp.myweatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

  @Autowired
  WeatherService weatherService;

  @GetMapping("/forecast/{city}")
  public ResponseEntity<CityInfo> forecastByCity(@PathVariable("city") String city) {

    CityInfo ci = weatherService.forecastByCity(city);

    return ResponseEntity.ok(ci);
  }

  @GetMapping("/compare/daylightHours/{city1}/{city2}")
  public ResponseEntity<CompareCity> compareDaylightHours(
      @PathVariable("city1") String city1,
      @PathVariable("city2") String city2) {
    CompareCity result = weatherService.compareDaylightHours(city1, city2);
    return ResponseEntity.ok(result);
  }

  @GetMapping("/compare/rain/{city1}/{city2}")
  public ResponseEntity<CompareCity> compareRain(
      @PathVariable("city1") String city1,
      @PathVariable("city2") String city2) {
    CompareCity result = weatherService.compareRain(city1, city2);
    return ResponseEntity.ok(result);
  }

}
