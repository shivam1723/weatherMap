package com.weather.weathermap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.weather.weathermap.entity.WeatherInfo;
import com.weather.weathermap.services.WeatherService;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public WeatherInfo getWeatherInfo(
            @RequestParam String pincode,
            @RequestParam("for_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate forDate) {
        return weatherService.getWeatherInfo(pincode, forDate);
    }
}

