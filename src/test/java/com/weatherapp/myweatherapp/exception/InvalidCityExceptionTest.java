package com.weatherapp.myweatherapp.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InvalidCityExceptionTest {
    @Test
    public void testMessageConstructor() {
        InvalidCityException ex = new InvalidCityException("Invalid city");
        assertEquals("Invalid city", ex.getMessage());
    }
}
