package com.udpConnection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;;

public class Server {
    private DatagramSocket socket;
    private byte[] buffer;
    private DatagramPacket packet;

    private void start() throws IOException {
        buffer = new byte[256];
        socket = new DatagramSocket(12345);
        packet = new DatagramPacket(buffer, buffer.length);

        while (true) {
            socket.receive(packet);
            System.out.println(new String(packet.getData()));
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
