package org.example.java8.v9;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author wangp
 * @Date 2020/3/26
 * @Version 1.0
 */
public class NumericStream {
    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        Integer sum = stream.filter(i -> i.intValue() > 3).reduce(0, Integer::sum);
        System.out.println(sum);
        //上列本质为下面
        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        Stream<Integer> integerStream = stream.filter(i -> i.intValue() > 3);
        Integer sum2 = integerStream.reduce(0, Integer::sum);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        IntStream intStream = stream.mapToInt(i -> i.intValue());
        int sum3 = intStream.filter(i -> i > 3).sum();
        System.out.println(sum3);
//       intStream.filter(i->i>3).reduce(0,(i,j)->i+j);
        //IntStream比IntegerStream节省内存


        System.out.println("-----intStream-----");
        int a = 9;
        // 1-1000有哪个数可以和a满足勾股定律
        //需要数组[a,b,c...]
        IntStream.rangeClosed(1, 1000)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .boxed()
                .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                .forEach(b -> System.out.println("a="+b[0]+",b="+b[1]+",c="+b[2]));


        System.out.println("==================================");
        IntStream.rangeClosed(1, 1000)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                .forEach(b -> System.out.println("a="+b[0]+",b="+b[1]+",c="+b[2]));
        ;
    }
}
