package org.example.java8.v13;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

/**
 * @author farling-wangp
 * @version 1.0
 * @date 2020/3/28 10:47
 */
public class CustomerCollectorAction {

    public static void main(String[] args) {
        Collector<String,List<String>,List<String>> collector = new ToListCollector();
        String[] strArr = new String[]{"wangp","pp","java8","create","lambda","stream"};
        List<String> list = Arrays.stream(strArr)
                .filter(s -> s.length() > 5)
                .collect(collector);
        System.out.println(list);

        System.out.println("--------------------------\n");
        List<String> list2 = Arrays.asList("wangp", "pp", "java8", "create", "lambda", "stream")
                .parallelStream().filter(s -> s.length() > 5)
                .collect(collector);
        System.out.println(list2);

    }
}
