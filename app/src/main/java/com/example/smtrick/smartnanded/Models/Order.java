package com.example.smtrick.smartnanded.Models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.ServerValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {


    public String userId;
    public String userPinCode;
    public String userMobile;
    public String userAddress;
    public String ordertId;
    public String status;
    public String date;
    public String userName;
    public String totalAmount;
    List<String> orderList;
    private Long createdDateTime;


    public Order() {
    }

    public Order(String userId, String userPinCode, String userMobile
            , String userAddress, String ordertId, String date, String status, String userName,
                 String totalAmount) {

        this.userId = userId;
        this.userPinCode = userPinCode;
        this.userMobile = userMobile;
        this.userAddress = userAddress;
        this.ordertId = ordertId;
        this.date = date;
        this.status = status;
        this.userName = userName;
        this.totalAmount = totalAmount;
        this.orderList = new ArrayList<String>();

    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPinCode() {
        return userPinCode;
    }

    public void setUserPinCode(String userPinCode) {
        this.userPinCode = userPinCode;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getOrdertId() {
        return ordertId;
    }

    public void setOrdertId(String ordertId) {
        this.ordertId = ordertId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<String> orderList) {
        this.orderList = orderList;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Exclude
    public Long getCreatedDateTimeLong() {
        return createdDateTime;
    }

    public Map<String, String> getCreatedDateTime() {
        return ServerValue.TIMESTAMP;
    }

    public void setCreatedDateTime(Long createdDateTime) {
        this.createdDateTime = (Long) createdDateTime;
    }



    @Exclude
    public Map getLeedStatusMap() {
        Map<String, Object> leedMap = new HashMap();


        leedMap.put("userId", getUserId());
        leedMap.put("userPinCode", getUserPinCode());
        leedMap.put("userMobile",getUserMobile() );
        leedMap.put("userAddress",getUserAddress() );
        leedMap.put("ordertId",getOrdertId() );
        leedMap.put("date",getDate() );
        leedMap.put("status",getStatus() );
        leedMap.put("userName",getUserName() );
        leedMap.put("orderList",getOrderList() );
        leedMap.put("createdDateTime", getCreatedDateTime());


        return leedMap;

    }
}
