package com.example.baocaocuoiky_vothanhhai.model;

import java.io.Serializable;

public class DetailFood implements Serializable {

    public String idMon;
    public String imageURL;
    public String name;
    public String description;
    public String price;
    public String rating;

    public DetailFood(){
    }



    public DetailFood(String idMon, String imageURL, String name, String description, String price, String rating) {
        this.idMon = idMon;
        this.imageURL = imageURL;
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
    }
}
