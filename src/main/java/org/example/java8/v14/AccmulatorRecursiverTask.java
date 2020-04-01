package org.example.java8.v14;

import java.util.concurrent.RecursiveTask;

/**
 * @author farling-wangp
 * @version 1.0
 * @date 2020/3/28 11:32
 */
public class AccmulatorRecursiverTask extends RecursiveTask<Integer>  {

    private final int start;
    private final int end;
    private final int[] data;
    private final int limit = 3;

    public AccmulatorRecursiverTask(int start, int end, int[] data) {
        this.start = start;
        this.end = end;
        this.data = data;
    }

    @Override
    protected Integer compute() {
        if((end - start) <= limit){
            int result = 0;
            for (int i = start; i < end; i++) {
                result += data[i];
            }
            return result;
        }
        int mid = (start + end)/2;
        AccmulatorRecursiverTask left = new AccmulatorRecursiverTask(start,mid,data);
        AccmulatorRecursiverTask right = new AccmulatorRecursiverTask(mid,end,data);
        left.fork();

        Integer rightResult = right.compute();
        Integer leftResult =  left.join();
        return leftResult+rightResult;
    }
}
