package org.example.java8.v4;

import org.example.App;
import org.example.java8.v1.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Author wangp
 * @Date 2020/3/26
 * @Version 1.0
 */
public class MethodReference {

    public static void main(String[] args) {
        userConsumer((s)-> System.out.println(s),"hello world");

        userConsumer(System.out::println,"::");

        List<Apple> apples = Arrays.asList(
                new Apple("yellow",110),
                new Apple("red",120),
                new Apple("green",80)
        );
        System.out.println(apples);
        apples.sort((o1,o2)->o1.getColor().compareTo(o2.getColor()));
        System.out.println("---- sort ---\n"+apples);

        System.out.println("--- stream ---");
        apples.stream().forEach(a -> System.out.println(a));
        apples.stream().forEach(System.out::println);

        System.out.println("函数推导");
        //推导
        int value = Integer.parseInt("123");
        Function<String, Integer> function = Integer::parseInt;
        Integer intResult = function.apply("456");
        System.out.println(intResult);

        BiFunction<String, Integer, Character> characterBiFunction = String::charAt;
        Character charResult = characterBiFunction.apply("hello", 2);
        System.out.println(charResult);

        Function<Apple, String> colorFunction = Apple::getColor;
        String color = colorFunction.apply(new Apple("Niubility",111));
        System.out.println(color);

        //构造函数推导
        Supplier<String> strSupplier = String::new;
        String s = strSupplier.get();

        Supplier<Apple> appSupplier = Apple::new;
        Apple apple = appSupplier.get();

        BiFunction<String,Long,Apple> appBiFunction = Apple::new;
        Apple appleByConstructor = appBiFunction.apply("Constructor", 111l);
        System.out.println(appleByConstructor);

        ThreeFunction<String,Long,String,ComplexApple> threeFunction = ComplexApple::new;
        ComplexApple complexApple = threeFunction.apply("complex", 1l, "apple");
        System.out.println(complexApple);

        System.out.println("进一步推导");
        List<Apple> apples2 = Arrays.asList(
                new Apple("yellow",110),
                new Apple("red",120),
                new Apple("green",80)
        );
        apples2.sort(Comparator.comparing(Apple::getColor));
        System.out.println(apples2);
    }

    private static<T> void userConsumer(Consumer<T> consumer,T t){
        consumer.accept(t);
        consumer.accept(t);
    }
}
