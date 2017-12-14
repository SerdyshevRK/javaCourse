package com.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Bank {
    private Map<Integer, User> users = new HashMap<>();
    private BlockingQueue<String> messages = new ArrayBlockingQueue<>(5);

    public static void main(String[] args) {
        Thread thread;
        Bank bank = new Bank();
        User user1 = new User("Tom");
        User user2 = new User("Jerry");
        bank.users.put(user1.getId(), user1);
        bank.users.put(user2.getId(), user2);
        List<Account> accounts = new ArrayList<>();

        for (User user : bank.users.values()) {
            accounts.add(new Account(10000, user.getId()));
        }

        Mailer mailer = bank.new Mailer();
        mailer.start();

        try {
            thread = new Thread(bank.new BankThread(accounts.get(0), accounts.get(1), 100000));
            thread.start();
            thread.join();
            thread = new Thread(bank.new BankThread(accounts.get(1), accounts.get(0), 1000));
            thread.start();
            thread.join();
            thread = new Thread(bank.new BankThread(accounts.get(0), accounts.get(0), 1000));
            thread.start();
            thread.join();
            thread = new Thread(bank.new BankThread(accounts.get(0), accounts.get(1), -1000));
            thread.start();
            thread.join();
            thread = new Thread(bank.new BankThread(accounts.get(0), accounts.get(1), 2000));
            thread.start();
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Account account : accounts) {
            System.out.println(bank.users.get(account.getUserID()).getName() + ": " + account.getBalance());
        }
        mailer.interrupt();
    }

    private TxResult transferMoney(Account src, Account dest, int amount) throws InterruptedException {
        TxResult result;
        String msg;

        if (src == dest) {
            result = TxResult.NOT_VALID_DESTINATION;
            msg = src.getUserID() + ":Не удалось выполнить операцию, проверьте корректность данных.";
            messages.put(msg);
            return result;
        }

        if (amount < 1) {
            result = TxResult.NOT_VALID_AMOUNT;
            msg = src.getUserID() + ":Некорректная сумма для перевода.";
            messages.put(msg);
            return result;
        }

        if (src.getBalance() < amount) {
            result = TxResult.NOT_ENOUGH;
            msg = src.getUserID() + ":На вашем счете не хватает средств.";
            messages.put(msg);
            return result;
        }

        if (src.getAccountId() < dest.getAccountId()) {
            synchronized (src) {
                synchronized (dest) {
                    doTransfer(src, dest, amount);
                }
            }
        } else {
            synchronized (dest) {
                synchronized (src) {
                    doTransfer(src, dest, amount);
                }
            }
        }

        result = TxResult.SUCCESS;
        msg = src.getUserID() + ":С вашего счета переведена сумма в размере "
                + amount + " на счет пользователя " + users.get(dest.getUserID()).getName() + ".";
        messages.put(msg);
        msg = dest.getUserID() + ":На ваш счет поступили средства в размере "
                + amount + " от пользователя " + users.get(src.getUserID()).getName() + ".";
        messages.put(msg);
        return result;
    }

    private void doTransfer(Account src, Account dest, int amount) {
        src.changeBalance(amount, false); // take money
        dest.changeBalance(amount, true); // add money
    }

    private void sendMessage(int userID, String message) {
        users.get(userID).printMessage(message);
    }

    private class BankThread implements Runnable {
        Account src;
        Account dest;
        int amount;

        public BankThread(Account src, Account dest, int amount) {
            this.src = src;
            this.dest = dest;
            this.amount = amount;
        }

        @Override
        public void run() {
            try {
                System.out.println(transferMoney(src, dest, amount));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class Mailer extends Thread {
        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    String[] msg = messages.take().split(":");
                    sendMessage(Integer.parseInt(msg[0]), msg[1]);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
