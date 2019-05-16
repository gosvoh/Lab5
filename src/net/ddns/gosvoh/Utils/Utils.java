/*
 * Copyright (c) 2019.
 * Программа написана gosvoh (Алексей Вохмин)
 * This program has been written by gosvoh (Alexey Vokhmin)
 */

package net.ddns.gosvoh.Utils;

import java.util.Scanner;

public class Utils {
    public static String ConsoleInput() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine())
            return scanner.nextLine();
        throw new RuntimeException("Ошибка ввода! Требуется перезапуск программы!");
    }
}
