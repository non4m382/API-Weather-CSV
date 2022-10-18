package com.example.api_scheduler.utils;

import com.example.api_scheduler.response.WeatherResponseNew;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class WriteToFile {

    @Value("${api.file_folder}")
    private String fileFolder;
    private static final String[] HEADER = {"Time", "Description", "Temperature", "Feels Like", "Wind Speed"};

    public String[] convert(WeatherResponseNew weatherResponseNew) {
        List<String> list = new ArrayList<>();
        list.add(weatherResponseNew.getTime());
        list.add(weatherResponseNew.getDescription());
        list.add(String.valueOf(weatherResponseNew.getTemperature()));
        list.add(String.valueOf(weatherResponseNew.getFeelsLike()));
        list.add(String.valueOf(weatherResponseNew.getWindSpeed()));
        return list.toArray(new String[0]);
    }

    public void writeToFile(String[] data, String fileName) {
        List<String[]> csvData = new ArrayList<>();

        String filePath = fileFolder + "/" + fileName + ".csv";
        File f = new File(filePath);
        if (!f.exists() || (f.isFile() && f.length() == 0)) {
            csvData.add(HEADER);
        }
        csvData.add(data);

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath, true))) {
            writer.writeAll(csvData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
