package com.example.booking_app.models.user;

import com.google.gson.annotations.SerializedName;

public class DataUser {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("phone")
    private int phone;
    @SerializedName("address")
    private String address;
    @SerializedName("url")
    private String url;

    public DataUser(){}

    public int getId() {  return id; }

    public void setId(int id) { this.id = id;}

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public int getPhone() { return phone; }

    public void setPhone(int phone) { this.phone = phone; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }
}
