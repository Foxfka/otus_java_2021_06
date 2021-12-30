package ru.otus.dataprocessor;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class FileSerializer implements Serializer {
    private final String fileName;
    private final Gson gson;

    public FileSerializer(String fileName) {
        this.fileName = fileName;
        this.gson = new Gson();
    }

    @Override
    public void serialize(Map<String, Double> data) {
        //формирует результирующий json и сохраняет его в файл


        try (Writer writer = new BufferedWriter(new FileWriter(fileName))) {
            gson.toJson(data, writer);
        } catch(IOException e) {
            throw new FileProcessException(e);
        }
    }
}
