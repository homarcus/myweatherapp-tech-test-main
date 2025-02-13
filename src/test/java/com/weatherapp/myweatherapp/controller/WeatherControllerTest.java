package com.weatherapp.myweatherapp.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.weatherapp.myweatherapp.model.CompareCity;
import com.weatherapp.myweatherapp.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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
        compareCity.setDescription("London has longer daylight hours: 14 hours and 0 minutes");

        when(weatherService.compareDaylightHours("London", "Paris"))
                .thenReturn(compareCity);

        // Act & Assert
        mockMvc.perform(get("/compare/daylightHours/London/Paris"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city").value("London"))
                .andExpect(jsonPath("$.description").value("London has longer daylight hours: 14 hours and 0 minutes"));
    }

    @Test
    void compareDaylightHours_Error() throws Exception {
        // Arrange
        when(weatherService.compareDaylightHours("London", "Paris"))
                .thenThrow(new RuntimeException("API Error"));

        // Act & Assert
        mockMvc.perform(get("/compare/daylightHours/London/Paris"))
                .andExpect(status().isBadRequest());
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
    void compareRain_Error() throws Exception {
        // Arrange
        when(weatherService.compareRain("London", "Paris"))
                .thenThrow(new RuntimeException("API Error"));

        // Act & Assert
        mockMvc.perform(get("/compare/rain/London/Paris"))
                .andExpect(status().isBadRequest());
    }
}
