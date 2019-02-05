package com.example.productlist;

public class Product {

    private String title;
    private String desc;
    private int image;
    private int largeImage;

    public Product(String title, String desc, int image, int largeImage) {
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.largeImage = largeImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(int largeImage) {
        this.largeImage = largeImage;
    }
}
