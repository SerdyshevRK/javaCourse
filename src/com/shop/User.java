package com.shop;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private String password;
    private Map<Product, Integer> cart;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        cart = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void addToCart(Product product, int quantity) {
        if (cart.containsKey(product)) {
            cart.put(product, cart.get(product) + quantity);
            return;
        }
        cart.put(product, quantity);
    }

    public Product getProdctFromCart(String title) {
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            if (entry.getKey().getTitle().equals(title))
                return entry.getKey();
        }
        return null;
    }

    public void removeFromCart(Product product) {
        if (cart.containsKey(product)) {
            cart.remove(product);
            return;
        }
        System.out.println("There is no product in cart, like this one.");
    }

    public void showProductsInCart() {
        System.out.println("Products in cart:\n");
        for (Map.Entry<Product, Integer> entry : cart.entrySet()){
            System.out.println(entry.getKey() + "\nquantity = " + entry.getValue());
            System.out.println();
        }

    }

    public void doTransaction() {
        int totalPrice = 0;
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            totalPrice += entry.getValue() * entry.getKey().getPrice();
        }
        System.out.println("Total price is: " + totalPrice);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User))
            return false;
        if (!((User) obj).name.equals(this.name))
            return false;
        if (!((User) obj).password.equals(this.password))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User:")
                .append("\nname = " + name)
                .append("\npassword = " + password);
        return sb.toString();
    }
}
