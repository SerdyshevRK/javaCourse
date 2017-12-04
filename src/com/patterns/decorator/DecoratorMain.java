package com.patterns.decorator;

import java.io.*;

public class DecoratorMain {
    public static void main(String[] args) throws Exception{
        final String inPath = "E:\\ITMO\\Learning\\Practice\\wp\\objectTest.txt";

        Test testObj = new Test(1, 1.0d, "some string", true);

        try (ObjectOutputStream out = new ObjectOutputStream(new CryptoOutputStream(new FileOutputStream(inPath), "secret".getBytes()))) {
            out.writeObject(testObj);
        }

        try (ObjectInputStream in = new ObjectInputStream(new CryptoInputStream(new FileInputStream(inPath), "secret".getBytes()))) {
            testObj = (Test) in.readObject();
            System.out.println(testObj);
        }
    }
}

