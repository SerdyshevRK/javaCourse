package com.multithreading.eatery;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class EateryMain {
    BlockingQueue<String> waiterOrder = new ArrayBlockingQueue<>(5);
    BlockingQueue<String> cookOrder = new ArrayBlockingQueue<>(5);
    BlockingQueue<String> dishes = new ArrayBlockingQueue<>(5);

    public static void main(String[] args) {
        EateryMain eatery = new EateryMain();

        Thread cookThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("cook");
                String order;
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        order = eatery.cookOrder.take();
                        System.out.println(order + " was cooked.");
                        eatery.dishes.put(order);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println(Thread.currentThread().getName() + " interrupted");
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread waiterThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("waiter");
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        eatery.cookOrder.put(eatery.waiterOrder.take());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println();
                        System.out.println(Thread.currentThread().getName() + " interrupted");
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread customerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("customer");
                String order;
                Scanner sc = new Scanner(System.in);
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        order = sc.nextLine();
                        if (order.equals("exit")) {
                            waiterThread.interrupt();
                            cookThread.interrupt();
                            Thread.currentThread().interrupt();
                            System.out.println(Thread.currentThread().getName() + " interrupted");
                            continue;
                        }

                        eatery.waiterOrder.put(order);
                        System.out.println("I'm eating " + eatery.dishes.take() + ".");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }
            }
        });

        cookThread.start();
        waiterThread.start();
        customerThread.start();
    }
}
