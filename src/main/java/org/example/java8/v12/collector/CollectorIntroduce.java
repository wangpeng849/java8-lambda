package org.example.java8.v12.collector;

import org.example.java8.v1.Apple;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author wangp
 * @Date 2020/3/27
 * @Version 1.0
 */
public class CollectorIntroduce {
    public static void main(String[] args) {

        List<Apple> apples = Arrays.asList(
                new Apple("green", 123),
                new Apple("red", 143),
                new Apple("red", 160),
                new Apple("yellow", 10),
                new Apple("yellow", 50)
        );

        List<Apple> reds = apples.stream()
                .filter(apple -> apple.getColor().equals("red"))
                .collect(Collectors.toList());
        Optional.ofNullable(reds).ifPresent(System.out::println);



        Optional.ofNullable(groupByNormal(apples)).ifPresent(System.out::println);


        Optional.ofNullable(groupByFunction(apples)).ifPresent(System.out::println);


        Optional.ofNullable(groupByCollection(apples)).ifPresent(System.out::println);

    }

    private static Map<String,List<Apple>> groupByNormal(List<Apple> apples){
        Map<String,List<Apple>> map = new HashMap<>();
        for (Apple apple : apples) {
            List<Apple> list = map.get(apple.getColor());
            if(null == list){
                list = new ArrayList<>();
                map.put(apple.getColor(),list);
            }
            list.add(apple);
        }
        return map;
    }

    private static Map<String,List<Apple>> groupByFunction(List<Apple> apples){
        Map<String,List<Apple>> map = new HashMap<>();
        apples.stream()
                .forEach(apple -> {
                    List<Apple> colorList = Optional.ofNullable(map.get(apple.getColor())).orElseGet(()->{
                            List<Apple> list = new ArrayList<>();
                            map.put(apple.getColor(),list);
                            return list;
                    });
                    colorList.add(apple);
                });
        return map;
    }

    private static Map<String,List<Apple>> groupByCollection(List<Apple> apples){
        return apples.stream()
                .collect(Collectors.groupingBy(Apple::getColor));
    }
}
