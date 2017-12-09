package com.textAnalyzing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class Analyzer {
    public static void main(String[] args) {
        File text = new File("E:/ITMO/Learning/Practice/wp/wp.txt");
        List<String> words;
        Map<String, Integer> wordsOccurrence;
        Map<String, Integer> phrasesOccurrence;
        Map<String, Integer> groups;

        try {
            words = Files.lines(text.toPath())
                    .parallel()
                    .map(line -> line.toLowerCase().replaceAll("\\pP", " "))
                    .flatMap(line -> Arrays.stream(line.split(" ")))
                    .map(String::trim)
                    .filter(word -> !"".equals(word))
                    .collect(Collectors.toList());

            wordsOccurrence = countWords(words);
            printTopWords(wordsOccurrence, 10);
        } catch (IOException e) {
            e.printStackTrace();
        }



        // if file exists, read all lines from this file
//        try {
//            lines = Files.readAllLines(text.toPath());
//        } catch (IOException e) {
//            e.printStackTrace();
//            return;
//        }

        // split lines to a words

//        words = readWords(lines);
//        // count the occurrence of words in the text



//        for (Map.Entry<String, Integer> entry : wordsOccurrence.entrySet()) {
//            System.out.println("Word '" + entry.getKey() + "' occurs in the text " + entry.getValue() + " times.");
//        }
//
        // count the occurrence of phrases in the text
//        phrasesOccurrence = countPhrases(2, words);
//        for (Map.Entry<String, Integer> entry : phrasesOccurrence.entrySet()) {
//            System.out.println("Phrase '" + entry.getKey() + "' occurs in the text " + entry.getValue() + " times.");
//        }
//
//        // count the occurrence of letters in the text
//        countLetters(lines);
//
//        // group words by their length
//        groups = groupWords(words);
//        System.out.println("Все слова из четырех букв:");
//        int c = 0;
//        for (Map.Entry<String, Integer> entry : groups.entrySet()) {
//            if (entry.getValue() == 4) {
//                System.out.print("\t" + entry.getKey());
//                if (++c % 10 == 0)
//                    System.out.print("\n");
//            }
//        }
//        System.out.println();
//
//        // top ten most used words and phrases in text
//        System.out.println("Десять наиболее часто встречающихся слов в тексте:");

//        System.out.println("Десять наиболее часто встречающихся фраз в тексте:");
//        printTopTenWords(phrasesOccurrence);
    }

    private static List<String> readWords(List<String> lines){
        List<String> words = new ArrayList<>();

        for (String line : lines) {
            // Для каждой строки
            String[] wordSplit =
                    line.toLowerCase() // Переводим в нижний регистр
                            .replaceAll("\\pP", " ")
//                            .replaceAll("\\p{Punct}", " ") // Заменяем все знаки на пробел
//                            .replaceAll("\"", "")
                            .trim() // Убираем пробелы в начале и конце строки.
                            .split("\\s"); // Разбиваем строки на слова

            for (String s : wordSplit) {
                // Выбираем только непустые слова.
                if (s.length() > 0)
                    words.add(s.trim());
            }
        }
        return words;
    }
    private static void countLetters(List<String> lines){
        int[] lettersCount = new int[Letters.values().length];

        for (int i = 0; i < lines.size(); i++) {
            String string = lines.get(i);
            for (char ch : string.toCharArray()) {
                if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z')
                    lettersCount[Letters.valueOf(String.valueOf(ch).toUpperCase()).ordinal()]++;
            }
        }

        System.out.println("Частота встречаемости букв использованных в тексте (в процентах):");
        double[] lettersPercents = percents(lettersCount);
        for (int i = 0; i < Letters.values().length; i++) {
            System.out.printf("%s - %.2f\n", Letters.values()[i], lettersPercents[i]);
        }
    }
    private static int findMaxValue(int[] array){
        int retVal = array[0];
        for (int i = 1; i < array.length; i++) {
            if (retVal < array[i])
                retVal = array[i];
        }
        return retVal;
    }
    private static double[] percents(int[] array){
        double[] retArray = new double[array.length];
        int max = findMaxValue(array);

        for (int i = 0; i < array.length; i++) {
            retArray[i] = (double) array[i] / max * 100;
        }
        return retArray;
    }
    private static Map<String, Integer> groupWords(List<String> words) {
        Map<String, Integer> groups = new HashMap<>();
        for (String word : words) {
            groups.put(word, word.length());
        }
        return groups;
    }

    private static void printTopWords(Map<String, Integer> map, int range) {

        String[] top = new String[range];
        List<Integer> values = new ArrayList<>();

        for (Integer value : map.values()) {
            values.add(value);
        }
        values.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -1 * (o1 - o2);
            }
        });

        for (int i = 0; i < range; i++) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == values.get(i))
                    top[i] = entry.getKey();
            }
        }
        System.out.println(Arrays.toString(top));
    }

    private static Map<String, Integer> countWords(List<String> words) {
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

    private static Map<String, Integer> countPhrases(int phraseLength, List<String> words) {
        System.out.println("Calculating, stand by...");

        Map<String, Integer> map = new TreeMap<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < words.size() - phraseLength; i++) {
            for (int j = i; j < phraseLength + i; j++) {
                sb.append(words.get(j)).append(" ");
            }
            if (map.containsKey(sb.toString().trim())) {
                map.put(sb.toString().trim(), map.get(sb.toString().trim()) + 1);
            } else {
                map.put(sb.toString().trim(), 1);
            }
            sb.setLength(0);
        }
        return map;
    }

    private class AnalizerThread implements Runnable {
        @Override
        public void run() {

        }
    }
}
