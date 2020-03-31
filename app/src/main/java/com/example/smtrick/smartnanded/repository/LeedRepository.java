package com.example.smtrick.smartnanded.repository;

import com.example.smtrick.smartnanded.Models.Order;
import com.example.smtrick.smartnanded.Models.Products;
import com.example.smtrick.smartnanded.callback.CallBack;

import java.util.Map;

public interface LeedRepository {

    void updateLeedDocuments(final String leedId, final Map leedMap, final CallBack callback);

    void updateLeed(final String leedId, final Map leedMap, final CallBack callback);

    void readProductsByCategory(final String id, final CallBack callBack);

    void readAdvertise(final CallBack callback);

    void addToCart(final Products leedsModel, final CallBack callback);

    void readCartProductsByuserId(final String id, final CallBack callBack);

    void placeOrder(final Order order, final CallBack callback);

    void readOrders(final CallBack callback);

    void readreadOrdersByStatus(final String status, final CallBack callBack);

    void updateOrder(final String leedId, final Map leedMap, final CallBack callback);

    void readreadOrdersByUserId(final String userId, final CallBack callBack);

}
