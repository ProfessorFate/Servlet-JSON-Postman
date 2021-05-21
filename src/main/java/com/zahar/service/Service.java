package com.zahar.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zahar.data.JDBC;
import com.zahar.data.User;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Service {
    private final com.zahar.data.JDBC JDBC;
    private static final Gson GSON= new GsonBuilder().setPrettyPrinting().create();

    public Service(){
        JDBC = new JDBC();
    }

    public String getAllUsersForResponse(){
        return GSON.toJson(JDBC.getAllUsers());
    }

    public void addUser(String userJson){
        JDBC.addUser(GSON.fromJson(userJson,User.class));
    }

    public void deleteUser(String id){
        Pattern pattern=Pattern.compile("\\d*");
        Matcher matcher=pattern.matcher(id);
        if(matcher.matches()|| !id.trim().equals("")){
            int newId =Integer.parseInt(id);
            JDBC.deleteUserById(newId);
        }else{
            throw new NoSuchElementException("ID has an invalid format");
        }
    }
}
