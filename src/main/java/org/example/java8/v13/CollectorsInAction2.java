package org.example.java8.v13;

import org.example.java8.v5.stream.Dish;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

/**
 * @Author wangp
 * @Date 2020/3/27
 * @Version 1.0
 */
public class CollectorsInAction2 {

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
        testGroupingByConcurrentWithFunction();
        testGroupingByConcurrentWithFunctionAndCollector();
        testGroupingByConcurrentWithFunctionAndSupplierAndCollector();
        testJoining();
        testJoiningWithDelimiter();
        testJoiningWithDelimiterAndPrefixAndSuffix();
        testMapping();
        testMaxAndMinBy();
    }

    private static void testGroupingByConcurrentWithFunction() {
        System.out.println("----testGroupingByConcurrent-----");
        ConcurrentMap<Dish.Type, List<Dish>> map = menu.stream().collect(
                Collectors.groupingByConcurrent(Dish::getType)
        );

        Optional.ofNullable(map.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(map).ifPresent(System.out::println);
    }

    private static void testGroupingByConcurrentWithFunctionAndCollector() {
        System.out.println("\n----testGroupingByConcurrentWithFunctionAndCollector-----");
        ConcurrentMap<Dish.Type, Double> map = menu.stream().collect(
                Collectors.groupingByConcurrent(
                        Dish::getType, Collectors.averagingInt(Dish::getCalories)
                )
        );
        Optional.ofNullable(map).ifPresent(System.out::println);
    }

    //redis 主要数据结构就是跳表 ConcurrentSkipListMap
    private static void testGroupingByConcurrentWithFunctionAndSupplierAndCollector() {
        System.out.println("\n----testGroupingByConcurrentWithFunctionAndSupplierAndCollector-----");
        ConcurrentMap<Dish.Type, Double> map = menu.stream().collect(
                Collectors.groupingByConcurrent(
                        Dish::getType, ConcurrentSkipListMap::new, Collectors.averagingInt(Dish::getCalories)
                )
        );
        Optional.ofNullable(map.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(map).ifPresent(System.out::println);
    }

    private static void testJoining() {
        System.out.println("\n----testJoining-----");
        String str = menu.stream()
                .map(Dish::getName)
                .collect(
                        Collectors.joining()
                );
        Optional.ofNullable(str).ifPresent(System.out::println);
    }

    private static void testJoiningWithDelimiter() {
        System.out.println("\n----testJoiningWithDelimiter-----");
        String str = menu.stream()
                .map(Dish::getName)
                .collect(
                        Collectors.joining(",")
                );
        Optional.ofNullable(str).ifPresent(System.out::println);
    }

    private static void testJoiningWithDelimiterAndPrefixAndSuffix() {
        System.out.println("\n----testJoiningWithDelimiterAndPrefixAndSuffix-----");
        String str = menu.stream()
                .map(Dish::getName)
                .collect(
                        Collectors.joining(",", "Names [", "]")
                );
        Optional.ofNullable(str).ifPresent(System.out::println);
    }

    private static void testMapping() {
        System.out.println("\n----testMapping-----");
        String str = menu.stream()
                .collect(
                        Collectors.mapping(Dish::getName, Collectors.joining("-", "Mapping [", "]"))
                );
        Optional.ofNullable(str).ifPresent(System.out::println);
    }

    private static void testMaxAndMinBy() {
        System.out.println("\n----testMaxBy-----");
        menu.stream()
                .collect(
                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))
                ).ifPresent(System.out::println);
        menu.stream().min(Comparator.comparingInt(Dish::getCalories)).ifPresent(System.out::println);
    }
}
