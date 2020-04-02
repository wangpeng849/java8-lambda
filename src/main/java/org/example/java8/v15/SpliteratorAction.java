package org.example.java8.v15;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * @Author wangp
 * @Date 2020/4/2
 * @Version 1.0
 */
public class SpliteratorAction {
    public static void main(String[] args) {
        IntStream intStream = IntStream.rangeClosed(0,10);
        Spliterator.OfInt spliterator = intStream.spliterator();
        Consumer<Integer> consumer = i -> System.out.println(i);
        spliterator.forEachRemaining(consumer);
    }
}
