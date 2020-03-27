package org.example.java8.v13;

import org.example.java8.v5.stream.Dish;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * @Author wangp
 * @Date 2020/3/27
 * @Version 1.0
 */
public class CollectorsInAction3 {

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
        testPartitioningBy();
        testPartitioningByWithPredicateAndCollector();
        testReducingBinaryOperator();
        testReducingBinaryOperatorAndIdentity();
        testReducingBinaryOperatorAndIdentityAndFunction();
        testSummarizingDouble();
        testSummarizingInt();
        testSummarizingLong();
    }

    private static void testPartitioningBy(){
        System.out.println("----testPartitioningBy-----");
        Map<Boolean, List<Dish>> map = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian));
        Optional.ofNullable(map).ifPresent(System.out::println);
    }

    private static void testPartitioningByWithPredicateAndCollector(){
        System.out.println("----\ntestPartitioningByWithPredicateAndCollector-----");
        Map<Boolean, Double> map = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.averagingInt(Dish::getCalories)));
        Optional.ofNullable(map).ifPresent(System.out::println);
    }

    private static void testReducingBinaryOperator(){
        System.out.println("----\ntestReducingBinaryOperator-----");
        Optional<Dish> dish = menu.stream()
                .collect(Collectors.reducing(BinaryOperator.maxBy(Comparator.comparingInt(Dish::getCalories))));
        dish.ifPresent(System.out::println);
    }

    private static void testReducingBinaryOperatorAndIdentity(){
        System.out.println("----\ntestReducingBinaryOperatorAndIdentity-----");
        Integer sum = menu.stream()
                .map(Dish::getCalories)
                .collect(Collectors.reducing(0, (d1, d2) -> d1 + d2));
        System.out.println(sum);
    }

    private static void testReducingBinaryOperatorAndIdentityAndFunction(){
        System.out.println("----\ntestReducingBinaryOperatorAndIdentityAndFunction-----");
        Integer sum = menu.stream()
                .collect(Collectors.reducing(0,Dish::getCalories, (d1, d2) -> d1 + d2));
        System.out.println(sum);
    }

    private static void testSummarizingDouble(){
        System.out.println("\ntestSummarizingDouble");
        Optional.ofNullable(menu.stream().collect(
                Collectors.summarizingDouble(Dish::getCalories)
        )).ifPresent(System.out::println);
    }

    private static void testSummarizingInt(){
        System.out.println("\ntestSummarizingInt");
        Optional.ofNullable(menu.stream().collect(
                Collectors.summarizingInt(Dish::getCalories)
        )).ifPresent(System.out::println);
    }

    private static void testSummarizingLong(){
        System.out.println("\ntestSummarizingLong");
        Optional.ofNullable(menu.stream().collect(
                Collectors.summarizingLong(Dish::getCalories)
        )).ifPresent(System.out::println);
    }
}
