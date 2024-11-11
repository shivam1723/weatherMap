package com.weather.weathermap;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.weather.weathermap.dao.WeatherInfoRepository;
import com.weather.weathermap.entity.WeatherInfo;
import com.weather.weathermap.services.WeatherService;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WeathermapApplicationTests {

    @Mock
    private WeatherInfoRepository weatherInfoRepository;

    @InjectMocks
    private WeatherService weatherService;

    public WeathermapApplicationTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetWeatherInfo_CachedData() {
        WeatherInfo mockWeather = new WeatherInfo();
        mockWeather.setPincode("411014");
        mockWeather.setDate(LocalDate.of(2020, 10, 15));
        mockWeather.setWeatherData("Clear");

        when(weatherInfoRepository.findByPincodeAndDate("411014", LocalDate.of(2020, 10, 15)))
                .thenReturn(Optional.of(mockWeather));

        WeatherInfo result = weatherService.getWeatherInfo("411014", LocalDate.of(2020, 10, 15));

        assertEquals("Clear", result.getWeatherData());
    }
}
