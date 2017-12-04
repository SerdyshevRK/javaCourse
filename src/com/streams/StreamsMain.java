package com.streams;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StreamsMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите путь к файлу для шифрования:");
        Path srcPath = Paths.get(sc.nextLine());
        System.out.println("Введите путь к файлу ключу:");
        Path keyPath = Paths.get(sc.nextLine());

        try {
            Encode.encodeFile(srcPath, keyPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Введите путь к зашифрованному файлу:");
        srcPath = Paths.get(sc.nextLine());

        try {
            Encode.decodeFile(srcPath, keyPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        InputStream in = new SawInputStream();
//        while (true) {
//            try {
//                System.out.println(in.read());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public static void copyFile() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите путь к файлу (включая имя файла), который нужно скопировать:");
        Path pathIn = Paths.get(sc.nextLine());
        System.out.println("Введите путь (включая имя файла), куда нужно скопировать:");
        Path pathOut = Paths.get(sc.nextLine());

        try (InputStream input = new FileInputStream(pathIn.toFile());
             OutputStream output = new FileOutputStream(new File(pathOut.toString()))) {
            byte[] buffer = new byte[1024];
            int length, offset = 0;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, offset, length);
                offset = length;
            }
        }
    }

    public static void splitFile() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите путь к файлу, который нужно разбить:");
        Path path = Paths.get(sc.nextLine());
        System.out.println("Введите размер кусков в байтах:");
        int pieceLength = sc.nextInt();

        try (InputStream input = new FileInputStream(path.toFile())) {
            OutputStream output = null;
            String fileNameFull = path.getFileName().toString();
            String directoryPath = path.toString().replaceFirst(fileNameFull, "");
            String fileName = fileNameFull.substring(0, fileNameFull.length() - 4) + "_part_";
            String extension = fileNameFull.substring(fileNameFull.length() - 4);
            byte[] pieceBuffer = new byte[pieceLength];
            int length = 0;
            int part = 1;
            while ((length = input.read(pieceBuffer)) > 0) {
                try {
                    output = new FileOutputStream(directoryPath + fileName + part + extension);
                    output.write(pieceBuffer, 0, length);
                    part++;
                } finally {
                    output.close();
                }
            }
        }
    }

    public static void assembleFile() throws IOException {
        Scanner sc = new Scanner(System.in);
        List<String> files = new ArrayList<>();
        String outputPath;
        System.out.println("Введите путь для результирующео файла:");
        outputPath = sc.nextLine();

        while (true) {
            System.out.println("Введите путь до 'куска' или команду '/start' для сбора файла:");
            String inputLine = sc.nextLine();
            if (inputLine.equals("/start")) {
                InputStream input = null;
                int length;
                byte[] buffer = new byte[1024];

                try (OutputStream output = new FileOutputStream(outputPath, true)) {
                    for (String file : files) {
                        try {
                            input = new FileInputStream(file);
                            while ((length = input.read(buffer)) > 0) {
                                output.write(buffer, 0, length);
                            }
                        } finally {
                            input.close();
                        }
                    }
                }
                return;
            }
            files.add(inputLine);
        }
    }
}
