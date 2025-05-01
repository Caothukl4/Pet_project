package com.example.petproject.model;

public class Cart {
    private int id;
    private String productName;
    private String productDescription;
    private int price;

    // Constructor không tham số (default)
    public Cart() {
    }

    // Constructor đầy đủ
    public Cart(int id, String productName, String productDescription, int price) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // Optional: toString (giúp debug)
    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", price=" + price +
                '}';
    }
}

