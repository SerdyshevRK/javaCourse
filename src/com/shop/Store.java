package com.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {
    private Map<Product, Integer> products;
    private List<User> users;

    private class StoreEntry<K, V> implements Map.Entry<K, V> {
        K key;
        V value;

        public StoreEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            return null;
        }

        @Override
        public String toString() {
            return key + " = " + value;
        }
    }

    public Store() {
        this.products = new HashMap<>();
        this.users = new ArrayList<>();
    }

    public void addProduct(Product product, int quantity) {
        if (products.containsKey(product)) {
            products.put(product, products.get(product) + quantity);
            return;
        }
        products.put(product, quantity);
    }

    public Map.Entry<Product, Integer> getProduct(String title) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (entry.getKey().getTitle().equals(title))
                return entry;
        }
        return null;
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public Map.Entry<Product, Integer> takeProduct(String title, int quantity) {
        Map.Entry<Product, Integer> entry = null;
        entry = getProduct(title);
        if (entry.getValue() <= quantity) {
            products.remove(entry.getKey());
            return entry;
        }
        StoreEntry<Product, Integer> sEntry = new StoreEntry<>(entry.getKey(), quantity);
        addProduct(entry.getKey(), -quantity);
        return sEntry;
    }

    public void showAllProducts() {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            System.out.println(entry.getKey() + "\nquantity = " + entry.getValue());
            System.out.println();
        }
    }

    public void addUser(User user) {
        if (users.contains(user)) {
            System.out.println("User already exist.");
            return;
        }
        users.add(user);
    }

    public User getUser(User user) {
        if (users.contains(user))
            return users.get(users.indexOf(user));
        System.out.println("User do not exist.");
        return null;
    }

    public void removeUser(User user) {
        if (!users.contains(user)) {
            System.out.println("User is not registered.");
            return;
        }
        users.remove(user);
    }
}
