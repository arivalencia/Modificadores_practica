package com.example.dvoladaaliado.pojo;

public class Option {
    String title;
    Double price;

    public Option(String title, Double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }
}
