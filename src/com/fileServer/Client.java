package com.fileServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Client {
    private SocketAddress serverAddress;

    public Client(SocketAddress serverAddress) {
        this.serverAddress = serverAddress;
    }

    private void start() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите путь к файлу для зарузки ('exit' - чтобы завершить работу):");
        String path = sc.nextLine();
        if ("/exit".equals(path))
            System.exit(0);

        Path pathToFile = Paths.get(path);
        sendFile(pathToFile);
    }

    private void sendFile(Path pathToFile) throws IOException {
        Socket socket = new Socket();
        socket.connect(serverAddress);
        File file = new File(pathToFile.toString());
        if (file.exists()) {
            String fileName = file.getName();
            Long fileLength = file.length();
            byte[] buffer = new byte[1024];
            int length;

            try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
                FileInputStream in = new FileInputStream(pathToFile.toString());
                out.writeUTF(fileName);
                out.writeLong(fileLength);

                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                    out.flush();
                }
                in.close();
            }
        } else {
            System.out.println("Не удается найти файл.");
        }
    }

    public static void main(String[] args) {
        Client client = new Client(new InetSocketAddress("localhost", 12345));

        while (true) {
            try {
                client.start();
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }
}
