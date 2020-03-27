package org.example.java8.v2;

import org.example.App;
import org.example.java8.v1.Apple;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author wangp
 * @Date 2020/3/25
 * @Version 1.0
 */
public class LambdaExpression {
    public static void main(String[] args) {
        Comparator<Apple> byColor = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getColor().compareTo(o2.getColor());
            }
        };

        List<Apple> list = Collections.emptyList();
        list.sort(byColor);

        Comparator<Apple> byColor2 = ((o1, o2) -> o1.getColor().compareTo(o2.getColor()));
        Comparator<Apple> byColor3 = (Comparator.comparing(Apple::getColor));

        Function<String,Integer> fLambda =  s -> s.length();
        Predicate<Apple> pLambda = apple -> apple.getColor().equals("green");
        Runnable r = ()->{};
        Supplier s = ()->"hello";
        Supplier<Apple> appleSupplier = Apple::new;
        Test test = () -> "hello";
    }
    interface Test{
        public String test();
    }
}
