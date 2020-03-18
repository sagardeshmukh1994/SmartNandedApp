package com.example.smtrick.smartnanded.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.smtrick.smartnanded.Models.Products;
import com.example.smtrick.smartnanded.R;
import com.example.smtrick.smartnanded.Views.Adapters.Products_Adapter;
import com.example.smtrick.smartnanded.Views.Dialog.ProgressDialogClass;
import com.example.smtrick.smartnanded.callback.CallBack;
import com.example.smtrick.smartnanded.constants.Constant;
import com.example.smtrick.smartnanded.repository.LeedRepository;
import com.example.smtrick.smartnanded.repository.impl.LeedRepositoryImpl;
import com.example.smtrick.smartnanded.utilities.Utility;

import java.util.ArrayList;

public class Activity_Products extends AppCompatActivity {

    RecyclerView recycleProducts;
    ProgressDialogClass progressDialogClass;
    LeedRepository leedRepository;
    ArrayList<Products> productsArrayList;
    Products_Adapter productsAdapter;
    String CATEGORY;

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__others);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.drower_icon_color), PorterDuff.Mode.SRC_ATOP);


        Intent intent = getIntent();
        CATEGORY = intent.getStringExtra("CATEGORY");
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#24897B'>" + CATEGORY + "</font>"));


        leedRepository = new LeedRepositoryImpl();
        progressDialogClass = new ProgressDialogClass(this);

        productsArrayList = new ArrayList<>();

        recycleProducts = (RecyclerView) findViewById(R.id.recycleProducts);
        getProducts();

    }

    private void getProducts() {
        progressDialogClass.showDialog(this.getString(R.string.loading), this.getString(R.string.PLEASE_WAIT));
        leedRepository.readProductsByCategory(CATEGORY, new CallBack() {
            @Override
            public void onSuccess(Object object) {
                if (object != null) {

                    productsArrayList = (ArrayList<Products>) object;

                    productsAdapter = new Products_Adapter(getApplication(), productsArrayList, CATEGORY);
                    //adding adapter to recyclerview
                    recycleProducts.setAdapter(productsAdapter);
                    recycleProducts.setHasFixedSize(true);
                    recycleProducts.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    productsAdapter.notifyDataSetChanged();
                    progressDialogClass.dismissDialog();

                } else {
                    progressDialogClass.dismissDialog();
                }

            }

            @Override
            public void onError(Object object) {
                progressDialogClass.dismissDialog();
                Utility.showLongMessage(getApplication(), getString(R.string.server_error));
            }
        });
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
                Intent intent = new Intent(Activity_Products.this, Activity_Cart.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
