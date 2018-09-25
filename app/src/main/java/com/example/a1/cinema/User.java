package com.example.a1.cinema;

public class User
{
       // private int id;
        private String name,email,password;

    public User(String name, String email, String password) {
        //this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

//    public int getId() {
//        return id;
//    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
