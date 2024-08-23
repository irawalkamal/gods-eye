package com.automation.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringUtils {

    // Check if a string is null or empty using a lambda expression
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    // Reverse a string using Java Streams
    public static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    // Convert a string to title case using Java Streams
    public static String toTitleCase(String str) {
        if (isNullOrEmpty(str)) {
            return str;
        }
        return Arrays.stream(str.split("\\s+"))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

    // Remove leading and trailing whitespace from a string using method reference
    public static String trim(String str) {
        return str != null ? str.trim() : null;
    }

    // Check if a string contains only digits using a lambda expression
    public static boolean isNumeric(String str) {
        return str != null && str.chars().allMatch(Character::isDigit);
    }
}
