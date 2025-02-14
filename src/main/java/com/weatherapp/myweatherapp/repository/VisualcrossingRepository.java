package com.weatherapp.myweatherapp.repository;

import com.weatherapp.myweatherapp.exception.InvalidCityException;
import com.weatherapp.myweatherapp.model.CityInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Repository
public class VisualcrossingRepository {

    @Value("${weather.visualcrossing.url}")
    String url;
    
    @Value("${weather.visualcrossing.key}")
    String key;

    private RestTemplate createRestTemplateWithTimeout() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(3000);  // 3 seconds timeout
        factory.setReadTimeout(3000);     // 3 seconds timeout
        return new RestTemplate(factory);
    }

    public CityInfo getByCity(String city) {
        String uri = url + "timeline/" + city + "?key=" + key;
        RestTemplate restTemplate = createRestTemplateWithTimeout();
        
        try {
            return restTemplate.getForObject(uri, CityInfo.class);
        } catch (HttpClientErrorException.BadRequest e) {
            throw new InvalidCityException("Invalid location parameter value: " + city);
        } catch (ResourceAccessException e) {
            throw new InvalidCityException("Timeout while fetching weather data for: " + city);
        } catch (Exception e) {
            throw new InvalidCityException("Error fetching weather data for: " + city);
        }
    }
}
