package ru.dmitriev.MyThirdTestAppSpringBoot.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTimeUtil {
    public static SimpleDateFormat getCustomFormat() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    }

    public static long getPassedTime(String time_1, String time_2) throws ParseException {
        SimpleDateFormat format = getCustomFormat();
        return Math.abs(format.parse(time_2).getTime() - format.parse(time_1).getTime());
    }
}