package com.example.nguyenthimanduy_2123110470;

public class CartItem {
    private String name;
    private String price;       
    private int imageResId;
    private int quantity;

    // Constructor
    public CartItem(String name, String price, int imageResId, int quantity) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.quantity = quantity;
    }

    // Getter
    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setter
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Gợi ý thêm: Setter cho price
    public void setPrice(String price) {
        this.price = price;
    }
}
