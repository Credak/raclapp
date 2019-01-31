package com.example.halling.raclapp.database;

import com.example.halling.raclapp.database.entity.Product;
import com.example.halling.raclapp.database.entity.User;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

    private static final String[] userarray = new String[]{"admin", "customer", "waitress"};
    private static final String[] passwordarray = new String[]{"admin", "customer", "waitress"};
    private static final String[] productnamearray = new String[]{"testprodukt1", "testprodukt2" , "testprodukt3"};
    private static final int[] productquantityarray = new int[]{1,2,3};


    public static List<User> generateUser(){
        List<User> userList = new ArrayList<>(userarray.length);
        for (int i = 0; i < userarray.length; i++) {
                User user = new User();
                user.setUsername(userarray[i]);
                user.setPassword(passwordarray[i]);
                user.setId(userarray.length * i + 1);
                userList.add(user);
        }
        return userList;
    }
    public static List<Product> generateProducts(){
        List<Product> productList = new ArrayList<>(productnamearray.length);
        for(int i = 0; i<productnamearray.length; i++){
            Product product = new Product();
            product.setProductid(i);
            product.setProductname(productnamearray[i]);
            product.setQuantity(productquantityarray[i]);
            productList.add(product);
        }
        return productList;
    }
}
