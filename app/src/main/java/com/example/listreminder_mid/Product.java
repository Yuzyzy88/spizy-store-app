package com.example.listreminder_mid;

public class Product {

    // initialize variables
    private String productId;
    private String name;
    private String price;
    private String product_type;

    public Product() {}

    public Product(String productId, String name, String price, String product_type) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.product_type = product_type;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }
}
