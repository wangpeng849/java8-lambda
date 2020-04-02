package org.example.java8.v16;

import javax.swing.text.DateFormatter;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

/**
 * @Author wangp
 * @Date 2020/4/2
 * @Version 1.0
 */
public class LocalDateTest {

    public static void main(String[] args) throws InterruptedException {
        testLocalDate();
        System.out.println("----------------");
        testLocalTime();
        System.out.println("----------------");
        combineLocalDateAndTime();
        System.out.println("----------------");
        testInstant();
        System.out.println("----------------");
        testDuration();
        System.out.println("----------------");
        testPeriod();
        System.out.println("----------------");
        testDateFormat();
        System.out.println("----------------");
        testDateParse();
    }

    private static void testLocalDate(){
        LocalDate date = LocalDate.of(2020, 4, 02);
        System.out.println(date);
        System.out.println(date.getYear());
        System.out.println(date.getMonth());
        System.out.println(date.getMonthValue());
        System.out.println(date.getDayOfYear());
        System.out.println(date.getDayOfMonth());
        System.out.println(date.getDayOfWeek());

        LocalDate now = LocalDate.now();
        System.out.println(now);
    }


    private static void testLocalTime(){
        LocalTime now = LocalTime.now();
        System.out.println(now);
        System.out.println(now.getHour());
        System.out.println(now.getMinute());
        System.out.println(now.getSecond());
    }

    private static void combineLocalDateAndTime() {
        LocalDate localDate = LocalDate.now();
        LocalTime time = LocalTime.now();

        LocalDateTime localDateTime = LocalDateTime.of(localDate, time);
        System.out.println(localDateTime);
        System.out.println(localDateTime.getChronology());

        System.out.println(LocalDateTime.now());

        long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("-8"));
        System.out.println("秒数："+second);
        long millSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println("毫秒数："+millSecond);
    }

    private static void testInstant() throws InterruptedException {
        Instant start = Instant.now();
        Thread.sleep(1000L);
        Instant end = Instant.now();
        Duration duration = Duration.between(start,end);
        System.out.println(duration.toMillis());
    }

    private static void testDuration(){
        LocalTime time = LocalTime.now();
        LocalTime beforeTime = time.minusHours(1);
        System.out.println(Duration.between(time,beforeTime));
        System.out.println(Duration.between(time,beforeTime).toHours());

    }

    private static void testPeriod(){
        Period between = Period.between(LocalDate.of(2019, 1, 11),
                LocalDate.of(2020, 4, 2));
        System.out.println(between.getMonths());
        System.out.println(between.getDays());
        System.out.println(between.getUnits());
    }

    private static void testDateFormat(){
        LocalDate localDate = LocalDate.now();
        String f1 = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        String f2 = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(f1);
        System.out.println(f2);

        String f3 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy:MM:dd HH-mm-SS"));
        System.out.println(f3);


        DateTimeFormatter mySelfFormatter = DateTimeFormatter.ofPattern("yyyy->MM->dd");
        String format = localDate.format(mySelfFormatter);
        System.out.println(format);
    }


    private static void testDateParse(){
        String date = "20200402";
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(localDate);
    }
}
