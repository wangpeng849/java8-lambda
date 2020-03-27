package org.example.java8.v7.filter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author wangp
 * @Date 2020/3/26
 * @Version 1.0
 */
public class StreamFilter {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,8,7,6,5,4,3,2,1);
        List<Integer> collect = list.stream()
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(collect);
        list = collect.stream().distinct().collect(Collectors.toList());
        System.out.println("去重后--> \n"+ list);
        list = list.stream().skip(2).collect(Collectors.toList());
        System.out.println("跳过2个后-->\n" + list);
    }
}
