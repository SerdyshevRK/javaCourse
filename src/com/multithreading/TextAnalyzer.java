package com.multithreading;

import java.io.File;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TextAnalyzer {

    BlockingQueue<String> input = new ArrayBlockingQueue<>(50);
    BlockingQueue<Map<String, Integer>> output = new ArrayBlockingQueue<>(4);
    Map<String, Integer> wordsOccurrence = new TreeMap<>();
    private final static String STOP = new String();

    public static void main(String[] args) throws Exception {
        File file = new File("E:/ITMO/Learning/Practice/wp/wp.txt");
        List<String> lines = Files.readAllLines(file.toPath());
        TextAnalyzer analyzer = new TextAnalyzer();
        int cpuCounter = Runtime.getRuntime().availableProcessors();
        Thread thread;

        // запуск потоков в количестве равном количеству доступных ядер
        for (int i = 0; i < cpuCounter; i++) {
            thread = new Thread(analyzer.new AnalyzerThread(analyzer.input, analyzer.output));
            thread.start();
        }

        // запись строк из файла в очередь для потоков
        for (String line : lines) {
            if (line.length() > 0)
                analyzer.input.put(line);
        }
        analyzer.input.put(STOP);

        // получение результатов работы потоков в виде 'Map<String, Integer>'
        // и сведение результатов в общую мапу
        for (int i = 0; i < cpuCounter; i++) {
            analyzer.mergeResults(analyzer.output.take());
        }

        // вывод топа слов в консоль
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

    private List<String> readWords(String line) {
        List<String> words = new ArrayList<>();
        String[] wordsArray = line.toLowerCase()
                .replaceAll("\\pP", " ")
//                .replaceAll("\\p{Punct}", " ")
//                .replaceAll("\"", "")
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

    private void countWords(Map<String, Integer> map, List<String> words) {
        for (String word : words) {
            if (map.containsKey(word)){
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        }
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
        BlockingQueue<String> input;
        BlockingQueue<Map<String, Integer>> output;
        String line;
        Map<String, Integer> innerMap = new HashMap<>();
        List<String> words;

        public AnalyzerThread(BlockingQueue<String> input, BlockingQueue<Map<String, Integer>> output) {
            this.input = input;
            this.output = output;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    line = input.take();
                    if (line == STOP) {
                        input.put(STOP);
                        break;
                    }

                    words = readWords(line);
                    countWords(innerMap, words);
                }

                output.put(innerMap);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
