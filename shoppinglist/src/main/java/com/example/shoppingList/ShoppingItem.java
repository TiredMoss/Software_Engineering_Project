package com.example.shoppingList;
public class ShoppingItem {
    private long id;
    private String name;
    private int quantity;
    private boolean bought;

    public ShoppingItem(long id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.bought = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }
}
