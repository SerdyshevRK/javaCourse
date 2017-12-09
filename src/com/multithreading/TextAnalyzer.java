package com.multithreading;

import java.io.File;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextAnalyzer {

    BlockingQueue<String> input = new ArrayBlockingQueue<>(50);
    BlockingQueue<Map<String, Integer>> output = new ArrayBlockingQueue<>(4);
    Map<String, Long> wordsOccurrence = new TreeMap<>();
    private final static String STOP = new String();

    public static void main(String[] args) throws Exception {
        File file = new File("E:/ITMO/Learning/Practice/wp/wp.txt");
        List<String> lines = Files.readAllLines(file.toPath());
        TextAnalyzer analyzer = new TextAnalyzer();
        int cpuCounter = Runtime.getRuntime().availableProcessors();

        ExecutorService pool = Executors.newFixedThreadPool(cpuCounter);
        List<FutureTask<Map<String, Long>>> tasks = new ArrayList<>();
        for (int i = 0; i < cpuCounter; i++) {
            tasks.add(new FutureTask(analyzer.new AnalyzerThread(analyzer.input)));
        }

        for (FutureTask<Map<String, Long>> task : tasks) {
            pool.submit(task);
        }

        // запись строк из файла в очередь для потоков
        for (String line : lines) {
            if (line.length() > 0)
                analyzer.input.put(line);
        }
        analyzer.input.put(STOP);

        for (FutureTask<Map<String, Long>> task : tasks) {
            analyzer.mergeResults(task.get());
        }
        pool.shutdown();
        // вывод топа слов в консоль
        analyzer.printTop(10);
    }

    private void printTop(int range) {
        List<String> top = wordsOccurrence.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(range)
                .map(e -> e.getKey())
                .collect(Collectors.toList());

        System.out.println(top.toString());
    }

    private List<String> readWords(String line) {
        List<String> words = new ArrayList<>();
        String[] wordsArray = line.toLowerCase()
                .replaceAll("\\pP", " ")
                .trim()
                .split("\\s");

        for (String word : wordsArray) {
            if (word.length() > 0) {
                word.trim();
                words.add(word);
            }
        }

        return words;
    }

    private void countWords(Map<String, Long> map, List<String> words) {
        for (String word : words) {
            if (map.containsKey(word)){
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1L);
            }
        }
    }

    private synchronized void mergeResults(Map<String, Long> map) {
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            if (wordsOccurrence.containsKey(entry.getKey())) {
                wordsOccurrence.put(entry.getKey(), entry.getValue() + wordsOccurrence.get(entry.getKey()));
                continue;
            }
            wordsOccurrence.put(entry.getKey(), entry.getValue());
        }
    }

    private class AnalyzerThread implements Callable<Map<String, Long>> {
        BlockingQueue<String> input;
        String line;
        Map<String, Long> innerMap = new HashMap<>();
        List<String> words;

        public AnalyzerThread(BlockingQueue<String> input) {
            this.input = input;
        }

        @Override
        public Map<String, Long> call() {
            try {
                while (true) {
                    line = input.take();
                    if (line == STOP) {
                        input.put(STOP);
                        break;
                    }

                    words = Arrays.stream(line.toLowerCase().replaceAll("\\pP", " ").trim().split(" "))
                            .filter(word -> !word.equals(""))
                            .map(String::trim)
                            .collect(Collectors.toList());

                    countWords(innerMap, words);
                }

            } catch(InterruptedException e){
                e.printStackTrace();
            }
            return innerMap;
        }
    }
}
