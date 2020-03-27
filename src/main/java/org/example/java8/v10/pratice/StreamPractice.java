package org.example.java8.v10.pratice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author wangp
 * @Date 2020/3/26
 * @Version 1.0
 * <p>
 * Stream 综合练习
 */
public class StreamPractice {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //1. Find all transactions in the year 2011 and sort them by value (small to high).
        System.out.println("-------------1------------------");
        List<Transaction> list1 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue).reversed())
                .collect(Collectors.toList());
        System.out.println(list1);


        //2.What are all the unique cities where the traders work?
        System.out.println("-------------2------------------");

        transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);

        //3.Find all traders from Cambridge and sort them
        System.out.println("-------------3------------------");
        transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);

        //4.Return a string of all traders’ names sorted alphabetically
        System.out.println("-------------4------------------");
        String value = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (name1, name2) -> name1 + "-" + name2);
        System.out.println(value);

        //5. Are any traders based in Milan?
        System.out.println("-------------5------------------");
        boolean b1 = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        boolean b2 = transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(trader -> trader.getCity().equals("Milan"));
        System.out.println(b1 + " " +b2);

        //6.Print all transactions’ values from the traders living in Cambridge.
        System.out.println("-------------6------------------");
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
//                .map(Transaction::getValue)
                .map((n)->n.getTrader().getName()+"-"+n.getValue())
                .distinct()
                .forEach(System.out::println);

        //7.What’s the highest value of all the transactions?
        System.out.println("-------------7------------------");
        Integer max = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .get();
        System.out.println(max);


        //8.Find the transaction with the smallest value.
        System.out.println("-------------8------------------");
        Integer min = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min)
                .get();
        System.out.println(min);
    }
}
