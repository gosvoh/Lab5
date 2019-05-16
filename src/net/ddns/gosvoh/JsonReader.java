package net.ddns.gosvoh;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.*;
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

    //public JsonReader() {    }

    public JsonReader(String fileName) throws FileNotFoundException {
        fileReader = new FileReader(fileName);
        scanner = new Scanner(fileReader);
    }

    public JsonReader(File file) throws FileNotFoundException {
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

    public Universe readAsUniverse(String json) {
        return new Gson().fromJson(json, Universe.class);
    }

    public HashMap<Integer, Hero> readAsHashMapIntegerHero() {
        Type type = new TypeToken<HashMap<Integer, Hero>>() {
        }.getType();
        return new Gson().fromJson(readAsJson(), type);
    }

    public HashMap<Integer, Universe> readAsHashMapIntegerUniverse() {
        Type type = new TypeToken<HashMap<Integer, Universe>>() {
        }.getType();
        return new Gson().fromJson(readAsJson(), type);
    }

    @Override
    public void close() {
        try {
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
