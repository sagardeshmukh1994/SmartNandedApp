package com.example.smtrick.smartnanded.Views.Activities;

import android.content.ContentResolver;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.smtrick.smartnanded.Models.Products;
import com.example.smtrick.smartnanded.R;
import com.example.smtrick.smartnanded.constants.Constant;

import java.util.ArrayList;

public class Activity_Market extends AppCompatActivity {

    public static final String FRAGRANCE_NAME = "fragranceName";
    public static final String FRAGRANCE_DESCRIPTION = "fragranceDescription";
    public static final String FRAGRANCE_RATING = "fragranceRating";
    public static final String FRAGRANCE_IMAGE = "fragranceImage";
    public static final String FRAGRANCE_PRICE = "fragrancePrice";

    Products product;
    static ArrayList<Products> productsArrayList;
    TextView txtPrice, txtDescription, txtTotalPrice;
    ImageView imgproduct;
    Button btnAddToCart;
    final int[] i = {0};
    public ImageView pluse, minus;
    public EditText count;


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__market);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.drower_icon_color), PorterDuff.Mode.SRC_ATOP);

        product = (Products) getIntent().getSerializableExtra(Constant.PRODUCT_MODEL);

        productsArrayList = new ArrayList<>();

        txtPrice = (TextView) findViewById(R.id.productPrice);
        txtDescription = (TextView) findViewById(R.id.productDescription);
        txtTotalPrice = (TextView) findViewById(R.id.totalpricevalue);
        imgproduct = (ImageView) findViewById(R.id.ivProductImage);
        pluse = (ImageView) findViewById(R.id.plus);
        minus = (ImageView) findViewById(R.id.minus);
        count = (EditText) findViewById(R.id.count);
        btnAddToCart = (Button) findViewById(R.id.addtocart);
        count.setText("1");

        txtDescription.setText(product.getProductDescription());
        if (product.getProductPrice() != null && !product.getProductPrice().equalsIgnoreCase("")) {
//            Typeface tf = Typeface.createFromAsset(getAssets(), "font/Rupee.ttf");
//            txtPrice.setTypeface(tf);
            txtPrice.setText("\u20B9 " + product.getProductPrice());
        } else {
            txtPrice.setText("");
        }
        Glide.with(getApplicationContext()).load(product.getUrl()).placeholder(R.drawable.loading).into(imgproduct);


        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i[0] == 0) {
                    Toast.makeText(getApplicationContext(), "Please increase", Toast.LENGTH_SHORT).show();
                } else {
                    i[0]--;
                    count.setText(String.valueOf(i[0]));

                    double total = Double.parseDouble(count.getText().toString()) * Double.parseDouble(product.getProductPrice());
                    txtTotalPrice.setText(String.valueOf(total));
                }
            }
        });
        pluse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i[0] = i[0] + 1;
                count.setText(String.valueOf(i[0]));

                count.setText(String.valueOf(i[0]));
                double total = Double.parseDouble(count.getText().toString()) * Double.parseDouble(product.getProductPrice());
                txtTotalPrice.setText(String.valueOf(total));
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Products product1 = fillUserModel(product);
                productsArrayList.add(product1);
            }
        });


    }

    private Products fillUserModel(Products product) {

        product.setTotalPrice(txtTotalPrice.getText().toString());
        return product;
    }

}
