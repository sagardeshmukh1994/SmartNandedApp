package com.example.smtrick.smartnanded.service;


import com.example.smtrick.smartnanded.callback.CallBack;

public interface ImageCompressionService {
    void compressImage(String ImagePath, CallBack callBack);
}
