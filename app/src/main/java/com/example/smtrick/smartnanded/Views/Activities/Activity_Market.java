package com.example.smtrick.smartnanded.Views.Activities;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
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
import com.example.smtrick.smartnanded.utilities.FragranceContract;

import java.util.ArrayList;

public class Activity_Market extends AppCompatActivity {

    public static final String  FRAGRANCE_NAME = "fragranceName";
    public static final String  FRAGRANCE_DESCRIPTION = "fragranceDescription";
    public static final String  FRAGRANCE_RATING = "fragranceRating";
    public static final String  FRAGRANCE_IMAGE = "fragranceImage";
    public static final String  FRAGRANCE_PRICE = "fragrancePrice";

    Products product;
    ArrayList<String> imageList;
    TextView txtPrice, txtDescription;
    ImageView imgproduct;
    Button btnAddToCart;
    final int[] i = {0};
    public ImageView pluse, minus;
    public EditText count;
    String fragranceName,fragImage,description;
    Double price;
    private int mQuantity = 1;
    private double mTotalPrice;
    ContentResolver mContentResolver;

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

        mContentResolver = this.getContentResolver();

        product = (Products) getIntent().getSerializableExtra(Constant.PRODUCT_MODEL);

        imageList = new ArrayList<>();

        txtPrice = (TextView) findViewById(R.id.productPrice);
        txtDescription = (TextView) findViewById(R.id.productDescription);
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

        fragranceName = product.getProductName();
        description = product.getProductDescription();
        fragImage = product.getUrl();
        price = Double.valueOf(product.getProductPrice());

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

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    public void addToCart(View view) {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.add_to_cart);
        builder.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                addValuesToCart();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Cancel" button, so dismiss the dialog
                // and continue editing the items.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void addValuesToCart() {

        ContentValues cartValues = new ContentValues();

        cartValues.put(FragranceContract.FragranceEntry.COLUMN_CART_NAME, fragranceName);
        cartValues.put(FragranceContract.FragranceEntry.COLUMN_CART_IMAGE, fragImage);
        cartValues.put(FragranceContract.FragranceEntry.COLUMN_CART_QUANTITY, mQuantity);
        cartValues.put(FragranceContract.FragranceEntry.COLUMN_CART_TOTAL_PRICE, mTotalPrice);
        

        mContentResolver.insert(FragranceContract.FragranceEntry.CONTENT_URI, cartValues);

        Toast.makeText(this, "Successfully added to Cart",
                Toast.LENGTH_SHORT).show();


    }
}
