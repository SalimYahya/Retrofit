package com.example.retrofit.model.product;

public class Product {

    public String name;
    public String url;
    public String price;

    public Product() {
    }

    public Product(String name, String url, String price) {
        this.name = name;
        this.url = url;
        this.price = price;
    }
}
