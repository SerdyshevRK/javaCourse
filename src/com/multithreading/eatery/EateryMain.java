package com.multithreading.eatery;

import java.util.Scanner;

public class EateryMain {
    enum Direction {
        COOK, CUSTOMER
    }
    private static boolean isAllowed;
    private static Direction direction = Direction.CUSTOMER;

    public static void main(String[] args) {
        Customer customer = new Customer();
        Waiter waiter = new Waiter();
        Cook cook = new Cook();
        Scanner sc = new Scanner(System.in);

        Thread cookThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("cook");
                while (!Thread.currentThread().isInterrupted()) {
                    synchronized (cook) {
                        try {
                            cook.wait();
                            if (!isAllowed)
                                continue;
                            isAllowed = false;
                            cook.prepare();

                            synchronized (waiter) {
                                direction = Direction.CUSTOMER;
                                isAllowed = true;
                                waiter.notify();
                            }
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.out.println(Thread.currentThread().getName() + " closing...");
                        }
                    }
                }
            }
        });

        Thread waiterThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("waiter");
                while (!Thread.currentThread().isInterrupted()) {
                    synchronized (waiter) {
                        try {
                            waiter.wait();
                            if (!isAllowed)
                                continue;
                            isAllowed = false;
                            if (direction == Direction.COOK) {
                                synchronized (cook) {
                                    cook.setOrder(waiter.getOrder());
                                    isAllowed = true;
                                    cook.notify();
                                }
                                continue;
                            }
                            if (direction == Direction.CUSTOMER) {
                                waiter.deliver();
                                synchronized (customer) {
                                    isAllowed = true;
                                    customer.notify();
                                }
                            }
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.out.println(Thread.currentThread().getName() + " closing...");
                        }
                    }
                }
            }
        });

        Thread customerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String order;
                Thread.currentThread().setName("customer");
                while (!Thread.currentThread().isInterrupted()) {
                    if (direction == Direction.CUSTOMER) {
                        System.out.println("What exactly I want? Hmm...");
                        order = sc.nextLine();
                        if (order.equals("exit")) {
                            waiterThread.interrupt();
                            cookThread.interrupt();
                            Thread.currentThread().interrupt();
                        }
                        customer.setOrder(order);
                        synchronized (waiter) {
                            waiter.setOrder(customer.getOrder());
                            direction = Direction.COOK;
                            isAllowed = true;
                            waiter.notify();
                        }
                    }
                    synchronized (customer) {
                        try {
                            customer.wait();
                            if (!isAllowed)
                                continue;
                            isAllowed = false;
                            customer.eating();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.out.println(Thread.currentThread().getName() + " closing...");
                        }
                    }
                }
            }
        });

        cookThread.start();
        waiterThread.start();
        customerThread.start();
    }
}
