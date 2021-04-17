package com.example.assignment4;

public class Purchase {
    String customerName;
    String item;
    int quantity;

    public Purchase(){
        this.customerName = null;
        this.item = null;
        this.quantity = 0;
    }

    public Purchase(String customerName, String item, int quantity){
        this.customerName = customerName;
        this.item = item;
        this.quantity = quantity;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
