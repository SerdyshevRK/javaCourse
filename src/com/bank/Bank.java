package com.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Bank {

    public TxResult transferMoney(Account src, Account dest, int amount) {
        if (src.getBalance() < amount) {
            return TxResult.NOT_ENOUGH;
        }

        if (amount < 1) {
            return TxResult.NOT_VALID_AMOUNT;
        }

        src.changeBalance(amount, false); // take money
        dest.changeBalance(amount, true); // add money

        return TxResult.SUCCESS;
    }

    public static void main(String[] args) {
        Thread thread;
        Bank bank = new Bank();
        User user1 = new User("Tom");
        User user2 = new User("Jerry");
        final Account account1 = new Account(50000, user1.getId());
        final Account account2 = new Account(50000, user2.getId());
        final Account account3 = new Account(50000, user1.getId());
        final Account account4 = new Account(50000, user2.getId());

        List<Account> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);
        accounts.add(account3);
        accounts.add(account4);

        FutureTask<TxResult> task1 = new FutureTask<TxResult>(bank.new BankThread(account1, account2, 100));
        FutureTask<TxResult> task2 = new FutureTask<TxResult>(bank.new BankThread(account2, account1, 1000));
        FutureTask<TxResult> task3 = new FutureTask<TxResult>(bank.new BankThread(account2, account4, 1000));
        FutureTask<TxResult> task4 = new FutureTask<TxResult>(bank.new BankThread(account4, account1, 1000));
        FutureTask<TxResult> task5 = new FutureTask<TxResult>(bank.new BankThread(account3, account4, 1000));

        List<FutureTask<TxResult>> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);
        tasks.add(task5);

        for (FutureTask<TxResult> task : tasks) {
            thread = new Thread(task);
            thread.start();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            System.out.println(task1.get());
            System.out.println(task2.get());
            System.out.println(task3.get());
            System.out.println(task4.get());
            System.out.println(task5.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (Account account : accounts) {
            System.out.println(account.getAccountId() + " " + account.getBalance());
        }
    }

    class BankThread implements Callable {
        Account src;
        Account dest;
        int amount;

        public BankThread(Account src, Account dest, int amount) {
            this.src = src;
            this.dest = dest;
            this.amount = amount;
        }

        @Override
        public TxResult call() {
            synchronized (src) {
                synchronized (dest) {
                    return transferMoney(src, dest, amount);
                }
            }
        }
    }
}
