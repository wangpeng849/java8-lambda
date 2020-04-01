package org.example.java8.v14;

import java.util.concurrent.ForkJoinPool;

/**
 * @author farling-wangp
 * @version 1.0
 * @date 2020/3/28 11:29
 */
public class ForkJoinPoolTest {

    private static int[] arr = {1,2,3,4,5,6,7,8,9,10};

    public static void main(String[] args) {
        System.out.println("result => "  + calc());

        AccmulatorRecursiverTask task = new AccmulatorRecursiverTask(0,arr.length,arr);
        ForkJoinPool pool = new ForkJoinPool();
        Integer result = pool.invoke(task);
        System.out.println("result => "+result);
    }

    private static int calc(){
        int result = 0;
        for (int i : arr) {
            result+=i;
        }
        return result;
    }
}
