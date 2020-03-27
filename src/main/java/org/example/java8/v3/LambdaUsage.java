package org.example.java8.v3;

import org.example.App;
import org.example.java8.v1.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * @Author wangp
 * @Date 2020/3/25
 * @Version 1.0
 */
public class LambdaUsage {
    @FunctionalInterface
    public interface Adder {
        int add(int a, int b);
    }

    //    @FunctionalInterface  -> error
    public interface SmartAdder extends Adder {
        int add(long a, long b);
    }

    @FunctionalInterface
    public interface Nothing extends Adder {

    }

    //    @FunctionalInterface -> error
    public interface DoNothing {

    }


    public static void main(String[] args) {
        Runnable r1 = () -> System.out.println("hello runnable");
        process(r1);
        process(() -> System.out.println("hello"));

        List<Apple> apples = Arrays.asList(
                new Apple("green", 123),
                new Apple("red", 143),
                new Apple("red", 160)
        );

        List<Apple> red = filter(apples, apple -> apple.getColor().equals("red"));
        System.out.println(red);

        List<Apple> byWight = filterByWight(apples, w -> w > 100);
        System.out.println(byWight);

        List<Apple> byBiPredicate = filterByBiPredicate(apples, (color, w) -> color.equals("red") && w > 100);
        System.out.println(byBiPredicate);

        System.out.println("------consumer------");
        simpleTestConsumer(apples,(a)-> System.out.println(a));
        System.out.println("------BiConsumer------");
        simpleBiConsumer(apples,"Hello",(a,c)-> System.out.println(a.toString()+c));

        System.out.println("----------Function---------");
        String black = testFunction(new Apple("Black", 100), (a) -> a.toString());
        System.out.println(black);

        IntFunction<Double> f = i -> i*100.0d;
        double intFunction = f.apply(1);
        System.out.println(intFunction);

        Apple pure = testBiFunction("Pure", 200, Apple::new);
        System.out.println(pure);

        System.out.println("-----supplier-----");
        Supplier<String> str = String::new;
        System.out.println(str.get().getClass());

        Apple apple = createSupplier(Apple::new);
        System.out.println(apple);
    }

    private static void process(Runnable r) {
        r.run();
    }

    private static List<Apple> filter(List<Apple> apples, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple a : apples) {
            if (predicate.test(a))
                result.add(a);
        }
        return result;
    }

    private static List<Apple> filterByWight(List<Apple> apples, LongPredicate predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple a : apples) {
            if (predicate.test(a.getWeight()))
                result.add(a);
        }
        return result;
    }

    private static List<Apple> filterByBiPredicate(List<Apple> apples, BiPredicate<String,Long> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple a : apples) {
            if (predicate.test(a.getColor(), a.getWeight()))
                result.add(a);
        }
        return result;
    }

    public static void simpleTestConsumer(List<Apple> apples, Consumer consumer){
        for (Apple a : apples) {
            consumer.accept(a);
        }
    }

    public static void simpleBiConsumer(List<Apple> apples,String c, BiConsumer consumer){
        for (Apple a : apples) {
            consumer.accept(a,c);
        }
    }

    public static String testFunction(Apple apple,Function<Apple,String> function){
        return function.apply(apple);
    }

    public static Apple testBiFunction(String color,Integer weight,BiFunction<String,Integer,Apple> biFunction){
        return biFunction.apply(color,weight);
    }

    public static Apple createSupplier(Supplier<Apple> supplier){
        return supplier.get();
    }
}

