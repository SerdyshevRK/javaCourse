package com.shop;

import java.util.Scanner;

/**
 * Usability отсутствует полностью, будьте внимательны!
 */
public class ShopMain {
    public static void main(String[] args) {
        Store store = new Store();
        store.addProduct(new Product("title_0", "description_0", 10.0), 20);
        store.addProduct(new Product("title_1", "description_1", 10.0), 20);
        store.addProduct(new Product("title_2", "description_2", 10.0), 20);
        store.addProduct(new Product("title_3", "description_3", 10.0), 20);
        store.addProduct(new Product("title_4", "description_4", 10.0), 20);
        store.addProduct(new Product("title_5", "description_5", 10.0), 20);
        store.addProduct(new Product("title_6", "description_6", 10.0), 20);

        store.addUser(new User("first", "fPass"));
        store.addUser(new User("second", "sPass"));

        Scanner sc = new Scanner(System.in);
        User user = null;
        String userData;

        while (true) {
            if (user == null) {
                System.out.println("Login or register. (type '/help' to show commands)");
            }
            switch (sc.nextLine()) {
                case "/exit":
                    return;
                case "/help":
                    System.out.println("'/exit' - to exit\n"
                            + "'/login' - to login\n"
                            + "'/register' - to register\n"
                            + "'/show products' - to show products is store\n"
                            + "'/add to cart' - to add product to cart\n"
                            + "'/remove from cart' - to remove product from cart\n"
                            + "'/show cart' - to show products in cart\n"
                            + "'/do transaction' - to show total price of products in cart\n");
                    break;
                case "/login":
                    System.out.println("Enter name and password divided by 'space':");
                    userData = sc.nextLine();
                    if (userData != null) {
                        user = store.getUser(new User(userData.split(" ")[0], userData.split(" ")[1]));
                    }
                    break;
                case "/register":
                    System.out.println("Enter name and password divided by 'space':");
                    userData = sc.nextLine();
                    if (userData != null) {
                        user = new User(userData.split(" ")[0], userData.split(" ")[1]);
                        store.addUser(user);
                    }
                    break;
                case "/show products":
                    if (user == null)
                        break;
                    store.showAllProducts();
                    break;
                case "/add to cart":
                    if (user == null)
                        break;
                    System.out.println("Enter title of product and quantity:");
                    userData = sc.nextLine();
                    user.addToCart(store.takeProduct(userData.split(" ")[0], Integer.valueOf(userData.split(" ")[1])).getKey(),
                            Integer.valueOf(userData.split(" ")[1]));
                    break;
                case "/show cart":
                    if (user == null)
                        break;
                    user.showProductsInCart();
                    break;
                case "/remove from cart":
                    System.out.println("Enter title of product:");
                    userData = sc.nextLine();
                    if (userData != null) {
                        user.removeFromCart(user.getProdctFromCart(userData));
                    }
                    break;
                case "/do transaction":
                    user.doTransaction();
                    break;
            }
        }
    }
}
