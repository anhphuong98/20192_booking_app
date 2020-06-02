package com.example.booking_app.models.order;

import com.google.gson.annotations.SerializedName;

// id: {
//         type: DataTypes.INTEGER,
//         primaryKey:true,
//         autoIncrement: true
//         },
//         store_id: {
//         type: DataTypes.INTEGER,
//         allowNull: false
//         },
//         category_id: {
//         type: DataTypes.INTEGER,
//         allowNull: false
//         },
//         url_image: {
//         type: DataTypes.STRING,
//         },
//         price: {
//         type: DataTypes.DOUBLE,
//         allowNull: false
//         },
//         sale: {
//         type: DataTypes.DOUBLE,
//         },
//         name: {
//         type: DataTypes.STRING,
//         allowNull: false
public class Dish {
    @SerializedName("id")
    int id;
    @SerializedName("store_id")
    int store_id;
    @SerializedName("category_id")
    int category_id;
    @SerializedName("url_image")
    String url_image;
    @SerializedName("price")
    double price;
    @SerializedName("sale")
    double sale;
    @SerializedName("name")
    String name;

    public Dish(){}

    public Dish(int id, int store_id, int category_id, String url_image, double price, double sale, String name) {
        this.id = id;
        this.store_id = store_id;
        this.category_id = category_id;
        this.url_image = url_image;
        this.price = price;
        this.sale = sale;
        this.name = name;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public int getStore_id() {return store_id;}

    public void setStore_id(int store_id) {this.store_id = store_id;}

    public int getCategory_id() {return category_id; }

    public void setCategory_id(int category_id) { this.category_id = category_id;  }

    public String getUrl_image() { return url_image;  }

    public void setUrl_image(String url_image) {this.url_image = url_image; }

    public double getPrice() {  return price;}

    public void setPrice(double price) {this.price = price; }

    public double getSale() { return sale; }

    public void setSale(double sale) {this.sale = sale; }

    public String getName() {return name; }

    public void setName(String name) {this.name = name; }
}
