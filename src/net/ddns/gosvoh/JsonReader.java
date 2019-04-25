package net.ddns.gosvoh;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Класс для чтения данных в формате json
 *
 * @author Vokhmin Aleksey
 * @version 1.0
 */
public class JsonReader implements Closeable {
    private FileReader fileReader;
    private Scanner scanner;

    public JsonReader() {
    }

    public JsonReader(String fileName) throws IOException {
        fileReader = new FileReader(fileName);
        scanner = new Scanner(fileReader);
    }

    public JsonReader(File file) throws IOException {
        fileReader = new FileReader(file);
        scanner = new Scanner(fileReader);
    }

    private String readAsJson() {
        StringBuilder json = new StringBuilder();
        while (scanner.hasNextLine())
            json.append(scanner.nextLine());
        return json.toString();
    }

    public Hero readAsHero(String json) {
        return new Gson().fromJson(json, Hero.class);
    }

    public Planet readAsPlanet(String json) {
        return new Gson().fromJson(json, Planet.class);
    }

    public HashMap<Integer, Hero> readAsHashMapIntegerHero() {
        Type type = new TypeToken<HashMap<Integer, Hero>>() {
        }.getType();
        return new Gson().fromJson(readAsJson(), type);
    }

    public HashMap<Integer, Planet> readAsHashMapIntegerPlanet() {
        Type type = new TypeToken<HashMap<Integer, Planet>>() {
        }.getType();
        return new Gson().fromJson(readAsJson(), type);
    }

    @Override
    public void close() throws IOException {
        fileReader.close();
    }
}
