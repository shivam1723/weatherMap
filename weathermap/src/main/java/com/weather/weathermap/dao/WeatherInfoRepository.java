package com.weather.weathermap.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weather.weathermap.entity.WeatherInfo;

import java.time.LocalDate;
import java.util.Optional;

public interface WeatherInfoRepository extends JpaRepository<WeatherInfo, Long> {
    Optional<WeatherInfo> findByPincodeAndDate(String pincode, LocalDate date);
}

