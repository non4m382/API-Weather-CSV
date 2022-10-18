package com.example.api_scheduler.utils;

import com.example.api_scheduler.response.WeatherResponseNew;
import com.opencsv.CSVWriter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class WriteToFile {

    private static final String FILE_PATH = "src/main/resources/static/test.csv";
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

    public void print(String[] s) {
        List<String[]> csvData = new ArrayList<>();

        File f = new File(FILE_PATH);
        if (!f.exists() || (f.isFile() && f.length() == 0)) {
            csvData.add(HEADER);
        }
        csvData.add(s);

        try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_PATH, true))) {
            writer.writeAll(csvData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
