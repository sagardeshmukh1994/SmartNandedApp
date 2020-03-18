package com.example.smtrick.smartnanded.Views.Activities;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.example.smtrick.smartnanded.callback.CallBack;
import com.example.smtrick.smartnanded.constants.Constant;
import com.example.smtrick.smartnanded.preferences.AppSharedPreference;
import com.example.smtrick.smartnanded.repository.LeedRepository;
import com.example.smtrick.smartnanded.repository.impl.LeedRepositoryImpl;

import java.util.ArrayList;

public class Activity_Market extends AppCompatActivity {

    Products product;
    static ArrayList<Products> productsArrayList;
    TextView txtPrice, txtDescription, txtTotalPrice;
    ImageView imgproduct;
    Button btnAddToCart;
    final int[] i = {0};
    public ImageView pluse, minus;
    public EditText count;
    LeedRepository leedRepository;
    private AppSharedPreference appSharedPreference;


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

        leedRepository = new LeedRepositoryImpl();
        appSharedPreference = new AppSharedPreference(this);
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
//                productsArrayList.add(product1);
                leedRepository.addToCart(product1, new CallBack() {
                    @Override
                    public void onSuccess(Object object) {
                        Toast.makeText(Activity_Market.this, "Added To Cart", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Object object) {

                    }
                });
            }
        });


    }

    private Products fillUserModel(Products product) {


        String uploadId = Constant.CART_TABLE_REF.push().getKey();
        product.setTotalPrice(txtTotalPrice.getText().toString());
        product.setUserId(appSharedPreference.getAgeniId());
        product.setProductQuantity(count.getText().toString());
        product.setProductId(uploadId);
        return product;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_cart:
//                addSomething();
                Intent intent = new Intent(Activity_Market.this, Activity_Cart.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
