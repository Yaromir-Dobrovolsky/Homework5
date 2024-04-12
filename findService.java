package org.example;

import java.util.Scanner;

public class findService {
    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;

    public void find(){
    Scanner scanner = new Scanner(System.in);

    String[] numbers;
    int[] intNumbers;

        do {
        // Запрос на ввод числовой последовательности
            System.out.println("Добровольский Я.Б. РИБО-02-22 \n");
        System.out.println("Введите числовую последовательность через запятую:");
        String input = scanner.nextLine().trim();

        numbers = input.split(",");
        intNumbers = new int[numbers.length];

        boolean validInput = true;

        // Проверка ввода на корректность (должны быть только числа)
        for (int i = 0; i < numbers.length; i++) {
            try {
                intNumbers[i] = Integer.parseInt(numbers[i]);
            } catch (NumberFormatException e) {
                validInput = false;
                System.out.println("Ошибка! Необходимо вводить только числа. Повторите попытку.");
                break;
            }
        }

        if (validInput) {
            break;
        }
    } while (true);

    // Запуск отдельных потоков для вычисления максимального и минимального значений
    int[] finalIntNumbers1 = intNumbers;
    Thread maxThread = new Thread(() -> {
        for (int num : finalIntNumbers1) {
            if (num > max) {
                max = num;
            }
        }
    });

    int[] finalIntNumbers = intNumbers;
    Thread minThread = new Thread(() -> {
        for (int num : finalIntNumbers) {
            if (num < min) {
                min = num;
            }
        }
    });

        maxThread.start();
        minThread.start();

        try {
        // Ожидание завершения работы потоков
        maxThread.join();
        minThread.join();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    // Вывод результатов - максимального и минимального значений
        System.out.println("Максимальное значение: " + max);
        System.out.println("Минимальное значение: " + min);
}
}
