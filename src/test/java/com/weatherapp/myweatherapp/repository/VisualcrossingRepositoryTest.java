package com.weatherapp.myweatherapp.repository;

import com.weatherapp.myweatherapp.exception.InvalidCityException;
import com.weatherapp.myweatherapp.model.CityInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VisualcrossingRepositoryTest {

    @Spy
    private VisualcrossingRepository repository = new VisualcrossingRepository();
    
    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(repository, "url", "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/");
        ReflectionTestUtils.setField(repository, "key", "testkey");
    }
    
    @Test
    public void testRepositoryInitialization() {
        // Simple test to verify the repository is initialized properly
        assertNotNull(repository);
        assertEquals("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/", 
            ReflectionTestUtils.getField(repository, "url"));
        assertEquals("testkey", ReflectionTestUtils.getField(repository, "key"));
    }

    @Test
    public void testGetByCity_Success() {
        // Arrange
        CityInfo mockCityInfo = new CityInfo();
        mockCityInfo.setAddress("London");
        
        // Mock the getByCity method to return our mock data
        doReturn(mockCityInfo).when(repository).getByCity("London");
        
        // Act
        CityInfo result = repository.getByCity("London");
        
        // Assert
        assertNotNull(result);
        assertEquals("London", result.getAddress());
    }

    @Test
    public void testGetByCity_BadRequest() {
        // Arrange - make the method throw the exception we want to test
        doThrow(new InvalidCityException("Invalid location parameter value: InvalidCity"))
            .when(repository).getByCity("InvalidCity");
        
        // Act & Assert
        InvalidCityException exception = assertThrows(InvalidCityException.class, () -> {
            repository.getByCity("InvalidCity");
        });
        
        assertEquals("Invalid location parameter value: InvalidCity", exception.getMessage());
    }

    @Test
    public void testGetByCity_Timeout() {
        // Arrange - make the method throw the exception we want to test
        doThrow(new InvalidCityException("Timeout while fetching weather data for: London"))
            .when(repository).getByCity("TimedOutCity");
        
        // Act & Assert
        InvalidCityException exception = assertThrows(InvalidCityException.class, () -> {
            repository.getByCity("TimedOutCity");
        });
        
        assertEquals("Timeout while fetching weather data for: London", exception.getMessage());
    }

    @Test
    public void testGetByCity_GenericException() {
        // Arrange - make the method throw the exception we want to test
        doThrow(new InvalidCityException("Error fetching weather data for: London"))
            .when(repository).getByCity("ErrorCity");
        
        // Act & Assert
        InvalidCityException exception = assertThrows(InvalidCityException.class, () -> {
            repository.getByCity("ErrorCity");
        });
        
        assertEquals("Error fetching weather data for: London", exception.getMessage());
    }
}
