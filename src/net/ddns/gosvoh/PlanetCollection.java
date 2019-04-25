package net.ddns.gosvoh;

import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

public class PlanetCollection {

    private Date initDate;
    private HashMap<Integer, Planet> planetHashMap = new HashMap<>();
    public static File mainFile = null,
            tempFile = new File(System.getProperty("user.home") + "/Desktop/.temp.json");

    public PlanetCollection(String filePath) {
        mainFile = new File(filePath);
        initDate = new Date(System.currentTimeMillis());
    }

    public PlanetCollection(File file) {
        mainFile = file;
        initDate = new Date(System.currentTimeMillis());
    }

    public void BeginWork() {
        while (true) {
            try {
                for (int i = 0; i < 10; i++) {
                    planetHashMap.put(i, new Planet("Планета " + i));
                }
                tempSave(planetHashMap);
                planetHashMap = new JsonReader(tempFile).readAsHashMapIntegerPlanet();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String[] command = Utils.ConsoleInput().toLowerCase().split(" ");

            switch (command[0]) {
                case "remove_greater_key":
                    if (checkInputSyntax(command))
                        remove_greater_key(command[1]);
                case "info":
                    info();
                case "add_if_max":
                    if (checkInputSyntax(command))
                        add_if_max(command[1]);
                case "remove_lower":
                    if (checkInputSyntax(command))
                        remove_lower(command[1]);
                case "show":
                    show();
                case "insert":
                    if (checkInputSyntax(command))
                        insert(command[1], command[2]);
                case "remove":
                    if (checkInputSyntax(command))
                        remove(command[1]);
                case "q":
                    System.exit(0);
                case "quit":
                    System.exit(0);
                default:
                    System.out.println("Неверная команда! Введите help или ? для просмотра достурных команд.");
            }
        }
    }

    public void remove_greater_key(String key) {
        int iterator = planetHashMap.size();
        Integer[] a = planetHashMap.keySet().toArray(new Integer[0]);
        for (int i = 0; i < iterator; i++) {
            if (a[i] > Integer.getInteger(key))
                planetHashMap.remove(a[i]);
        }
        tempSave(planetHashMap);
    }

    public void info() {
        System.out.println("Тип: " + planetHashMap.getClass() +
                ", Дата инициализации: " + initDate.toString() +
                ", Количество элементов: " + planetHashMap.size());
    }

    public void add_if_max(String stringJson) {
        try {
            Planet planet = new JsonReader().readAsPlanet(stringJson);
            planetHashMap.forEach((k, v) -> {
                if (planet.toString().getBytes().length > v.toString().getBytes().length)
                    planetHashMap.put(k + 1, planet);
            });
            tempSave(planetHashMap);
        } catch (JsonSyntaxException e) {
            System.out.println("Неправильный формат дынных! Вводите в формате json.");
        }
    }

    public void remove_lower(String key) {
        int iterator = planetHashMap.size();
        Integer[] a = planetHashMap.keySet().toArray(new Integer[0]);
        for (int i = 0; i < iterator; i++) {
            if (a[i] < Integer.getInteger(key))
                planetHashMap.remove(a[i]);
        }
        tempSave(planetHashMap);
    }

    /**
     * Этот метод выводит в стандартный поток вывода все элементы коллекции в строковом представлении.
     * Команда: show
     */
    public void show() {
        planetHashMap.forEach((h, s) -> System.out.println(h));
    }

    public void insert(String key, String element) throws JsonSyntaxException {
        planetHashMap.put(Integer.getInteger(key), new JsonReader().readAsPlanet(element));
        tempSave(planetHashMap);
    }

    public void remove(String key) {
        int i = Integer.getInteger(key);
        if (planetHashMap.containsKey(i)) {
            planetHashMap.remove(i);
            tempSave(planetHashMap);
        } else System.out.println("Вселенной с таким номером не существует!" +
                "\nВведите show для просмотра доступных вселенных");
    }

    public void save(Object object, File file) {
        try (JsonWriter toFile = new JsonWriter(file)) {
            toFile.writeAsJson(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tempSave(Object object) {
        save(object, tempFile);
    }

    public void quit() {
        System.exit(0);
    }

    public void help() {
        System.out.println(
                "Доступные команды: " +
                        "\nsave - сохранить коллекцию" +
                        "\nremove_greater_key {Key} - удалить из коллекции все элементы, ключ которых превышает заданный" +
                        "\ninfo - вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)" +
                        "\nadd_if_max {Json} - добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции" +
                        "\nremove_lower {Key} - удалить из коллекции все элементы, ключ которых меньше, чем заданный" +
                        "\nshow - вывести в стандартный поток вывода все элементы коллекции в строковом представлении" +
                        "\ninsert {Key} {Json} - добавить новый элемент с заданным ключом" +
                        "\nremove {Key} - удалить элемент из коллекции по его ключу" +
                        "\nq[uit] - выйти из программы" +
                        "\n{help | ?} - показать доступные команды"
        );
    }

    private boolean checkInputSyntax(String[] command) {
        if (command.length > 1) {
            if (command[1].isEmpty()) {
                System.out.println("Неверная команда! Введите help или ? для просмотра достурных команд.");
                return false;
            } else return true;
        }
        return false;
    }
}
