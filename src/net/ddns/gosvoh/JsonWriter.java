package net.ddns.gosvoh;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.*;

public class JsonWriter implements Closeable {

    private String fileName;
    private File file;
    private FileWriter fileWriter;

    public JsonWriter(String fileName) throws IOException {
        this.fileName = fileName;
        file = new File(fileName);
        fileWriter = new FileWriter(fileName);
    }

    public JsonWriter(File file) throws IOException {
        this.file = file;
        fileWriter = new FileWriter(file);
    }

    public void writeAsJson(Object obj) throws IOException {
        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
        JsonObject test = new JsonObject();
        fileWriter.write(gson.toJson(obj));
    }

    public boolean isExists() {
        return file != null || fileName != null;
    }
    @Override
    public void close() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
