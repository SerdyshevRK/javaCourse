package com.multithreading;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class TextAnalyzer {

    Map<String, Integer> wordsOccurrence = new TreeMap<>();

    public static void main(String[] args) throws Exception {
        File file = new File("E:/ITMO/Learning/Practice/wp/wp.txt");
        List<String> words;
        TextAnalyzer analyzer = new TextAnalyzer();
        int cpuCounter = Runtime.getRuntime().availableProcessors();
        words = analyzer.readWords(file);
        int pieceLength = words.size() / cpuCounter;
        int k;
        Thread thread;

        for (int i = 0; i < cpuCounter; i++) {
            k = i * pieceLength;
            if (i == cpuCounter - 1) {
                thread = new Thread(analyzer.new AnalyzerThread(words.subList(k, words.size())));
                thread.start();
                thread.join();
            }
            thread = new Thread(analyzer.new AnalyzerThread(words.subList(k, k + pieceLength)));
            thread.join();
        }

        analyzer.printTop(10);
    }

    private void printTop(int range) {
        String[] top = new String[range];
        List<Integer> values = new ArrayList<>();

        for (Integer value : wordsOccurrence.values()) {
            values.add(value);
        }
        values.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -1 * (o1 - o2);
            }
        });

        for (int i = 0; i < range; i++) {
            for (Map.Entry<String, Integer> entry : wordsOccurrence.entrySet()) {
                if (entry.getValue() == values.get(i))
                    top[i] = entry.getKey();
            }
        }
        System.out.println(Arrays.toString(top));
    }

    private List<String> readWords(File file) throws IOException {
        List<String> retWords = new ArrayList<>();
        List<String> lines = Files.readAllLines(file.toPath());

        for (String line : lines) {
            String[] wordSplit = line.toLowerCase()
                                .replaceAll("\\p{Punct}", " ")
                                .trim()
                                .split("\\s");

            for (String string : wordSplit) {
                if (string.length() > 0)
                    retWords.add(string.trim());
            }
        }
        return retWords;
    }

    private Map<String, Integer> countWords(List<String> words) {
        Map<String, Integer> map = new TreeMap<>();
        for (String word : words) {
            if (map.containsKey(word)){
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        }
        return map;
    }

    private synchronized void mergeResults(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (wordsOccurrence.containsKey(entry.getKey())) {
                wordsOccurrence.put(entry.getKey(), entry.getValue() + wordsOccurrence.get(entry.getKey()));
                continue;
            }
            wordsOccurrence.put(entry.getKey(), entry.getValue());
        }
    }

    private class AnalyzerThread implements Runnable {
        List<String> words;

        public AnalyzerThread(List<String> words) {
            this.words = words;
        }

        @Override
        public void run() {
            Map<String, Integer> innerMap = countWords(words);
            mergeResults(innerMap);
        }
    }
}
