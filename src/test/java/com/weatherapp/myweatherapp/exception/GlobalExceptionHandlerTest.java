package com.weatherapp.myweatherapp.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    @Test
    public void testHandleInvalidCityException() {
        // Arrange
        InvalidCityException exception = new InvalidCityException("City not found");

        // Act
        ResponseEntity<Map<String, String>> response = exceptionHandler.handleInvalidCityException(exception);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Invalid City", response.getBody().get("error"));
        assertEquals("City not found", response.getBody().get("message"));
    }

    @Test
    public void testHandleHttpClientErrorException() {
        // Arrange
        HttpClientErrorException exception = new HttpClientErrorException(HttpStatus.BAD_REQUEST);

        // Act
        ResponseEntity<Map<String, String>> response = exceptionHandler.handleHttpClientErrorException(exception);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Invalid City", response.getBody().get("error"));
        assertEquals("Invalid location parameter value", response.getBody().get("message"));
    }

    @Test
    public void testHandleResourceAccessException() {
        // Arrange
        ResourceAccessException exception = new ResourceAccessException("Connection timeout");

        // Act
        ResponseEntity<Map<String, String>> response = exceptionHandler.handleResourceAccessException(exception);

        // Assert
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
        assertEquals("Service Unavailable", response.getBody().get("error"));
        assertEquals("Weather service is temporarily unavailable", response.getBody().get("message"));
    }

    @Test
    public void testHandleGenericException() {
        // Arrange
        Exception exception = new RuntimeException("Unexpected error");

        // Act
        ResponseEntity<Map<String, String>> response = exceptionHandler.handleGenericException(exception);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Internal Server Error", response.getBody().get("error"));
        assertEquals("An unexpected error occurred", response.getBody().get("message"));
    }
}
