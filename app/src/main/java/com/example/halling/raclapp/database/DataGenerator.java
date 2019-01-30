package com.example.halling.raclapp.database;

import com.example.halling.raclapp.database.entity.User;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

    private static final String[] userarray = new String[]{"admin", "customer", "waitress"};
    private static final String[] passwordarray = new String[]{"admin", "customer", "waitress"};

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
}
