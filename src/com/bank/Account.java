package com.bank;

public class Account {
    private static int currentID = 0;
    private int id;
    private int balance;
    private int userID;

    public Account(int balance, int userID) {
        this.balance = balance;
        this.userID = userID;
        this.id = currentID++;
    }

    public int getBalance() {
        return balance;
    }

    public void changeBalance(int amount, boolean addMoney) { // true - add money, false - take money
        if (addMoney) {
            balance += amount;
            return;
        }
        balance -= amount;
    }

    public int getAccountId(){
        return id;
    }

    public int getUserID() {
        return userID;
    }
}
