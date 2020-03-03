package com.example.smtrick.smartnanded.repository;

import com.example.smtrick.smartnanded.callback.CallBack;

import java.util.Map;

public interface LeedRepository {

    void updateLeedDocuments(final String leedId, final Map leedMap, final CallBack callback);

    void updateLeed(final String leedId, final Map leedMap, final CallBack callback);

}