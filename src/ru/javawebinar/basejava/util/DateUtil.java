package ru.javawebinar.basejava.util;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 * gkislin
 * 20.07.2016
 */
public class DateUtil {

    public static final LocalDate NOW = LocalDate.of(3000, 1, 1);
    public static final String nowString = "Сейчас";

    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }

    public static String toString(LocalDate date) {
        return date.equals(NOW) ? nowString : date.format(DateTimeFormatter.ofPattern("MM/yyyy"));
    }

    public static LocalDate fromString(String str) {
        str = str.trim();
        if (str.toLowerCase().equals(nowString.toLowerCase()) || str.isEmpty()) {
            return NOW;
        }
        String[] tmp = str.split("/");
        return of(Integer.parseInt(tmp[1]), Month.of(Integer.parseInt(tmp[0])));
    }

}