package com.example.mydiary;

public class Users {

    private String name, phone, password, city, Age;

    public Users() {
    }

    public Users(String name, String phone, String password, String city, String age) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.city = city;
        Age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }
}

