package com.multithreading.eatery;

import java.util.Scanner;

public class EateryMain {
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
                            cook.prepare();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    synchronized (waiter) {
                        waiter.notify();
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
                            synchronized (cook) {
                                cook.setOrder(waiter.getOrder());
                                cook.notify();
                            }
                            waiter.wait();
                            waiter.deliver();
                            synchronized (customer) {
                                customer.notify();
                            }
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        });

        Thread customerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("customer");
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("What exactly I want? Hmm...");
                    customer.setOrder(sc.nextLine());
                    synchronized (waiter) {
                        waiter.setOrder(customer.getOrder());
                        waiter.notify();
                    }
                    synchronized (customer) {
                        try {
                            customer.wait();
                            customer.eating();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
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
