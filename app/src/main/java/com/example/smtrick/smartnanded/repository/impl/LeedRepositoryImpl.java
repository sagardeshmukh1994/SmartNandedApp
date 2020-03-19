package com.example.smtrick.smartnanded.repository.impl;

import com.example.smtrick.smartnanded.Models.Advertise;
import com.example.smtrick.smartnanded.Models.Order;
import com.example.smtrick.smartnanded.Models.Products;
import com.example.smtrick.smartnanded.callback.CallBack;
import com.example.smtrick.smartnanded.constants.Constant;
import com.example.smtrick.smartnanded.repository.FirebaseTemplateRepository;
import com.example.smtrick.smartnanded.repository.LeedRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Map;

public class LeedRepositoryImpl extends FirebaseTemplateRepository implements LeedRepository {


    @Override
    public void updateLeedDocuments(String leedId, Map leedMap, final CallBack callBack) {
        final DatabaseReference databaseReference = Constant.LEEDS_TABLE_REF.child(leedId).child("documentImages");
        fireBaseUpdateChildren(databaseReference, leedMap, new CallBack() {
            @Override
            public void onSuccess(Object object) {
                callBack.onSuccess(object);
            }

            @Override
            public void onError(Object object) {
                callBack.onError(object);
            }
        });
    }

    @Override
    public void updateLeed(String leedId, Map leedMap, final CallBack callBack) {
        final DatabaseReference databaseReference = Constant.LEEDS_TABLE_REF.child(leedId);
        fireBaseUpdateChildren(databaseReference, leedMap, new CallBack() {
            @Override
            public void onSuccess(Object object) {
                callBack.onSuccess(object);
            }

            @Override
            public void onError(Object object) {
                callBack.onError(object);
            }
        });
    }

    @Override
    public void readProductsByCategory(String category, final CallBack callBack) {
        final Query query = Constant.PRODUCTS_TABLE_REF.orderByChild("productCategory").equalTo(category);
        fireBaseNotifyChange(query, new CallBack() {
            @Override
            public void onSuccess(Object object) {
                if (object != null) {
                    DataSnapshot dataSnapshot = (DataSnapshot) object;
                    if (dataSnapshot.getValue() != null & dataSnapshot.hasChildren()) {
                        ArrayList<Products> leedsModelArrayList = new ArrayList<>();
                        for (DataSnapshot suggestionSnapshot : dataSnapshot.getChildren()) {
                            Products leedsModel = suggestionSnapshot.getValue(Products.class);

                                leedsModelArrayList.add(leedsModel);
                        }
                        callBack.onSuccess(leedsModelArrayList);
                    } else {
                        callBack.onSuccess(null);
                    }
                }
            }

            @Override
            public void onError(Object object) {
                callBack.onError(object);
            }
        });
    }

    @Override
    public void readAdvertise(final CallBack callBack) {
        final Query query = Constant.ADVERTISE_TABLE_REF;
        fireBaseNotifyChange(query, new CallBack() {
            @Override
            public void onSuccess(Object object) {
                if (object != null) {
                    DataSnapshot dataSnapshot = (DataSnapshot) object;
                    if (dataSnapshot.getValue() != null & dataSnapshot.hasChildren()) {
                        ArrayList<Advertise> leedsModelArrayList = new ArrayList<>();
                        for (DataSnapshot suggestionSnapshot : dataSnapshot.getChildren()) {
                            Advertise add = suggestionSnapshot.getValue(Advertise.class);
                            leedsModelArrayList.add(add);
                        }
                        callBack.onSuccess(leedsModelArrayList);
                    } else {
                        callBack.onSuccess(null);
                    }
                }
            }

            @Override
            public void onError(Object object) {
                callBack.onError(object);
            }
        });
    }

    @Override
    public void addToCart(Products products, final CallBack callBack) {
        DatabaseReference databaseReference = Constant.CART_TABLE_REF.child(products.getProductId());
        fireBaseCreate(databaseReference, products, new CallBack() {
            @Override
            public void onSuccess(Object object) {
                callBack.onSuccess(object);
            }

            @Override
            public void onError(Object object) {
                callBack.onError(object);
            }
        });
    }


    @Override
    public void readCartProductsByuserId(String id, final CallBack callBack) {
        final Query query = Constant.CART_TABLE_REF.orderByChild("userId").equalTo(id);
        fireBaseNotifyChange(query, new CallBack() {
            @Override
            public void onSuccess(Object object) {
                if (object != null) {
                    DataSnapshot dataSnapshot = (DataSnapshot) object;
                    if (dataSnapshot.getValue() != null & dataSnapshot.hasChildren()) {
                        ArrayList<Products> leedsModelArrayList = new ArrayList<>();
                        for (DataSnapshot suggestionSnapshot : dataSnapshot.getChildren()) {
                            Products leedsModel = suggestionSnapshot.getValue(Products.class);

                            leedsModelArrayList.add(leedsModel);
                        }
                        callBack.onSuccess(leedsModelArrayList);
                    } else {
                        callBack.onSuccess(null);
                    }
                }
            }

            @Override
            public void onError(Object object) {
                callBack.onError(object);
            }
        });
    }

    @Override
    public void placeOrder(Order order, final CallBack callBack) {
        DatabaseReference databaseReference = Constant.ORDER_TABLE_REF.child(order.getOrdertId());
        fireBaseCreate(databaseReference, order, new CallBack() {
            @Override
            public void onSuccess(Object object) {
                callBack.onSuccess(object);
            }

            @Override
            public void onError(Object object) {
                callBack.onError(object);
            }
        });
    }


    @Override
    public void readOrders(final CallBack callBack) {
        final Query query = Constant.ORDER_TABLE_REF;
        fireBaseNotifyChange(query, new CallBack() {
            @Override
            public void onSuccess(Object object) {
                if (object != null) {
                    DataSnapshot dataSnapshot = (DataSnapshot) object;
                    if (dataSnapshot.getValue() != null & dataSnapshot.hasChildren()) {
                        ArrayList<Order> leedsModelArrayList = new ArrayList<>();
                        for (DataSnapshot suggestionSnapshot : dataSnapshot.getChildren()) {
                            Order order = suggestionSnapshot.getValue(Order.class);
                            leedsModelArrayList.add(order);
                        }
                        callBack.onSuccess(leedsModelArrayList);
                    } else {
                        callBack.onSuccess(null);
                    }
                }
            }

            @Override
            public void onError(Object object) {
                callBack.onError(object);
            }
        });
    }

}
