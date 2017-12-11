package com.fileServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileServer {
    private int port;

    public FileServer(int port) {
        this.port = port;
    }

    private void start() throws IOException {
        Thread thread;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started: " + serverSocket);
            while (true) {
                Socket socket = serverSocket.accept();
                thread = new Thread(new Runner(socket));
                thread.start();
            }
        }
    }

    private void saveFile(Socket socket) throws IOException {
        Path directory = Paths.get("E:\\ITMO\\Learning\\Practice\\files");
        String fileName;
        Long fileLength, count = 0L;
        int length;
        byte[] buffer = new byte[1024];

        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            //читаем дескриптор файла: имя, длина
            //читаем сам файл в соответствии с дескриптором
            fileName = in.readUTF();
            fileLength = in.readLong();
            FileOutputStream out = new FileOutputStream(directory.toString() + "\\" + fileName);

            while (count < fileLength) {
                // пишем файл на диск
                length = in.read(buffer);
                count += length;
                out.write(buffer, 0, length);
                out.flush();
            }
            out.close();
        }
    }

    public static void main(String[] args) {
        int port = 12345;
        FileServer server = new FileServer(port);
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Runner implements Runnable {
        Socket socket;

        public Runner(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName());
                saveFile(socket);
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
