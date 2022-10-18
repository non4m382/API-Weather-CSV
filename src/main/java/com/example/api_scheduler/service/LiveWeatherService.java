package com.example.api_scheduler.service;

import com.example.api_scheduler.model.CurrentWeather;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class LiveWeatherService {

    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q={city},{country}&APPID={key}&units=metric";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    @Value("${api.openweathermap.key}")
    private String apiKey;

    @Value("${api.openweathermap.city}")
    private String city;

    @Value("${api.openweathermap.country}")
    private String country;

    public CurrentWeather getCurrentWeather() {
        URI url = new UriTemplate(WEATHER_URL).expand(city, country, apiKey);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return convert(response);
    }

    private CurrentWeather convert(ResponseEntity<String> response) {
        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            return new CurrentWeather(root.path("weather").get(0).path("main").asText(),
                    root.path("main").path("temp").asDouble(),
                    root.path("main").path("feels_like").asDouble(),
                    root.path("wind").path("speed").asDouble());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
    }
}
