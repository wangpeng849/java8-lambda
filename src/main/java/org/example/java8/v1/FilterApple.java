package org.example.java8.v1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterApple {


    @FunctionalInterface
    public interface AppleFilter {
        boolean filter(Apple apple);
    }
    @FunctionalInterface
    public interface MyAppleFilter {
        boolean filter(Apple apple,int flag);
    }
    public static List<Apple> findApple(List<Apple> apples, MyAppleFilter appleFilter) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if (appleFilter.filter(apple,1))
                list.add(apple);
        }
        return list;
    }

    public static List<Apple> findApple(List<Apple> apples, AppleFilter appleFilter) {
        List<Apple> list = new ArrayList<>();

        for (Apple apple : apples) {
            if (appleFilter.filter(apple))
                list.add(apple);
        }
        return list;
    }
    public static class myFindAppleFilter implements MyAppleFilter{

        @Override
        public boolean filter(Apple apple, int flag) {
            return apple != null && flag ==1;
        }
    }

    public static class GreenAnd160WeightFilter implements AppleFilter {

        @Override
        public boolean filter(Apple apple) {
            return (apple.getColor().equals("green") && apple.getWeight() >= 160);
        }
    }

    public static class YellowLess150WeightFilter implements AppleFilter {

        @Override
        public boolean filter(Apple apple) {
            return (apple.getColor().equals("yellow") && apple.getWeight() < 150);
        }
    }

    public static List<Apple> findGreenApple(List<Apple> apples) {

        List<Apple> list = new ArrayList<>();

        for (Apple apple : apples) {
            if ("green".equals(apple.getColor())) {
                list.add(apple);
            }
        }

        return list;
    }

    public static List<Apple> findApple(List<Apple> apples, String color) {
        List<Apple> list = new ArrayList<>();

        for (Apple apple : apples) {
            if (color.equals(apple.getColor())) {
                list.add(apple);
            }
        }

        return list;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Apple> list = Arrays.asList(
                new Apple("green", 150),
                new Apple("yellow", 120),
                new Apple("green", 170));
//        List<Apple> greenApples = findGreenApple(list);
//        assert greenApples.size() == 2;

       /* List<Apple> greenApples = findApple(list, "green");
        System.out.println(greenApples);

        List<Apple> redApples = findApple(list, "red");
        System.out.println(redApples);*/
        /*
        List<Apple> result = findApple(list, new GreenAnd160WeightFilter());
        System.out.println(result);

//匿名类实现接口
        List<Apple> yellowList = findApple(list, new AppleFilter() {
            @Override
            public boolean filter(Apple apple) {
                return "yellow".equals(apple.getColor());
            }
        });

        System.out.println(yellowList);*/

        List<Apple> lambdaResult = findApple(list, apple -> (apple.getColor().equals("green")
                && apple.getWeight() > 150));

        System.out.println(lambdaResult);
        //我自己实现
        List<Apple> myLambda = findApple(list, (apple,flag) -> apple != null && flag ==1);
        System.out.println(myLambda);
        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();


        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
//        Thread.currentThread().join();
        System.out.println(Thread.currentThread().getName());




    }




}
