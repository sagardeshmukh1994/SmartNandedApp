package com.example.smtrick.smartnanded.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.smtrick.smartnanded.Models.Products;
import com.example.smtrick.smartnanded.R;
import com.example.smtrick.smartnanded.Views.Adapters.Products_Adapter;
import com.example.smtrick.smartnanded.Views.Adapters.SliderAdapterExample;
import com.example.smtrick.smartnanded.Views.Dialog.ProgressDialogClass;
import com.example.smtrick.smartnanded.callback.CallBack;
import com.example.smtrick.smartnanded.constants.Constant;
import com.example.smtrick.smartnanded.repository.LeedRepository;
import com.example.smtrick.smartnanded.repository.impl.LeedRepositoryImpl;
import com.example.smtrick.smartnanded.utilities.Utility;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class Activity_Market extends AppCompatActivity {


    Products product;
    ArrayList<String> imageList;
    TextView txtPrice, txtDescription;
    ImageView imgproduct;
    Spinner psinnerQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__market);

        product = (Products) getIntent().getSerializableExtra(Constant.PRODUCT_MODEL);

        imageList = new ArrayList<>();
        imageList.add(product.getUrl());
        if (product.getSubImages() != null) {
            imageList.addAll(product.getSubImages());
        }


        txtPrice = (TextView) findViewById(R.id.productPrice);
        txtDescription = (TextView) findViewById(R.id.productDescription);
        imgproduct = (ImageView) findViewById(R.id.ivProductImage);
        psinnerQuantity = (Spinner) findViewById(R.id.spQuantity);

        txtDescription.setText(product.getProductDescription());
        if (product.getProductPrice() != null && !product.getProductPrice().equalsIgnoreCase("")) {
//            Typeface tf = Typeface.createFromAsset(getAssets(), "font/Rupee.ttf");
//            txtPrice.setTypeface(tf);
            txtPrice.setText("\u20B9 " + product.getProductPrice());
        }else {
            txtPrice.setText("");
        }




    }




}
