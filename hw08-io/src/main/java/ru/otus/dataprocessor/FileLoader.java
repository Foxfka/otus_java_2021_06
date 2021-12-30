package ru.otus.dataprocessor;

import com.google.gson.Gson;
import ru.otus.model.Measurement;

import java.util.Arrays;
import java.util.List;

public class FileLoader implements Loader {

    private final String fileName;
    private final Gson gson;

    public FileLoader(String fileName) {
        this.fileName = fileName;
        this.gson = new Gson();
    }

    @Override
    public List<Measurement> load() {
        //читает файл, парсит и возвращает результат

        String fileContent = new FileToStringReader().readFile(fileName);

        Measurement[] measurements = gson.fromJson(fileContent, Measurement[].class);
        return Arrays.asList(measurements);
    }
}