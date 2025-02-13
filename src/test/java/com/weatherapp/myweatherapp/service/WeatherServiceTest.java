package com.weatherapp.myweatherapp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.model.CompareCity;
import com.weatherapp.myweatherapp.repository.VisualcrossingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class WeatherServiceTest {

    @Mock
    private VisualcrossingRepository weatherRepo;

    @InjectMocks
    private WeatherService weatherService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void compareDaylightHours_FirstCityLonger() {
        // Arrange
        CityInfo london = new CityInfo();
        CityInfo.CurrentConditions londonConditions = new CityInfo.CurrentConditions();
        londonConditions.setSunrise("06:00");
        londonConditions.setSunset("20:00");
        london.setCurrentConditions(londonConditions);

        CityInfo paris = new CityInfo();
        CityInfo.CurrentConditions parisConditions = new CityInfo.CurrentConditions();
        parisConditions.setSunrise("07:00");
        parisConditions.setSunset("19:00");
        paris.setCurrentConditions(parisConditions);

        when(weatherRepo.getByCity("London")).thenReturn(london);
        when(weatherRepo.getByCity("Paris")).thenReturn(paris);

        // Act
        CompareCity result = weatherService.compareDaylightHours("London", "Paris");

        // Assert
        assertEquals("London", result.getCity());
        assertTrue(result.getDescription().contains("London has longer daylight hours"));
        assertTrue(result.getDescription().contains("14 hours"));
    }

    @Test
    void compareDaylightHours_SecondCityLonger() {
        // Arrange
        CityInfo london = new CityInfo();
        CityInfo.CurrentConditions londonConditions = new CityInfo.CurrentConditions();
        londonConditions.setSunrise("07:00");
        londonConditions.setSunset("19:00");
        london.setCurrentConditions(londonConditions);

        CityInfo paris = new CityInfo();
        CityInfo.CurrentConditions parisConditions = new CityInfo.CurrentConditions();
        parisConditions.setSunrise("06:00");
        parisConditions.setSunset("20:00");
        paris.setCurrentConditions(parisConditions);

        when(weatherRepo.getByCity("London")).thenReturn(london);
        when(weatherRepo.getByCity("Paris")).thenReturn(paris);

        // Act
        CompareCity result = weatherService.compareDaylightHours("London", "Paris");

        // Assert
        assertEquals("Paris", result.getCity());
        assertTrue(result.getDescription().contains("Paris has longer daylight hours"));
        assertTrue(result.getDescription().contains("14 hours"));
    }

    @Test
    void compareRain_BothCitiesRaining() {
        // Arrange
        CityInfo london = new CityInfo();
        CityInfo.CurrentConditions londonConditions = new CityInfo.CurrentConditions();
        londonConditions.setConditions("Light Rain");
        london.setCurrentConditions(londonConditions);

        CityInfo paris = new CityInfo();
        CityInfo.CurrentConditions parisConditions = new CityInfo.CurrentConditions();
        parisConditions.setConditions("Heavy Rain");
        paris.setCurrentConditions(parisConditions);

        when(weatherRepo.getByCity("London")).thenReturn(london);
        when(weatherRepo.getByCity("Paris")).thenReturn(paris);

        // Act
        CompareCity result = weatherService.compareRain("London", "Paris");

        // Assert
        assertEquals("London and Paris", result.getCity());
        assertEquals("It is currently raining in both cities", result.getDescription());
    }

    @Test
    void compareRain_FirstCityRaining() {
        // Arrange
        CityInfo london = new CityInfo();
        CityInfo.CurrentConditions londonConditions = new CityInfo.CurrentConditions();
        londonConditions.setConditions("Light Rain");
        london.setCurrentConditions(londonConditions);

        CityInfo paris = new CityInfo();
        CityInfo.CurrentConditions parisConditions = new CityInfo.CurrentConditions();
        parisConditions.setConditions("Clear");
        paris.setCurrentConditions(parisConditions);

        when(weatherRepo.getByCity("London")).thenReturn(london);
        when(weatherRepo.getByCity("Paris")).thenReturn(paris);

        // Act
        CompareCity result = weatherService.compareRain("London", "Paris");

        // Assert
        assertEquals("London", result.getCity());
        assertEquals("It is currently raining in London", result.getDescription());
    }

    @Test
    void compareRain_SecondCityRaining() {
        // Arrange
        CityInfo london = new CityInfo();
        CityInfo.CurrentConditions londonConditions = new CityInfo.CurrentConditions();
        londonConditions.setConditions("Clear");
        london.setCurrentConditions(londonConditions);

        CityInfo paris = new CityInfo();
        CityInfo.CurrentConditions parisConditions = new CityInfo.CurrentConditions();
        parisConditions.setConditions("Rain");
        paris.setCurrentConditions(parisConditions);

        when(weatherRepo.getByCity("London")).thenReturn(london);
        when(weatherRepo.getByCity("Paris")).thenReturn(paris);

        // Act
        CompareCity result = weatherService.compareRain("London", "Paris");

        // Assert
        assertEquals("Paris", result.getCity());
        assertEquals("It is currently raining in Paris", result.getDescription());
    }

    @Test
    void compareRain_NoCityRaining() {
        // Arrange
        CityInfo london = new CityInfo();
        CityInfo.CurrentConditions londonConditions = new CityInfo.CurrentConditions();
        londonConditions.setConditions("Clear");
        london.setCurrentConditions(londonConditions);

        CityInfo paris = new CityInfo();
        CityInfo.CurrentConditions parisConditions = new CityInfo.CurrentConditions();
        parisConditions.setConditions("Cloudy");
        paris.setCurrentConditions(parisConditions);

        when(weatherRepo.getByCity("London")).thenReturn(london);
        when(weatherRepo.getByCity("Paris")).thenReturn(paris);

        // Act
        CompareCity result = weatherService.compareRain("London", "Paris");

        // Assert
        assertEquals("None", result.getCity());
        assertEquals("It is not raining in either city", result.getDescription());
    }
}