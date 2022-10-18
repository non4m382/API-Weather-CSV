package com.example.api_scheduler.controller;

import com.example.api_scheduler.model.CurrentWeather;
import com.example.api_scheduler.response.WeatherResponseNew;
import com.example.api_scheduler.service.LiveWeatherService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("api/weather")
@RequiredArgsConstructor
public class WeatherController {
    private final LiveWeatherService liveWeatherService;

    @Value("${api.file_folder}")
    private String filePath;

    @GetMapping
    public WeatherResponseNew getWeatherObject() {
        WeatherResponseNew response = new WeatherResponseNew();

        CurrentWeather currentWeather = liveWeatherService.getCurrentWeather();

        response.setTime(LocalDateTime.now().toString());
        response.setDescription(currentWeather.getDescription());
        response.setTemperature(currentWeather.getTemperature());
        response.setFeelsLike(currentWeather.getFeelsLike());
        response.setWindSpeed(currentWeather.getWindSpeed());

        return response;
    }

    public byte[] getInputStreamFromResource(String uri) {
        File file = new File(uri);
        try {
            return FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/download")
    public HttpEntity<byte[]> createPdf() {

        byte[] documentBody = getInputStreamFromResource(filePath);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.parseMediaType("application/csv"));
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "Hello.csv");
        header.setContentLength(documentBody.length);

        return new HttpEntity<>(documentBody, header);
    }
}
