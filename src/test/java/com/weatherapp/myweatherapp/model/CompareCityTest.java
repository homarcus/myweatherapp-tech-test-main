package com.weatherapp.myweatherapp.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CompareCityTest {
    @Test
    public void testCityGetterSetter() {
        CompareCity city = new CompareCity();
        city.setCity("London");
        assertEquals("London", city.getCity());
    }
    
    @Test
    public void testDescriptionGetterSetter() {
        CompareCity city = new CompareCity();
        city.setDescription("Longer daylight hours");
        assertEquals("Longer daylight hours", city.getDescription());
    }
    
    @Test
    public void testSunriseGetterSetter() {
        CompareCity city = new CompareCity();
        city.setSunrise("06:00");
        assertEquals("06:00", city.getSunrise());
    }
    
    @Test
    public void testSunsetGetterSetter() {
        CompareCity city = new CompareCity();
        city.setSunset("20:00");
        assertEquals("20:00", city.getSunset());
    }
}

