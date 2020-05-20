package com.example.booking_app.models;

import com.google.gson.annotations.SerializedName;

//  id : {
//          type : DataTypes.INTEGER,
//          primaryKey : true,
//          autoIncrement : true
//          },
//          name : {
//          type : DataTypes.STRING,
//          allowNull : false
//          },
//          email : {
//          type : DataTypes.STRING,
//          allowNull : false
//          },
//          password : {
//          type : DataTypes.STRING,
//          allowNull : false
//          },
//          phone : {
//          type : DataTypes.BIGINT,
//          },
//          address : {
//          type : DataTypes.TEXT,
//          },
//          url_image : {
//          type : DataTypes.STRING
//          },
//          status : {
//          type : DataTypes.BOOLEAN,
//          defaultValue : 0
//          }
public class dataUser {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    private String email;
    private String password;
    private int phone;
    private String address;
    private String url;
}
