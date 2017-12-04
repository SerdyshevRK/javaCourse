package com.shop;

public class Product {
    private String title;
    private String description;
    private double price;

    public Product(String title, String description, double price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Product))
            return false;
        if (!((Product) obj).getTitle().equals(this.title))
            return false;
        if (!((Product) obj).getDescription().equals(this.description))
            return false;
        if (((Product) obj).getPrice() != this.price)
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product:")
                .append("\n" + title)
                .append("\n" + description)
                .append("\nprice = " + price);
        return sb.toString();
    }
}
