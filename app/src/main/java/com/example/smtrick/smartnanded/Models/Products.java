package com.example.smtrick.smartnanded.Models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Belal on 2/23/2017.
 */
@IgnoreExtraProperties
public class Products {


    public String productDescription;
    public String productName;
    public String productId;
    public String url;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Products() {
    }

    public Products(String desc, String name, String url,String id) {

        this.productDescription = desc;
        this.productName = name;
        this.productId = id;
        this.url = url;
    }


    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}