package com.example.api_scheduler.utils;

import com.example.api_scheduler.controller.WeatherController;
import com.example.api_scheduler.response.WeatherResponseNew;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);

    private final WeatherController weatherController;
    private final WriteToFile writeToFile;

    @Scheduled(cron = "0/5 * * * * *")
    public void scheduleTaskWithCronExpression() {
        LOGGER.info("Weather collected at: {}", LocalDateTime.now());
        WeatherResponseNew o = weatherController.getWeatherObject();
        String[] s = writeToFile.convert(o);
        writeToFile.print(s);
    }
}
