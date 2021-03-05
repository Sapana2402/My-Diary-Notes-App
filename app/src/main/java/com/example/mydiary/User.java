package com.example.mydiary;

public class User {
    String  subjectt,detilst;

    public User() {
    }

    public User(String subjectt, String detilst) {
        this.subjectt = subjectt;
        this.detilst = detilst;
    }

    public String getSubjectt() {
        return subjectt;
    }

    public String getDetilst() {
        return detilst;
    }

    public void setSubjectt(String subjectt) {
        this.subjectt = subjectt;
    }

    public void setDetilst(String detilst) {
        this.detilst = detilst;
    }
}
