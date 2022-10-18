package com.example.api_scheduler.utils;

import com.example.api_scheduler.controller.WeatherController;
import com.example.api_scheduler.response.WeatherResponseNew;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);
    private static String fileName;
    private final WeatherController weatherController;
    private final WriteToFile writeToFile;
    private static final DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

    @Scheduled(cron = "*/30 * * ? * * ")
    public static void createNewFileName() {
        String currentDateTime = dateFormatter.format(new Date());
        String baseName = "weather";
        fileName = baseName + currentDateTime;
        LOGGER.info("---------------------------" +
                "Create a new fileName: {}" +
                "-----------------------------", fileName);
    }

    @Scheduled(cron = "*/5 * * ? * * ")
    public void writeToFile() {
        String currentDateTime = dateFormatter.format(new Date());
        if (fileName != null) {
            LOGGER.info("{} collected at: {}", fileName, currentDateTime);
            WeatherResponseNew o = weatherController.getWeatherObject();
            String[] s = writeToFile.convert(o);
            writeToFile.writeToFile(s, fileName);
        }
    }
}
