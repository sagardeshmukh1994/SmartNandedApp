package com.example.smtrick.smartnanded.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.smtrick.smartnanded.Models.Products;
import com.example.smtrick.smartnanded.Models.SliderItem;
import com.example.smtrick.smartnanded.R;
import com.example.smtrick.smartnanded.RecyclerListener.RecyclerTouchListener;
import com.example.smtrick.smartnanded.Views.Adapters.SliderAdapterExample;
import com.example.smtrick.smartnanded.constants.Constant;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import static com.example.smtrick.smartnanded.constants.Constant.LEED_MODEL;

public class Activity_Product_Details extends AppCompatActivity {

    SliderView sliderView;
    private SliderAdapterExample adapter;
    Products product;
    ArrayList<String> imageList;
    TextView txtPrice, txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__product__details);

        product = (Products) getIntent().getSerializableExtra(Constant.PRODUCT_MODEL);

        imageList = new ArrayList<>();
        imageList.add(product.getUrl());
        if (product.getSubImages() != null) {
            imageList.addAll(product.getSubImages());
        }

        sliderView = findViewById(R.id.imageSlider);
        txtPrice = findViewById(R.id.price);
        txtDescription = findViewById(R.id.desciption);

        txtDescription.setText(product.getProductDescription());
        if (product.getProductPrice() != null && !product.getProductPrice().equalsIgnoreCase("")) {
//            Typeface tf = Typeface.createFromAsset(getAssets(), "font/Rupee.ttf");
//            txtPrice.setTypeface(tf);
            txtPrice.setText("\u20B9 " + product.getProductPrice());
        }else {
            txtPrice.setText("");
        }

        adapter = new SliderAdapterExample(this);
        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.THIN_WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);

        renewItems();
        addNewItem();
    }

    public void renewItems() {

        adapter.renewItems(imageList);
    }



    public void addNewItem() {

    }

}
