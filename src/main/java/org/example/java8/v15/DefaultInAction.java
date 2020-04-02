package org.example.java8.v15;

/**
 * @Author wangp
 * @Date 2020/4/2
 * @Version 1.0
 */
public class DefaultInAction {

    private interface A {
        int size(int a);

        default boolean isEmpty() {
            return size(10) == 0;
        }
    }

    public static void main(String[] args) {
        A a = x -> x - 10;
//        A a = x -> x - 101;
        System.out.println(a.size(10));
        System.out.println(a.isEmpty());


        DefaultInAction action = new DefaultInAction();
        action.confuse(null);

        int[] arr = null;
        Object o = arr;
        action.confuse(o);
        //object与方法参数最能匹配
        //若只传一个null， int[] 的参数类型更具体


        B b = new D();
        b.hello();
    }

    private void confuse(Object o) {
        System.out.println("object");
    }

    private void confuse(int[] i) {
        System.out.println("int[]");
    }









    private interface B {
        default void hello(){
            System.out.println("====B hello ===");
        }
    }

    private interface C extends B{
        @Override
        default void hello (){
            System.out.println(" ===C hello ===");
        }
    }

    private static class D implements B,C{

    }
}
