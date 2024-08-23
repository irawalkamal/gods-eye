package com.automation.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.stream.Stream;

public class DateUtils {

    // Format a Date object to a String in the specified format using DateTimeFormatter
    public static String formatDate(Date date, String format) {
        return DateTimeFormatter.ofPattern(format).format(date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
    }

    // Parse a String into a LocalDate object using DateTimeFormatter
    public static LocalDate parseDate(String dateString, String format) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(format));
    }

    // Get the current date as a String in the specified format using DateTimeFormatter
    public static String getCurrentDate(String format) {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(format));
    }

    // Calculate the difference in days between two dates using ChronoUnit
    public static long getDaysDifference(Date startDate, Date endDate) {
        return ChronoUnit.DAYS.between(
                startDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate(),
                endDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()
        );
    }

    // Calculate the period between two LocalDates using Stream API
    public static Stream<LocalDate> getPeriodBetween(LocalDate startDate, LocalDate endDate) {
        return startDate.datesUntil(endDate);
    }
}
