package org.example.java8.v14;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author farling-wangp
 * @version 1.0
 * @date 2020/3/28 10:56
 */
public class ParallelProcessing {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());


        System.out.println("the best process time(addNomal) => " + measurePerformance(ParallelProcessing::addNomal,100000000));
//        System.out.println("the best process time(iterateStream) => " + measurePerformance(ParallelProcessing::iterateStream,10000000));
//        System.out.println("the best process time(iterateParallelStream) => " + measurePerformance(ParallelProcessing::iterateParallelStream,10000000));
//        System.out.println("the best process time(iterateParallelStreamBox) => " + measurePerformance(ParallelProcessing::iterateParallelStreamBox,10000000));
        System.out.println("the best process time(iterateParallelStreamChange) => " + measurePerformance(ParallelProcessing::iterateParallelStreamChange,10000000));
    }

    private static long iterateStream(long limit) {
        return Stream.iterate(1l, i -> i + 1).limit(limit).reduce(0L, Long::sum);
    }
    private static long iterateParallelStream(long limit) {
        return Stream.iterate(1l, i -> i + 1).limit(limit).parallel().reduce(0L, Long::sum);
    }

    private static long iterateParallelStreamBox(long limit) {
        return Stream.iterate(1l, i -> i + 1).mapToLong(Long::longValue).limit(limit).parallel().reduce(0L, Long::sum);
    }

    private static long iterateParallelStreamChange(long limit) {
        return LongStream.rangeClosed(1,limit).parallel().reduce(0,Long::sum);
    }

    private static long addNomal(long limit) {
        long result = 0;
        for (long i = 0; i < limit; i++) {
            result += i;
        }
        return result;
    }


    private static long measurePerformance(Function<Long, Long> adder, long limit) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.currentTimeMillis();
            long result = adder.apply(limit);
            long duration = System.currentTimeMillis() - start;
//            System.out.println("the result of sum ->" + result);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }
}
