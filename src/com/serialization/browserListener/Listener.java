package com.serialization.browserListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener {
    private static final int port = 12345;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept();
                byte[] buf = new byte[1024];

                try (InputStream in = socket.getInputStream()) {
                    while (in.read(buf) > 0) {
                        System.out.println(new String(buf));
                    }
                }
            }
        }
    }
}
