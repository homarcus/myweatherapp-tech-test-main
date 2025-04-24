package com.weatherapp.myweatherapp.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CityInfoTest {
    @Test
    public void testAddressGetterSetter() {
        CityInfo info = new CityInfo();
        info.setAddress("London, UK");
        assertEquals("London, UK", info.getAddress());
    }
    
    @Test
    public void testDescriptionGetterSetter() {
        CityInfo info = new CityInfo();
        info.setDescription("Cloudy with a chance of rain");
        assertEquals("Cloudy with a chance of rain", info.getDescription());
    }
    
    @Test
    public void testCurrentConditionsGetterSetter() {
        CityInfo info = new CityInfo();
        CityInfo.CurrentConditions conditions = new CityInfo.CurrentConditions();
        info.setCurrentConditions(conditions);
        assertEquals(conditions, info.getCurrentConditions());
    }
    
    @Test
    public void testDaysGetterSetter() {
        CityInfo info = new CityInfo();
        List<CityInfo.Days> daysList = new ArrayList<>();
        daysList.add(new CityInfo.Days());
        info.setDays(daysList);
        assertEquals(daysList, info.getDays());
    }
    
    @Test
    public void testCurrentConditionsClass() {
        CityInfo.CurrentConditions conditions = new CityInfo.CurrentConditions();
        conditions.setSunrise("06:00");
        conditions.setSunset("20:00");
        conditions.setConditions("Sunny");
        conditions.setCurrentTemperature("25");
        conditions.setFeelslike("27");
        conditions.setHumidity("65");
        
        assertEquals("06:00", conditions.getSunrise());
        assertEquals("20:00", conditions.getSunset());
        assertEquals("Sunny", conditions.getConditions());
        assertEquals("25", conditions.getCurrentTemperature());
        assertEquals("27", conditions.getFeelslike());
        assertEquals("65", conditions.getHumidity());
    }
    
    @Test
    public void testCurrentConditionsConstructors() {
        // Test the constructor with sunrise and sunset
        CityInfo.CurrentConditions conditions1 = new CityInfo.CurrentConditions("06:00", "20:00");
        assertEquals("06:00", conditions1.getSunrise());
        assertEquals("20:00", conditions1.getSunset());
        
        // Test the constructor with conditions
        CityInfo.CurrentConditions conditions2 = new CityInfo.CurrentConditions("Sunny");
        assertEquals("Sunny", conditions2.getConditions());
        
        // Test the default constructor
        CityInfo.CurrentConditions conditions3 = new CityInfo.CurrentConditions();
        assertNotNull(conditions3);
    }
    
    @Test
    public void testDaysClass() {
        CityInfo.Days day = new CityInfo.Days();
        day.setDate("2025-04-22");
        day.setConditions("Partly cloudy");
        day.setCurrentTemperature("22");
        day.setMaxTemperature("25");
        day.setMinTemperature("18");
        day.setDescription("Partly cloudy with occasional sunshine");
        
        assertEquals("2025-04-22", day.getDate());
        assertEquals("Partly cloudy", day.getConditions());
        assertEquals("22", day.getCurrentTemperature());
        assertEquals("25", day.getMaxTemperature());
        assertEquals("18", day.getMinTemperature());
        assertEquals("Partly cloudy with occasional sunshine", day.getDescription());
    }
}

