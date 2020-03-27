package org.example.java8.v5.stream;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @Author wangp
 * @Date 2020/3/26
 * @Version 1.0
 */
public class SimpleStream {
    public static void main(String[] args) {
        // 低于卡路里150
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
        List<String> dishNameByCollection = getDishNameByCollection(menu);
        System.out.println(dishNameByCollection);

        List<String> dishNameByStream = getDishNameByStream(menu);
        System.out.println(dishNameByStream);


        //stream has already been operated upon or closed
        //一个stream只能操作一次
        Stream<Dish> stream = menu.stream();
        stream.forEach(System.out::println);
//        stream.forEach(System.out::println);

        List<String> result = menu.stream()
                .filter(d ->
                        {
                            System.out.println("filtering ---> " + d.getName());
                            return d.getCalories() > 300;
                        }
                )
                .map(d ->
                        {
                            System.out.println("map --> " + d.getName());
                            return d.getName();
                        }
                )
                .limit(3).collect(toList());
        System.out.println("--------------------------\n"+result);


        System.out.println("--------------------------------------------------");
        Stream<Dish> dishStream = Stream.of(new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
        dishStream.forEach(System.out::println);
    }

    private static List<String> getDishNameByCollection(List<Dish> menu) {
        List<Dish> lowCalories = new ArrayList<>();

        for (Dish dish : menu) {
            if (dish.getCalories() < 400) {
                lowCalories.add(dish);
            }
        }

        Collections.sort(lowCalories, Comparator.comparing(Dish::getCalories));

        List<String> dishNameList = new ArrayList<>();
        for (Dish d : lowCalories) {
            dishNameList.add(d.getName());
        }
        return dishNameList;
    }

    private static List<String> getDishNameByStream(List<Dish> menu) {
        List<String> dishNameList = menu.stream().filter(d -> d.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .limit(3)
                .collect(toList());
        return dishNameList;
    }
}
