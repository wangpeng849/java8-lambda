package org.example.java8.v13;

import org.example.java8.v5.stream.Dish;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * @Author wangp
 * @Date 2020/3/27
 * @Version 1.0
 */
public class CollectorsInAction4 {

    private static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    public static void main(String[] args) {
        testToCollection();
        testToConcurrentMap();
        testToConcurrentMapWithBinaryOperator();
        testToConcurrentMapWithBinaryOperatorAndSupplier();
        testToList();
        testToSet();
        testToMap();
    }

    private static void testToCollection() {
        System.out.println("----testToCollection----");
        Optional.of(
                menu.stream()
                        .filter(d -> d.getCalories() > 600)
                        .collect(Collectors.toCollection(LinkedList::new))
        ).ifPresent(System.out::println);
    }

    private static void testToConcurrentMap() {
        System.out.println("\n----testToConcurrentMap----");
        Optional.of(
                menu.stream()
                        .collect(Collectors.toConcurrentMap(Dish::getName, Dish::getCalories))
        ).ifPresent((v) -> {
            System.out.println(v);
            System.out.println(v.getClass());
        });
    }


    /**
     * type:total
     */
    private static void testToConcurrentMapWithBinaryOperator() {
        System.out.println("\n----testToConcurrentMapWithBinaryOperator----");
        Optional.of(
                menu.stream()
                        .collect(Collectors.toConcurrentMap(Dish::getType, v -> 1l, (a, b) -> a + b))
        ).ifPresent((v) -> {
            System.out.println(v);
            System.out.println(v.getClass());
        });
    }

    private static void testToConcurrentMapWithBinaryOperatorAndSupplier() {
        System.out.println("----\ntestToConcurrentMapWithBinaryOperatorAndSupplier----");
        Optional.of(
                menu.stream()
                        .collect(Collectors.toConcurrentMap(Dish::getType, v -> 1l, (a, b) -> a + b, ConcurrentSkipListMap::new))
        ).ifPresent((v) -> {
            System.out.println(v);
            System.out.println(v.getClass());
        });
    }

    private static void testToList() {
        System.out.println("----\ntestToList----");
        Optional.of(
                menu.stream()
                        .filter(Dish::isVegetarian)
                        .collect(Collectors.toList())
        ).ifPresent(System.out::println);
    }

    private static void testToSet() {
        System.out.println("----\ntestToSet----");
        Optional.of(
                menu.stream()
                        .filter(Dish::isVegetarian)
                        .collect(Collectors.toSet())
        ).ifPresent(System.out::println);
    }


    private static void testToMap() {
        System.out.println("----\ntestToMap----");
        Optional.of(
                menu.stream()
                        .collect(Collectors.toMap(Dish::getName, Dish::getCalories))
        ).ifPresent((v) -> {
            System.out.println(v);
            System.out.println(v.getClass());
        });
//转线程安全
        Optional.of(menu.stream()
                .collect(Collectors.collectingAndThen(Collectors.toMap(Dish::getName, Dish::getCalories), Collections::synchronizedMap)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
    }
}
