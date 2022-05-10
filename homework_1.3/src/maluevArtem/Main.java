package maluevArtem;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 * При массиве маленького размера многотпоточное решение задачи происходит быстрее или за то же время, что и однопоточное решение.
 * При большом размере массива время решения задачи многопоточным способом сильно увеличивается, по сравнению с однопоточным.
 */

public class Main {

    final static int SIZE_SMALL_ARRAY = 100;
    final static int SIZE_LARGE_ARRAY = 1_000_000;

    public static void main(String[] args) {

        int[] smallArray = fillArray(SIZE_SMALL_ARRAY);
        int[] largeArray = fillArray(SIZE_LARGE_ARRAY);

        Instant startTime1 = Instant.now();
        singleThreadedSolution(smallArray);
        Instant finishTime1 = Instant.now();
        long executionTime1 = Duration.between(startTime1, finishTime1).toMillis();
        System.out.println("Время выполнения задачи: " + executionTime1 + " мс");

        Instant startTime2 = Instant.now();
        multiThreadedSolution(smallArray);
        Instant finishTime2 = Instant.now();
        long executionTime2 = Duration.between(startTime2, finishTime2).toMillis();
        System.out.println("Время выполнения задачи: " + executionTime2 + " мс");


        Instant startTime3 = Instant.now();
        singleThreadedSolution(largeArray);
        Instant finishTime3 = Instant.now();
        long executionTime3 = Duration.between(startTime3, finishTime3).toMillis();
        System.out.println("Время выполнения задачи: " + executionTime3 + " мс");

        Instant startTime4 = Instant.now();
        multiThreadedSolution(largeArray);
        Instant finishTime4 = Instant.now();
        long executionTime4 = Duration.between(startTime4, finishTime4).toMillis();
        System.out.println("Время выполнения задачи: " + executionTime4 + " мс");
    }

    public static void singleThreadedSolution(int[] array) {
        System.out.println("\nОднопоточное решение");
        int sumElements = 0;
        for (int i : array) {
            sumElements += i;
        }
        int arithmeticMean = sumElements / array.length;
        System.out.println("Сумма элементов массива: " + sumElements);
        System.out.println("Среднее арефметическое элементов массива: " + arithmeticMean);
    }

    public static void multiThreadedSolution(int[] array) {
        System.out.println("\nМногопоточное решение");
        ArraySumTask arraySumTask = new ArraySumTask(array);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int sumElements = forkJoinPool.invoke(arraySumTask);
        int arithmeticMean = sumElements / array.length;
        System.out.println("Сумма элементов массива: " + sumElements);
        System.out.println("Среднее арефметическое элементов массива: " + arithmeticMean);
    }

    public static int[] fillArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }
        return array;
    }

    public static void outputArray(int[] array) {
        System.out.print("{");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i != array.length - 1) {
                System.out.print(",");
            }
        }
        System.out.print("}\n");
    }
}
