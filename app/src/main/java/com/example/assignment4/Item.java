package com.example.assignment4;

public class Item {
    String title;
    String manufacturer;
    String category;
    String price;
    int stock;

    public Item(){
        this.title = null;
        this.manufacturer = null;
        this.category = null;
        this.price = null;
        this.stock = 0;
    }

    public Item(String title, String manufacturer, String category, String price, int stock){
        this.title = title;
        this.manufacturer = manufacturer;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
