package com.weather.weathermap.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.weather.weathermap.dao.WeatherInfoRepository;
import com.weather.weathermap.entity.WeatherInfo;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class WeatherService {

    @Autowired
    private WeatherInfoRepository weatherInfoRepository;

    private final String geocodeApiUrl = "https://api.openweathermap.org/geo/1.0/zip";
    private final String weatherApiUrl = "https://api.openweathermap.org/data/2.5/weather";
    private final String apiKey = "API Key";


    public WeatherInfo getWeatherInfo(String pincode, LocalDate date) {
        Optional<WeatherInfo> cachedData = weatherInfoRepository.findByPincodeAndDate(pincode, date);

        if (cachedData.isPresent()) {
            return cachedData.get();
        }

        Double[] latLong = getLatLongFromPincode(pincode);
        Double latitude = latLong[0];
        Double longitude = latLong[1];

        String weatherData = getWeatherFromLatLong(latitude, longitude);

        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.setPincode(pincode);
        weatherInfo.setLatitude(latitude);
        weatherInfo.setLongitude(longitude);
        weatherInfo.setDate(date);
        weatherInfo.setWeatherData(weatherData);

        return weatherInfoRepository.save(weatherInfo);
    }

    private Double[] getLatLongFromPincode(String pincode) {
        String url = geocodeApiUrl + "?zip=" + pincode + ",IN&appid=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();
        GeocodeResponse response = restTemplate.getForObject(url, GeocodeResponse.class);

        return new Double[]{response.getLat(), response.getLon()};
    }

    private String getWeatherFromLatLong(Double latitude, Double longitude) {
        String url = weatherApiUrl + "?lat=" + latitude + "&lon=" + longitude + "&appid=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}
