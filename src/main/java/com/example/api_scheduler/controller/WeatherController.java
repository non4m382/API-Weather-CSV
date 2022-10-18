package com.example.api_scheduler.controller;

import com.example.api_scheduler.model.CurrentWeather;
import com.example.api_scheduler.response.WeatherResponseNew;
import com.example.api_scheduler.service.LiveWeatherService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final Logger logger = LoggerFactory.getLogger(WeatherController.class);

    private final LiveWeatherService liveWeatherService;

    @GetMapping
    public WeatherResponseNew getWeatherObject() {
        WeatherResponseNew response = new WeatherResponseNew();

        CurrentWeather currentWeather = liveWeatherService.getCurrentWeather();

        response.setTime(LocalDateTime.now().toString());
        response.setDescription(currentWeather.getDescription());
        response.setTemperature(currentWeather.getTemperature());
        response.setFeelsLike(currentWeather.getFeelsLike());
        response.setWindSpeed(currentWeather.getWindSpeed());

        logger.info("weather: {}", response);
        return response;
    }
}
