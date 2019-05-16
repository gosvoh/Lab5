/*
 * Copyright (c) 2019.
 * Программа написана gosvoh (Алексей Вохмин)
 * This program has been written by gosvoh (Alexey Vokhmin)
 */

package net.ddns.gosvoh;

import com.google.gson.JsonSyntaxException;
import net.ddns.gosvoh.Utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;

/**
 * Класс-оболочка для коллекции universeHashMap, реализованная с помощью HashMap
 *
 * @author Алексей Вохмин
 * @version 1.0
 * @see Universe
 */
public class UniverseCollection {

    private Date initDate;
    private HashMap<Integer, Universe> universeHashMap = new HashMap<>();
    public static File mainFile = null,
            tempFile = new File(System.getProperty("user.home") + "/Desktop/.temp.json");
    private JsonReader jsonReader;
    private JsonWriter jsonWriter, tempJsonWriter;

    public UniverseCollection(String filePath) {
        mainFile = new File(filePath);
        initDate = new Date(System.currentTimeMillis());
    }

    public UniverseCollection(File file) {
        mainFile = file;
        initDate = new Date(System.currentTimeMillis());
    }

    public void BeginWork() {
        try {
            if (tempFile.exists())
                jsonReader = new JsonReader(tempFile);
            else if (mainFile.exists())
                jsonReader = new JsonReader(mainFile);
            else throw new FileNotFoundException();
            universeHashMap = jsonReader.readAsHashMapIntegerUniverse();
            universeHashMap.size();
            tempJsonWriter = new JsonWriter(tempFile);
        } catch (FileNotFoundException e) {
            System.out.println("Указанный файл не найден! Пожалуйста, укажите путь до файла!");
            quit();
        } catch (JsonSyntaxException e) {
            System.out.println("Неправильный формат даных, пожалуйста исправьте файл с данными!");
            e.getCause().printStackTrace();
            quit();
        } catch (IOException e) {
            System.out.println("У вас недостаточно прав лдя записи файла в данную директорию:" +
                    mainFile.getPath());
            quit();
        } catch (NullPointerException e) {
            System.out.println("Файл пуст! Пожалуйста, заполните файл перед запуском!");
            quit();
        }

        System.out.println("Введите help или ? для просмотра достурных команд.");

        while (true) {
            String[] commands = Utils.ConsoleInput().split(" ");
            String command = commands[0].toLowerCase();

            switch (command) {
                case "save":
                    save(universeHashMap, mainFile);
                    System.out.println("Коллекция успешно сохранена!");
                    break;
                case "remove_greater_key":
                    if (commands.length > 1)
                        remove_greater_key(commands[1]);
                    else System.out.println("Неправильный формат команды!");
                    break;
                case "info":
                    info();
                    break;
                case "add_if_max":
                    if (commands.length > 1) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 1; i < commands.length; i++) {
                            stringBuilder.append(commands[i]);
                        }
                        add_if_max(stringBuilder.toString());
                    } else System.out.println("Неправильный формат команды!");
                    break;
                case "remove_lower":
                    if (commands.length > 1)
                        remove_lower(commands[1]);
                    else System.out.println("Неправильный формат команды!");
                    break;
                case "show":
                    show();
                    break;
                case "insert":
                    if (commands.length > 1) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 2; i < commands.length; i++) {
                            stringBuilder.append(commands[i]);
                        }
                        try {
                            insert(commands[1], stringBuilder.toString());
                        } catch (JsonSyntaxException e) {
                            System.out.println("Неправильный формат данных!");
                        }
                    } else System.out.println("Неправильный формат команды!");
                    break;
                case "remove":
                    if (commands.length > 1)
                        remove(commands[1]);
                    else System.out.println("Неправильный формат команды!");
                    break;
                case "q":
                case "quit":
                    System.out.println("Сохранить файл перед выходом? Yes/No");
                    if (Utils.ConsoleInput().toLowerCase().matches("y | yes")) {
                        save(universeHashMap, mainFile);
                        //deleteFile(tempFile);
                        jsonWriter.close();
                    }
                    jsonReader.close();
                    tempJsonWriter.close();
                    deleteFile(tempFile);
                    quit();
                case "?":
                case "help":
                    help();
                    break;
                default:
                    System.out.println("Неверная команда! Введите help или ? для просмотра достурных команд.");
            }
        }
    }

    /**
     * Метод, отвечающий за удаление всех элементов, ключ которых больше заданного числа
     * Пример команды: remove_greater_key 10
     *
     * @param key - максимальное значение ключа для коллекции
     */
    private void remove_greater_key(String key) {
        int iterator = universeHashMap.size();
        Integer[] a = universeHashMap.keySet().toArray(new Integer[0]);
        int keyInt = getKeyFromString(key);
        if (keyInt != Integer.MIN_VALUE) {
            for (int i = 0; i < iterator; i++) {
                if (a[i] > keyInt)
                    universeHashMap.remove(a[i]);
            }
            System.out.println("Объекты успешно удалены из коллекции.");
            tempSave(universeHashMap);
        }
    }

    /**
     * Выводит информацию о коллекции
     * Пример команды: info
     */
    private void info() {
        System.out.println("Тип: " + universeHashMap.getClass() +
                ", Дата инициализации: " + initDate.toString() +
                ", Количество элементов: " + universeHashMap.size());
    }

    /**
     * Метод добавляет элемент в коллекцию, если значение (длина байтов имени) этого элемента
     * превышает максимальное значение элемента в коллекции
     * Пример команды: add_if_max {"name": "Мидгард"}
     *
     * @param stringJson - элемент в формате Json
     */
    private void add_if_max(String stringJson) {
        try {
            Universe universe = jsonReader.readAsUniverse(stringJson);
            universeHashMap.forEach((k, v) -> {
                if (universe.toString().getBytes().length > v.toString().getBytes().length) {
                    universeHashMap.put(k + 1, universe);
                    System.out.println("Элемент " + universe.toString() + " был успешно добавлен в коллекцию.");
                }
            });
            tempSave(universeHashMap);
        } catch (JsonSyntaxException e) {
            System.out.println("Неправильный формат дынных! Вводите в формате json.");
        }
    }

    /**
     * Метод, отвечающий за удаление всех элементов, ключ которых меньше заданного числа
     * Пример команды: remove_lower 10
     *
     * @param key - минимальное значение ключа для коллекции
     */
    private void remove_lower(String key) {
        int iterator = universeHashMap.size();
        Integer[] a = universeHashMap.keySet().toArray(new Integer[0]);
        int intKey = getKeyFromString(key);
        if (intKey != Integer.MIN_VALUE) {
            for (int i = 0; i < iterator; i++) {
                if (a[i] < Integer.getInteger(key))
                    universeHashMap.remove(a[i]);
            }
            System.out.println("Объекты успешно удалены из коллекции.");
            tempSave(universeHashMap);
        }
    }

    /**
     * Этот метод выводит в стандартный поток вывода все элементы коллекции в строковом представлении.
     * Пример команды: show
     */
    private void show() {
        universeHashMap.forEach((k, v) -> System.out.println("Ключ: [" + k + "], элемент: " + v));
    }

    /**
     * Метод, отвечающий за добавление в коллекцию элемента с заданным ключом
     * Пример команды: insert 10 {"name": "Мидгард"}
     *
     * @param key     - ключ элемента
     * @param element - элемент коллекции
     * @throws JsonSyntaxException - в случае, если синтаксис введён не в формате Json,
     *                             бросается исключение
     */
    private void insert(String key, String element) throws JsonSyntaxException {
        int intKey = getKeyFromString(key);
        Universe universe = jsonReader.readAsUniverse(element);
        if (intKey != Integer.MIN_VALUE) {
            universeHashMap.put(intKey, universe);
            System.out.println("Объект " + universe.toString() + " успешно добавлен в коллекцию.");
            tempSave(universeHashMap);
        }
    }

    /**
     * Метод удаляет элемент из коллекции по заданному ключу
     * Пример команды: remove 10
     *
     * @param key - ключ элемента коллекции
     */
    private void remove(String key) {
        int intKey = getKeyFromString(key);
        if (intKey != Integer.MIN_VALUE) {
            if (universeHashMap.containsKey(intKey)) {
                universeHashMap.remove(intKey);
                System.out.println("Объект успешно удален из коллекции.");
                tempSave(universeHashMap);
            } else System.out.println("Вселенной с таким номером не существует!" +
                    "\nВведите show для просмотра доступных вселенных");
        }
    }

    /**
     * Метод отвечает за сохранение коллекции в файл формата Json
     * Пример команды: save
     *
     * @param object - объект для сохранения
     * @param file - файл для сохранения
     */
    private void save(Object object, File file) {
        try (JsonWriter jsonWriter = new JsonWriter(file)) {
            jsonWriter.writeAsJson(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для временного сохранения коллекции в файл
     *
     * @param object - объект для сохранения
     */
    private void tempSave(Object object) {
        try {
            tempJsonWriter.writeAsJson(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Выход из программы
     * Пример команды: q
     */
    private void quit() {
        System.out.println("Выход из программы...");
        //closeAllIO();
        System.exit(0);
    }

    /**
     * Список доступных команд
     * Пример команды: ?
     */
    private void help() {
        System.out.println(
                "Доступные команды: " +
                        "\nsave - сохранить коллекцию" +
                        "\nremove_greater_key {Key} - удалить из коллекции все элементы, ключ которых превышает заданный" +
                        "\ninfo - вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)" +
                        "\nadd_if_max {Json} - добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции" +
                        "\nremove_lower {Key} - удалить из коллекции все элементы, ключ которых меньше, чем заданный" +
                        "\nshow - вывести в стандартный поток вывода все элементы коллекции в строковом представлении" +
                        "\ninsert {Key} {Json} - добавить новый элемент с заданным ключом ( insert 10 {\"name\": \"Обливион\"} )" +
                        "\nremove {Key} - удалить элемент из коллекции по его ключу" +
                        "\nq[uit] - выйти из программы" +
                        "\n{help | ?} - показать доступные команды"
        );
    }

    /**
     * Метод удаляет указанный файл
     *
     * @param file - файл для удаления
     */
    private void deleteFile(File file) {
        try {
            Files.delete(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод позволяет получить число из строки
     *
     * @param key - строка с числом
     * @return - возвращает минимальное целочисленное значение, если было введено не число
     */
    private int getKeyFromString(String key) {
        int intKey;
        try {
            intKey = Integer.valueOf(key);
            return intKey;
        } catch (NumberFormatException e) {
            System.out.println("Введите число!");
            return Integer.MIN_VALUE;
        }
    }
}
