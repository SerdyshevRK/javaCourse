package com.serialization.print;

import com.patterns.decorator.CryptoInputStream;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xmitya on 28.08.16.
 */
public class PrintServer {

    private int port;
    private Map<String, String> connections = new HashMap<>();

    private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");

    public PrintServer(int port) {
        this.port = port;
    }

    private void start() throws IOException {
        try (ServerSocket ssocket = new ServerSocket(port)) {
            System.out.println("Server started on " + ssocket);

            while (true) {
                Socket sock = ssocket.accept();

                try {
                    process(sock);
                }
                catch (ClassNotFoundException e) {
                    System.err.println("Wrong message was received");

                    e.printStackTrace();
                }
                finally {
                    sock.close();
                }
            }
        }
    }

    private void process(Socket sock) throws IOException, ClassNotFoundException {
        String host = sock.getInetAddress().getHostAddress();

        try (ObjectInputStream objIn = new ObjectInputStream(sock.getInputStream());
             OutputStream out = sock.getOutputStream()) {
            ObjectOutputStream objOut = new ObjectOutputStream(out);

            Object obj = objIn.readObject();

            if (obj instanceof Message) {
                connections.put(host, ((Message) obj).getSender());
                printMessage((Message) obj, host);
            } else {
                connections.put(host, ((Command) obj).getSender());
                executeCommand((Command) obj, objOut, objIn);
            }
        }
        catch (IOException | ClassNotFoundException | RuntimeException e) {
            System.err.println("Failed process connection from: " + host);

            e.printStackTrace();

            throw e;
        }
    }

    private void printMessage(Message msg, String senderAddr) {
        System.out.printf("%s (%s) at %s wrote: %s\n", msg.getSender(), senderAddr, format.format(new Date(msg.getTimestamp())), msg.getText());
    }

    private void executeCommand(Command cmd, ObjectOutputStream out, ObjectInputStream in) throws IOException, ClassNotFoundException {
        if (cmd.isListOfUsers()) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : connections.entrySet()) {
                sb.append(entry.getKey()).append(" - ").append(entry.getValue()).append(" ");
            }
            System.out.println(sb.toString());
            return;
        }
        if (cmd.isServerTime()) {
            System.out.println("Server time is: " + format.format(new Date()));
            return;
        }
        if (cmd.isPing()) {
            for (int i = 0; i < 3; i++) {
                out.writeObject(cmd);
                cmd = (Command) in.readObject();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        if (args == null || args.length == 0)
            throw new IllegalArgumentException("Port must be specified");

        int port = Integer.parseInt(args[0]);

        PrintServer printServer = new PrintServer(port);

        printServer.start();
    }
}
