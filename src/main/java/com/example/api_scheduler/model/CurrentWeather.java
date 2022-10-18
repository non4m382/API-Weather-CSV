package com.example.api_scheduler.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrentWeather implements Serializable {

    private String description;
    private double temperature;
    private double feelsLike;
    private double windSpeed;

    @Override
    public String toString() {
        return "CurrentWeather{" +
                "description='" + description + '\'' +
                ", temperature=" + temperature +
                ", feelsLike=" + feelsLike +
                ", windSpeed=" + windSpeed +
                '}';
    }
}
