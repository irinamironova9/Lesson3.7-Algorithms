package course3.lesson7_algorithms.sorting;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class SortComparison {

    public static void main(String[] args) {
        int[] array = generateArray();
        benchmarkSort(SortComparison::insertionSort, Arrays.copyOf(array, array.length));
        benchmarkSort(SortComparison::selectionSort, Arrays.copyOf(array, array.length));
        benchmarkSort(SortComparison::bubbleSort, Arrays.copyOf(array, array.length));
    }

    private static int[] generateArray() {
        return IntStream
                .generate(() -> ThreadLocalRandom.current().nextInt(0, 1_000_000))
                .limit(10_000)
                .toArray();
    }

    private static void benchmarkSort(Consumer<int[]> sortFunction, int[] array) {
        long start = System.currentTimeMillis();
        sortFunction.accept(array);
        long end = System.currentTimeMillis();
        System.out.println("Сортировка = " + isSorted(array));
        System.out.println("Время сортировки = " + (end - start) + " мс");

    }

    private static void insertionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int tmp = array[i];
            int j = i;
            while (j > 0 && array[j - 1] >= tmp) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = tmp;
        }
    }

    private static void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int tmp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = tmp;
        }
    }

    private static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[j] > array[i]) {
                    int tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }

    private static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }
}