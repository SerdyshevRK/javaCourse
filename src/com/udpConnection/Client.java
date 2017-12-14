package com.udpConnection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Scanner;

public class Client {
    DatagramSocket socket;
    DatagramPacket packet;
    byte[] buffer;

    private void start() throws IOException {
        SocketAddress address = new InetSocketAddress("localhost", 12345);
        socket = new DatagramSocket();
        Scanner sc = new Scanner(System.in);
        buffer = sc.nextLine().getBytes();
        packet = new DatagramPacket(buffer, buffer.length, address);

        socket.send(packet);
        System.out.println("Packet sent...");
    }

    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
