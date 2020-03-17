package com.example.smtrick.smartnanded.Models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Belal on 2/23/2017.
 */
@IgnoreExtraProperties
public class Products implements Serializable {


    public String productDescription;
    public String productName;
    public String productId;
    public String productCategory;
    public String productPrice;
    public String url;
    public  String totalPrice;
    private ArrayList<String> subImages;
    public String carKM;
    public String carBrand;
    public String carModel;
    public String carYear;
    public String carFuel;
    private String pName;
    private BigDecimal pPrice;


    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Products() {
    }

    public Products(String desc, String name, String url,String id,String category,String price,String totalPrice) {

        this.productDescription = desc;
        this.productName = name;
        this.productId = id;
        this.productCategory = category;
        this.productPrice = price;
        this.url = url;
        this.totalPrice = totalPrice;
        this.subImages = new ArrayList<String>();
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

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ArrayList<String> getSubImages() {
        return subImages;
    }

    public void setSubImages(ArrayList<String> subImages) {
        this.subImages = subImages;
    }

}