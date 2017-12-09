package com.streamAPI;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import static java.util.stream.Collectors.*;

public class StreamAPIMain {
    public static void main(String[] args) throws IOException {
        File file = new File("E:\\ITMO\\Learning\\Practice\\wp\\wp.txt");

        Map<Integer, Set<String>> groups =
                Files.lines(file.toPath())
                .parallel()
                .map(line -> line.toLowerCase())
                .map(line -> line.replaceAll("\\pP", " "))
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .map(String::trim)
                .filter(word -> !word.equals(""))
                .collect(groupingBy(String::length, toCollection(TreeSet::new)));

        System.out.println(groups.toString());
    }
}
