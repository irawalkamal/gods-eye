package com.automation.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {

    // Read the content of a file and return it as a String using Java Streams
    public static String readFileToString(String filePath) throws IOException {
        return Files.lines(Paths.get(filePath)).collect(Collectors.joining(System.lineSeparator()));
    }

    // Write a String to a file using Files API
    public static void writeStringToFile(String filePath, String content) throws IOException {
        Files.write(Paths.get(filePath), content.getBytes());
    }

    // Read the content of a file line by line and return it as a List of Strings using Java Streams
    public static List<String> readFileToLines(String filePath) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines.collect(Collectors.toList());
        }
    }

    // Write a List of Strings to a file, each String on a new line using Java Streams
    public static void writeLinesToFile(String filePath, List<String> lines) throws IOException {
        Files.write(Paths.get(filePath), lines);
    }

    // Delete a file using Files API
    public static boolean deleteFile(String filePath) throws IOException {
        return Files.deleteIfExists(Paths.get(filePath));
    }

    // Check if a file exists using Files API
    public static boolean fileExists(String filePath) {
        return Files.exists(Paths.get(filePath));
    }
}
