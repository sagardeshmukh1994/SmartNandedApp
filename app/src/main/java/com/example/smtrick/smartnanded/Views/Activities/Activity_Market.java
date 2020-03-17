package com.example.smtrick.smartnanded.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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

        imageList = new ArrayList<>();

        txtPrice = (TextView) findViewById(R.id.productPrice);
        txtDescription = (TextView) findViewById(R.id.productDescription);
        imgproduct = (ImageView) findViewById(R.id.ivProductImage);
        pluse = (ImageView) findViewById(R.id.plus);
        minus = (ImageView) findViewById(R.id.minus);
        count = (EditText) findViewById(R.id.count);
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
                }
            }
        });
        pluse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i[0] = i[0] + 1;
                count.setText(String.valueOf(i[0]));
            }
        });


    }


}
