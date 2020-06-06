package com.example.booking_app.models.dish;

import com.google.gson.annotations.Expose;
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
public class StoreDish {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("store_id")
    @Expose
    private Integer storeId;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("url_image")
    @Expose
    private String urlImage;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("sale")
    @Expose
    private Integer sale;
    @SerializedName("name")
    @Expose
    private String name;

    public StoreDish(){}

    public StoreDish(Integer id, Integer storeId, Integer categoryId, String urlImage, Double price, Integer sale, String name) {
        this.id = id;
        this.storeId = storeId;
        this.categoryId = categoryId;
        this.urlImage = urlImage;
        this.price = price;
        this.sale = sale;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
