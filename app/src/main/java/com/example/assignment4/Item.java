package com.example.assignment4;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
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

    protected Item(Parcel in) {
        title = in.readString();
        manufacturer = in.readString();
        category = in.readString();
        price = in.readString();
        stock = in.readInt();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(manufacturer);
        dest.writeString(category);
        dest.writeString(price);
        dest.writeInt(stock);
    }
}
