// https://javadevblog.com/polnoe-rukovodstvo-po-java-8-stream.html
package ru.javawebinar.basejava;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainStreamApi {
    public static void main(String[] args) {
        int[][] arguments = {
                {1, 2, 3, 3, 2, 3},
                {9, 8},
                {6, 4},
                {9, 3, 4, 4, 5, 5, 6, 6, 8, 9, 4}
        };
        System.out.println("minValue:");
        for (int[] argument : arguments) {
            System.out.print("\t");
            for (int val : argument) {
                System.out.print(" " + val);
            }
            System.out.println(" => " + minValue(argument));
        }
        System.out.println("oddOrEven:");
        for (int[] argument : arguments) {
            System.out.print("\t");
            for (int val : argument) {
                System.out.print(" " + val);
            }
            System.out.println(" => " + oddOrEven(Arrays.stream(argument).boxed().collect(Collectors.toList())).toString());
        }

    }

    public static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (ret, value) -> ret * 10 + value);
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        Supplier<Stream<Integer>> streamSupplier = integers::stream;

        int sum = streamSupplier.get().reduce(0, Integer::sum);

        int valOddOrEven = (sum + 1) % 2;

        return streamSupplier.get().filter(value -> (value % 2) == valOddOrEven).toList();
    }
}
