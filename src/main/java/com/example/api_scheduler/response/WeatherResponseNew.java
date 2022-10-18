package com.example.api_scheduler.response;

import com.example.api_scheduler.model.CurrentWeather;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponseNew extends CurrentWeather {
    private String time;

//    public WeatherResponseNew(String time, String description, double temperature, double feelsLike, double windSpeed) {
//        super(description, temperature, feelsLike, windSpeed);
//        this.time = time;
//    }

    @Override
    public String toString() {
        return "WeatherResponseNew{" +
                "time='" + time + '\'' +
                "} " + super.toString();
    }
}
