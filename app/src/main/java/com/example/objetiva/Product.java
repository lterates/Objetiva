package com.example.objetiva;

public class Product {
    private String name;
    private String ImageUrl;
    private String description;
    private String price;

    public Product(String name, String imageUrl, String description, String price) {
        this.name = name;
        ImageUrl = imageUrl;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", ImageUrl='" + ImageUrl + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
