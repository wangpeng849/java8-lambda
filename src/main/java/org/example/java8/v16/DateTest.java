package org.example.java8.v16;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author wangp
 * @Date 2020/4/2
 * @Version 1.0
 */
public class DateTest {

    public static void main(String[] args) throws ParseException {
        /**
         *  问题1
         *  1990+114  年     2+1 月  18 日
         */
        Date date = new Date(114,2,18);
        System.out.println(date);//Tue Mar 18 00:00:00 CST 2014


        /**
         *   线程安全问题
         */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date parse = sdf.parse("20160505");
        System.out.println(parse);


        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                for (int j = 0; j < 100; j++) {
                    Date parseDate = null;
                    try {
                        parseDate = sdf.parse("20200420");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println(parseDate);
                }
            }).start();
        }
    }
}
