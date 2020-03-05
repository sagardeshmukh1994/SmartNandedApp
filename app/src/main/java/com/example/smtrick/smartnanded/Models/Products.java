package com.example.smtrick.smartnanded.Models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Belal on 2/23/2017.
 */
@IgnoreExtraProperties
public class Products {


    public String desc;
    public String name;
    public String url;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Products() {
    }

    public Products(String desc, String name, String url) {

        this.desc = desc;
        this.name = name;
        this.url = url;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}