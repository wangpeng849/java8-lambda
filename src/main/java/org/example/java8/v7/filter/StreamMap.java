package org.example.java8.v7.filter;

import org.example.java8.v5.stream.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author wangp
 * @Date 2020/3/26
 * @Version 1.0
 */
public class StreamMap {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        List<Integer> listDouble = list.stream().map(a -> a * 2).collect(Collectors.toList());
        System.out.println(listDouble);

        List<String> collect = listDish().stream().map(Dish::getName).collect(Collectors.toList());
        System.out.println(collect);

        //flatMap flat扁平化
        String[] words = {"hello","world"};
        //{h,e,l,l,o},{w,o,r,l,d}
        Stream<String[]> stream = Arrays.stream(words)
                .map(w -> w.split(""));
        //h,e,l,l,o,w,o,r,l,d
        Stream<String> stringStream = stream.flatMap(Arrays::stream);
        stringStream.distinct().forEach(System.out::println);
    }

    private static List<Dish> listDish(){
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
        return menu;
    }
}
