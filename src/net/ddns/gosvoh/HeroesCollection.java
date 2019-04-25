package net.ddns.gosvoh;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Данный класс является классом-оболочкой для коллекций Hero и Stick, реализованном с помощью HashMap.
 *
 * @author Vokhmin Aleksey
 * @version 1.0
 * @see net.ddns.gosvoh.Hero
 * @see net.ddns.gosvoh.Stick
 */
public class HeroesCollection {
    private HashMap<Integer, Hero> heroHashMap = new HashMap<>();

    private File mainFile = null;
    private File tempFile = new File(System.getProperty("user.home") +
            "/Desktop/temp.json");
    private final Date initDate = new Date();
    private int globalIterator;
/*
    public HeroesCollection() {
        initDate.setTime(System.currentTimeMillis());
        Hero[] heroes = new Hero[5];
        for (int i = 0; i < heroes.length; i++) {
            heroHashMap.put(i, new Hero("Герой " + (i + 1)));
        }

        tempSave(heroHashMap);
    }
*/

    public HeroesCollection() {
        try {
            heroHashMap = new JsonReader(tempFile).readAsHashMapIntegerHero();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            System.out.println("Неправильно введены дынные!");
        }
        globalIterator = heroHashMap.size() - 1;

        heroHashMap.forEach((i, h) -> System.out.println(h));
    }

    public void remove_greater_key(String key) {
        int iterator = heroHashMap.size();
        Integer[] a = heroHashMap.keySet().toArray(new Integer[0]);
        for (int i = 0; i < iterator; i++) {
            if (a[i] > Integer.getInteger(key))
                heroHashMap.remove(a[i]);
        }
    }

    public void info() {
        System.out.println("Тип: " + heroHashMap.getClass() +
                ", Дата инициализации: " + initDate.toString() +
                ", Количество элементов: " + heroHashMap.size());
    }

    public void add_if_max(String stringJson) {
        try {
            Hero hero = new JsonReader().readAsHero(stringJson);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            System.out.println("Неправильно введены дынные!");
        }
    }

    public void remove_lower(String key) {
        int iterator = heroHashMap.size();
        Integer[] a = heroHashMap.keySet().toArray(new Integer[0]);
        for (int i = 0; i < iterator; i++) {
            if (a[i] < Integer.getInteger(key))
                heroHashMap.remove(a[i]);
        }
    }

    /**
     * Этот метод выводит в стандартный поток вывода все элементы коллекции в строковом представлении.
     * Команда: show
     */
    public void show() {
        heroHashMap.forEach((h, s) -> System.out.println(h));
    }

    public void insert(String key, String element) {
    }

    public void remove(String key) {
    }

    public void tempSave(Object object) {
        try (JsonWriter file = new JsonWriter(tempFile)) {
            file.writeAsJson(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void help() {
        System.out.println(
                "Доступные команды: " +
                        "\nremove_greater_key {Json} - удалить из коллекции все элементы, ключ которых превышает заданный" +
                        "\ninfo - вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)" +
                        "\nadd_if_max {Json} - добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции" +
                        "\nremove_lower {Json} - удалить из коллекции все элементы, ключ которых меньше, чем заданный" +
                        "\nshow - вывести в стандартный поток вывода все элементы коллекции в строковом представлении" +
                        "\ninsert {Json} - добавить новый элемент с заданным ключом" +
                        "\nremove {Json} - удалить элемент из коллекции по его ключу" +
                        "\nq[uit] - выйти из программы" +
                        "\n{help | ?} - показать доступные команды"
        );
    }
}
