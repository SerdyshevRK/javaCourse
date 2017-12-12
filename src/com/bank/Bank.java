package com.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private Map<Integer, User> users = new HashMap<>();

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
    }

    private TxResult transferMoney(Account src, Account dest, int amount) throws InterruptedException {
        TxResult result;
        Thread thread;

        if (src == dest) {
            result = TxResult.NOT_VALID_DESTINATION;
            thread = new Thread(new Mailer(src, dest, amount, result));
            thread.start();
            return result;
        }

        if (amount < 1) {
            result = TxResult.NOT_VALID_AMOUNT;
            thread = new Thread(new Mailer(src, dest, amount, result));
            thread.start();
            return result;
        }

        if (src.getBalance() < amount) {
            result = TxResult.NOT_ENOUGH;
            thread = new Thread(new Mailer(src, dest, amount, result));
            thread.start();
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
        thread = new Thread(new Mailer(src, dest, amount, result));
        thread.start();
        return result;
    }

    private void doTransfer(Account src, Account dest, int amount) {
        src.changeBalance(amount, false); // take money
        dest.changeBalance(amount, true); // add money
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

    private class Mailer implements Runnable {  // забыл, кому и что он должен писать...
        Account src;
        Account dest;
        int amount;
        TxResult result;

        public Mailer(Account src, Account dest, int amount, TxResult result) {
            this.src = src;
            this.dest = dest;
            this.amount = amount;
            this.result = result;
        }

        @Override
        public void run() {
            switch (result) {
                case NOT_VALID_AMOUNT:
                    System.out.println("Некорректная сумма для перевода.");
                    break;
                case NOT_ENOUGH:
                    System.out.println("На счете " + users.get(src.getUserID()).getName() + " не хватает средств.");
                    break;
                case NOT_VALID_DESTINATION:
                    System.out.println("Не удалось выполнить операцию, проверьте корректность введенных данных.");
                    break;
                case SUCCESS:
                    System.out.println("Со счета " + users.get(src.getUserID()).getName()
                            + " переведены средства в размере " + amount
                            + " на счет " + users.get(dest.getUserID()).getName());
                default:
                    System.out.println("Паника! Что-то пошло не так!");
            }
        }
    }
}
