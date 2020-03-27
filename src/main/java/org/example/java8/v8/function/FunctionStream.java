package org.example.java8.v8.function;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @Author wangp
 * @Date 2020/3/26
 * @Version 1.0
 */
public class FunctionStream {
    public static void main(String[] args) {
        /**
         ***********
         *  match  *
         ***********
         */

        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        boolean match = stream.allMatch(i -> i > 4);
        System.out.println(match);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        boolean match1 = stream.anyMatch(i -> i > 6);
        System.out.println(match1);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        boolean match2 = stream.noneMatch(i -> i < 0);
        System.out.println(match2);


        /**
         ***********
         *  find  *
         ***********
         */
        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        Optional<Integer> optional1 = stream.filter(i -> i % 2 == 0).findAny();
        System.out.println("findAny  --> " + optional1.get());

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        Optional<Integer> optional3 = stream.filter(i -> i > 10).findAny();
        System.out.println("optional3 --> " + optional3.orElse(-1));

        int result = find(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, -1, i -> i > 10);
        System.out.println("like optional3 --> " + result);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        Optional<Integer> optional2 = stream.filter(i -> i % 2 == 0).findFirst();
        optional2.ifPresent(System.out::println);


        /**
         ***********
         *  reduce *
         ***********
         */
        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        Integer add = stream.reduce(0, (i, j) -> i + j);
        System.out.println("累加："+add);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        stream.reduce((i, j) -> i + j).ifPresent(System.out::println);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        Integer add2 = stream.reduce(0, Integer::sum);
        System.out.println("累加2："+add2);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        stream.reduce((i,j)->(i>j?i:j)).ifPresent(System.out::println);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        stream.reduce(Integer::max).ifPresent(System.out::println);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        stream.reduce(Integer::min).ifPresent(System.out::println);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7, 9});
        stream.filter(i->i%2==0).reduce((i,j)->i*j).ifPresent(System.out::println);
    }


    private static int find(Integer[] values, int defaultValue, Predicate<Integer> predicate) {
        for (Integer value : values) {
            if (predicate.test(value)) {
                return value;
            }
        }
        return defaultValue;
    }
}
