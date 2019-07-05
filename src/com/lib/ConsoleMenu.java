package com.lib;
import java.util.Scanner;
import java.util.Map;

public class ConsoleMenu implements Menu {
    private Dictionary[] dictionaries;
    private boolean interrupt = false;

    public ConsoleMenu(Dictionary... dictionaries) {
        this.dictionaries = dictionaries;
    }

    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                int index = -1;
                boolean loopFlag = true;
                while (loopFlag) {
                    clearConsole();
                    System.out.println("Выберите контроллер:");
                    printDictionariesList();
                    System.out.println("Выход");
                    if (scanner.hasNextInt()) {
                        index = scanner.nextInt();
                        if (index < 0) System.out.println("Число словарей не может быть меньше 0");
                        else if (index > 0) System.out.println("Число не может быть больше колличества словарей");
                        else loopFlag = false;
                    } else {
                        interrupt = scanner.nextLine().equals("Выход");
                        loopFlag = false;
                    }
                    System.out.println("Нажмите enter");
                    scanner.nextLine();
                }
                if (interrupt) break;
                Dictionary currentFileProperties = dictionaries[index];
                String command = "";
                while (!command.equals("Выход")) {
                    clearConsole();
                    System.out.println("Выберете команду:");
                    System.out.println("1-Вывод всех пар");
                    System.out.println("2-Удаление по ключу");
                    System.out.println("3-Получение по ключу");
                    System.out.println("4-Добавление");
                    System.out.println("Выход");
                    command = scanner.nextLine();
                    switch (command) {
                        case "1":
                            printDictionary(currentFileProperties);
                            break;
                        case "2": {
                            System.out.println("Введите key:");
                            String key = scanner.nextLine();
                            removeValue(currentFileProperties, key);
                            break;
                        }
                        case "3": {
                            System.out.println("Введите key:");
                            String key = scanner.nextLine();
                            getValue(currentFileProperties, key);
                            break;
                        }
                        case "4": {
                            System.out.println("Введите key:");
                            String key = scanner.nextLine();
                            System.out.println("Введите value:");
                            String value = scanner.nextLine();
                            putValue(currentFileProperties, key, value);
                            break;
                        }
                        case "exit":
                            break;
                        default:
                            System.out.println("Неверная команда!");
                    }
                    System.out.println("Нажмите Enter");
                    scanner.nextLine();
                }
            }
        }
    }

    private void printDictionary(Dictionary dictionary) {
        for (Map.Entry<String, String> entry : dictionary.getDictionary().entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    private void removeValue(Dictionary dictionary, String key) {
        System.out.println(dictionary.remove(key));
    }

    private void getValue(Dictionary dictionary, String key) {
        System.out.println(dictionary.get(key));
    }

    private void putValue(Dictionary dictionary, String key, String value) {
        System.out.println(dictionary.put(key, value));
    }

    private void printDictionariesList() {
        for (int i = 0; i < dictionaries.length; i++) {
            System.out.println(i + "-" + dictionaries[i].getName());
        }
    }

    private void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}