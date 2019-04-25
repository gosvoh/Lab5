package net.ddns.gosvoh;

import java.util.Scanner;

public class Utils {
    public static String ConsoleInput() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine())
            return scanner.nextLine();
        throw new RuntimeException("Ошибка ввода! Требуется перезапуск программы!");
    }
}
