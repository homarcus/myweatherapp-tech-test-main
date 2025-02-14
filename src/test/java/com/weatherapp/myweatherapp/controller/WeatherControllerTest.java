package com.weatherapp.myweatherapp.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.weatherapp.myweatherapp.model.CompareCity;
import com.weatherapp.myweatherapp.service.WeatherService;
import com.weatherapp.myweatherapp.exception.InvalidCityException;

@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @Test
    void compareDaylightHours_Success() throws Exception {
        // Arrange
        CompareCity compareCity = new CompareCity();
        compareCity.setCity("London");
        compareCity.setDescription("London has longer daylight hours with 14 hours compared to Paris with 13 hours");
        
        when(weatherService.compareDaylightHours("London", "Paris"))
            .thenReturn(compareCity);

        // Act & Assert
        mockMvc.perform(get("/compare/daylightHours/London/Paris"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.city").value("London"))
            .andExpect(jsonPath("$.description").value("London has longer daylight hours with 14 hours compared to Paris with 13 hours"));
    }

    @Test
    void compareDaylightHours_InvalidCity_Returns404() throws Exception {
        when(weatherService.compareDaylightHours("InvalidCity", "London"))
            .thenThrow(new InvalidCityException("Invalid location parameter value: InvalidCity"));

        mockMvc.perform(get("/compare/daylightHours/InvalidCity/London"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.error").value("Invalid City"))
            .andExpect(jsonPath("$.message").value("Invalid location parameter value: InvalidCity"));
    }

    @Test
    void compareRain_Success() throws Exception {
        // Arrange
        CompareCity compareCity = new CompareCity();
        compareCity.setCity("London");
        compareCity.setDescription("It is currently raining in London");
        
        when(weatherService.compareRain("London", "Paris"))
            .thenReturn(compareCity);

        // Act & Assert
        mockMvc.perform(get("/compare/rain/London/Paris"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.city").value("London"))
            .andExpect(jsonPath("$.description").value("It is currently raining in London"));
    }

    @Test
    void compareRain_InvalidCity_Returns404() throws Exception {
        when(weatherService.compareRain("London", "InvalidCity"))
            .thenThrow(new InvalidCityException("Invalid location parameter value: InvalidCity"));

        mockMvc.perform(get("/compare/rain/London/InvalidCity"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.error").value("Invalid City"))
            .andExpect(jsonPath("$.message").value("Invalid location parameter value: InvalidCity"));
    }

    @Test
    void compareRain_BothCitiesRaining() throws Exception {
        // Arrange
        CompareCity compareCity = new CompareCity();
        compareCity.setCity("London and Paris");
        compareCity.setDescription("It is currently raining in both cities");
        
        when(weatherService.compareRain("London", "Paris"))
            .thenReturn(compareCity);

        // Act & Assert
        mockMvc.perform(get("/compare/rain/London/Paris"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.city").value("London and Paris"))
            .andExpect(jsonPath("$.description").value("It is currently raining in both cities"));
    }

    @Test
    void compareRain_NoCityRaining() throws Exception {
        // Arrange
        CompareCity compareCity = new CompareCity();
        compareCity.setCity("None");
        compareCity.setDescription("It is not raining in either city");
        
        when(weatherService.compareRain("London", "Paris"))
            .thenReturn(compareCity);

        // Act & Assert
        mockMvc.perform(get("/compare/rain/London/Paris"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.city").value("None"))
            .andExpect(jsonPath("$.description").value("It is not raining in either city"));
    }
}
