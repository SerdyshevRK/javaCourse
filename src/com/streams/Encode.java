package com.streams;

import java.io.*;
import java.nio.file.Path;

public final class Encode {

    /**
     * Функция выполняет шифрование файла с помощью дргого файла
     * @param srcFile - файл, который нужно зашифровать
     * @param keyFile - файл - ключ, с помощью которого происходит шифрование исходного файла
     * @throws IOException
     */
    public static void encodeFile(Path srcFile, Path keyFile) throws IOException{
        String fullFileName = srcFile.getFileName().toString();
        String directoryPath = srcFile.toString().replaceFirst(fullFileName, "");
        String fileExtension = fullFileName.substring(fullFileName.lastIndexOf("."), fullFileName.length());
        String fileName = fullFileName.replaceFirst(fileExtension, "");

        try (InputStream srcInput = new FileInputStream(srcFile.toString());
            InputStream keyInput = new FileInputStream(keyFile.toString());
            OutputStream output = new FileOutputStream(directoryPath + fileName + "_encode" + fileExtension, true)) {

            encodeDecodeByFile(srcInput, keyInput, output);
        }
    }

    /**
     * Функция выполняет дешифрование файла с помощью дргого файла
     * @param encodeFile - зашифрованный файл
     * @param keyFile - файл-ключ для дешифровки
     * @throws IOException
     */
    public static void decodeFile(Path encodeFile, Path keyFile) throws IOException{
        String fullFileName = encodeFile.getFileName().toString();
        String directoryPath = encodeFile.toString().replaceFirst(fullFileName, "");
        String fileExtension = fullFileName.substring(fullFileName.lastIndexOf("."), fullFileName.length());
        String fileName = fullFileName.replaceFirst(fileExtension, "")
                                        .replaceFirst("_encode", "");

        try (InputStream encodeInput = new FileInputStream(encodeFile.toString());
            InputStream keyInput = new FileInputStream(keyFile.toString());
            OutputStream output = new FileOutputStream(directoryPath + fileName + "_decode" + fileExtension, true)) {

            encodeDecodeByFile(encodeInput, keyInput, output);
        }
    }

    /**
     * Функция выполняет шифрование файла при помощи передаваемого ключа
     * @param srcFile - файл для шифрования
     * @param key - ключ, с помощью которого происходит шифрование исходного файла
     * @throws IOException
     */
    public static void encodeFile(Path srcFile, String key) throws IOException{
        String fullFileName = srcFile.getFileName().toString();
        String directoryPath = srcFile.toString().replaceFirst(fullFileName, "");
        String fileExtension = fullFileName.substring(fullFileName.lastIndexOf("."), fullFileName.length());
        String fileName = fullFileName.replaceFirst(fileExtension, "");

        try (InputStream input = new FileInputStream(srcFile.toString());
             OutputStream output = new FileOutputStream(directoryPath + fileName + "_encode" + fileExtension, true)) {

            encodeDecodeByKey(key, input, output);
        }
    }

    /**
     * Функция выполняет дешифрование файла при помощи передаваемого ключа
     * @param encodeFile - зашифрованный файл
     * @param key - ключ для дешифровки
     * @throws IOException
     */
    public static void decodeFile(Path encodeFile, String key) throws IOException {
        String fullFileName = encodeFile.getFileName().toString();
        String directoryPath = encodeFile.toString().replaceFirst(fullFileName, "");
        String fileExtension = fullFileName.substring(fullFileName.lastIndexOf("."), fullFileName.length());
        String fileName = fullFileName.replaceFirst(fileExtension, "")
                                        .replaceFirst("_encode", "");

        try (InputStream input = new FileInputStream(encodeFile.toString());
            OutputStream output = new FileOutputStream(directoryPath + fileName + "_decode" + fileExtension, true)) {

            encodeDecodeByKey(key, input, output);
        }
    }

    /**
     * Функция выполняет операцию XOR для входящего потока и ключа в байтовом представлении,
     * затем пишет результат в выходящий поток
     * @param key - ключ для выполнения XOR
     * @param input - входящий поток данных
     * @param output - выходящий поток данных
     * @throws IOException
     */
    private static void encodeDecodeByKey(String key, InputStream input, OutputStream output) throws IOException{

        byte[] buffer = new byte[1024];
        byte[] keyBytes = key.getBytes();
        int length;
        while ((length = input.read(buffer)) > 0) {
            for (int i = 0; i < length; i++) {
                buffer[i] ^= keyBytes[i % keyBytes.length];
            }
            output.write(buffer, 0, length);
        }
    }

    /**
     * Функция выполняет операцию XOR для двух входящих потоков,
     * затем пишет результат в выходящий поток
     * @param srcInput - первый входящий поток для XOR
     * @param keyInput - второй входящий поток для XOR
     * @param output - выходящий поток данных
     * @throws IOException
     */
    private static void encodeDecodeByFile(InputStream srcInput, InputStream keyInput, OutputStream output) throws IOException{
        int length;
        byte[] srcBuffer = new byte[1024];
        byte[] keyBuffer = new byte[1024];

        while ((length = srcInput.read(srcBuffer)) > 0) {
            keyInput.read(keyBuffer);
            for (int i = 0; i < length; i++) {
                srcBuffer[i] ^= keyBuffer[i % keyBuffer.length];
            }
            output.write(srcBuffer, 0, length);
        }
    }
}
