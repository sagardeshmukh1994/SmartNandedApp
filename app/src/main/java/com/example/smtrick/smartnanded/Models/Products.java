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
    public String totalPrice;
    public String productQuantity;
    private ArrayList<String> subImages;
    public String carKM;
    public String carBrand;
    public String carModel;
    public String carYear;
    public String carFuel;
    public String bikeModel;
    public String bikeeYear;
    public String bikeKM;
    private String userId;

    private String jobSalaryPeriod;
    private String jobPosition;
    private String jobSalaryFrom;
    private String jobSalaryTo;

    private String propertyType;
    private String propertyBedrooms;
    private String propertyFurnishing;
    private String propertyConstructionStatus;
    private String propertyListedBy;
    private String propertyCarpetArea;
    private String propertyProjectName;
    private String propertyParkings;


    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Products() {
    }

    public Products(String desc, String name, String url, String id, String category, String price, String totalPrice, String userId,
                    String productQuantity, String carModel, String carYear, String carFuel, String carKM,
                    String bikeKM, String bikeModel, String bikeeYear, String jobSalaryPeriod, String jobPosition,
                    String jobSalaryFrom, String jobSalaryTo, String propertyType, String propertyBedrooms,
                    String propertyFurnishing, String propertyConstructionStatus, String propertyListedBy, String propertyCarpetArea,
                    String propertyProjectName, String propertyParkings) {

        this.productDescription = desc;
        this.productName = name;
        this.productId = id;
        this.productCategory = category;
        this.productPrice = price;
        this.url = url;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.productQuantity = productQuantity;
        this.carModel = carModel;
        this.carYear = carYear;
        this.carFuel = carFuel;
        this.carKM = carKM;
        this.bikeModel = bikeModel;
        this.bikeeYear = bikeeYear;
        this.bikeKM = bikeKM;
        this.jobSalaryPeriod = jobSalaryPeriod;
        this.jobPosition = jobPosition;
        this.jobSalaryFrom = jobSalaryFrom;
        this.jobSalaryTo = jobSalaryTo;
        this.propertyType = propertyType;
        this.propertyBedrooms = propertyBedrooms;
        this.propertyFurnishing = propertyFurnishing;
        this.propertyConstructionStatus = propertyConstructionStatus;
        this.propertyListedBy = propertyListedBy;
        this.propertyCarpetArea = propertyCarpetArea;
        this.propertyProjectName = propertyProjectName;
        this.propertyParkings = propertyParkings;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getCarKM() {
        return carKM;
    }

    public void setCarKM(String carKM) {
        this.carKM = carKM;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarYear() {
        return carYear;
    }

    public void setCarYear(String carYear) {
        this.carYear = carYear;
    }

    public String getCarFuel() {
        return carFuel;
    }

    public String getBikeModel() {
        return bikeModel;
    }

    public void setBikeModel(String bikeModel) {
        this.bikeModel = bikeModel;
    }

    public String getBikeeYear() {
        return bikeeYear;
    }

    public void setBikeeYear(String bikeeYear) {
        this.bikeeYear = bikeeYear;
    }

    public String getBikeKM() {
        return bikeKM;
    }

    public void setBikeKM(String bikeKM) {
        this.bikeKM = bikeKM;
    }

    public void setCarFuel(String carFuel) {
        this.carFuel = carFuel;
    }

    public String getJobSalaryPeriod() {
        return jobSalaryPeriod;
    }

    public void setJobSalaryPeriod(String jobSalaryPeriod) {
        this.jobSalaryPeriod = jobSalaryPeriod;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getJobSalaryFrom() {
        return jobSalaryFrom;
    }

    public void setJobSalaryFrom(String jobSalaryFrom) {
        this.jobSalaryFrom = jobSalaryFrom;
    }

    public String getJobSalaryTo() {
        return jobSalaryTo;
    }

    public void setJobSalaryTo(String jobSalaryTo) {
        this.jobSalaryTo = jobSalaryTo;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyBedrooms() {
        return propertyBedrooms;
    }

    public void setPropertyBedrooms(String propertyBedrooms) {
        this.propertyBedrooms = propertyBedrooms;
    }

    public String getPropertyFurnishing() {
        return propertyFurnishing;
    }

    public void setPropertyFurnishing(String propertyFurnishing) {
        this.propertyFurnishing = propertyFurnishing;
    }

    public String getPropertyConstructionStatus() {
        return propertyConstructionStatus;
    }

    public void setPropertyConstructionStatus(String propertyConstructionStatus) {
        this.propertyConstructionStatus = propertyConstructionStatus;
    }

    public String getPropertyListedBy() {
        return propertyListedBy;
    }

    public void setPropertyListedBy(String propertyListedBy) {
        this.propertyListedBy = propertyListedBy;
    }

    public String getPropertyCarpetArea() {
        return propertyCarpetArea;
    }

    public void setPropertyCarpetArea(String propertyCarpetArea) {
        this.propertyCarpetArea = propertyCarpetArea;
    }

    public String getPropertyProjectName() {
        return propertyProjectName;
    }

    public void setPropertyProjectName(String propertyProjectName) {
        this.propertyProjectName = propertyProjectName;
    }

    public String getPropertyParkings() {
        return propertyParkings;
    }

    public void setPropertyParkings(String propertyParkings) {
        this.propertyParkings = propertyParkings;
    }
}