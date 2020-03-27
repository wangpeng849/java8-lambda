package org.example.java8.v13;

import org.example.java8.v5.stream.Dish;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author wangp
 * @Date 2020/3/27
 * @Version 1.0
 */
public class CollectorsInAction {

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
        testAveragingDouble();
        testAveragingInt();
        testAveragingLong();
        testCollectingAndThen();
        testCounting();
        testGroupByFunction();
        testGroupByFunctionAndCollector();
        testGroupByFunctionAndSupplierAndCollector();
        testSummarizingInt();
    }

    /**
     * 类似 reduce 功能
     */
    private static void testAveragingDouble() {
        System.out.println("testAveragingDouble");
        Optional.ofNullable(
                menu.stream().collect(Collectors.averagingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testAveragingInt() {
        System.out.println("testAveragingInt");
        Optional.ofNullable(
                menu.stream().collect(Collectors.averagingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testAveragingLong() {
        System.out.println("testAveragingLong");
        Optional.ofNullable(
                menu.stream().collect(Collectors.averagingLong(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    /**
     * and then
     */
    private static void testCollectingAndThen() {
        System.out.println("\ntestCollectingAndThen");
        Optional.ofNullable(menu.stream()
                .collect(
                        Collectors.collectingAndThen(Collectors.averagingInt(Dish::getCalories)
                                , a -> "The avg calories is " + a)
                )
        ).ifPresent(System.out::println);


//        List<Dish> list = menu.stream()
//                .filter(dish -> dish.getType().equals(Dish.Type.MEAT))
//                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));//拿到过滤的list 不允许修改
//        list.add(new Dish("other",false,100, Dish.Type.OTHER));
    }

    private static void testCounting() {
        System.out.println("\ntestCounting");
        Optional.ofNullable(menu.stream().collect(Collectors.counting()))
                .ifPresent(System.out::println);
    }

    /**
     * groupBy
     */
    private static void testGroupByFunction() {
        System.out.println("\ntestGroupByFunction");
        Optional.ofNullable(menu.stream()
                .collect(Collectors.groupingBy(Dish::getType))
        ).ifPresent(System.out::println);
    }

    private static void testGroupByFunctionAndCollector() {
        System.out.println("\ntestGroupByFunctionAndCollector");
        Optional.ofNullable(
                menu.stream().collect(
                        Collectors.groupingBy(Dish::getType, Collectors.counting()))).ifPresent(System.out::println);
        Optional.ofNullable(
                menu.stream().collect(
                        Collectors.groupingBy(Dish::getType, Collectors.averagingInt(Dish::getCalories)))).ifPresent(System.out::println);
    }

    private static void testGroupByFunctionAndSupplierAndCollector() {
        System.out.println("\ntestGroupByFunctionAndSupplierAndCollector");
        Map<Dish.Type, Double> map =
                menu.stream().collect(Collectors.groupingBy(Dish::getType,TreeMap::new, Collectors.averagingInt(Dish::getCalories)));
        System.out.println(map.getClass());
        Optional.ofNullable(map).ifPresent(System.out::println);
    }

    private static void testSummarizingInt(){
        System.out.println("\ntestSummarizingInt");
        IntSummaryStatistics intSummaryStatistics = menu.stream()
                .collect(Collectors.summarizingInt(Dish::getCalories));

        Optional.ofNullable(intSummaryStatistics).ifPresent(System.out::println);
    }
}
