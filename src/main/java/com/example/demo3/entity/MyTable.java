package com.example.demo3.entity;

/**
 * @author Peko.Lai
 */
public class MyTable {
    private String id;
    private String name;
    private String phone;
    private String date;
    private String status;
    private String userImg;

    public String getUser_img() {
        return userImg;
    }

    public void setUser_img(String user_img) {
        this.userImg = user_img;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyTableDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                ", user_img='" + userImg + '\'' +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
