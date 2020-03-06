package com.example.smtrick.smartnanded.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.PorterDuff;
import android.os.Bundle;

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

public class Activity_Properties extends AppCompatActivity {

    RecyclerView recycleProducts;
    ProgressDialogClass progressDialogClass;
    LeedRepository leedRepository;
    ArrayList<Products> productsArrayList;
    Products_Adapter productsAdapter;

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__properties);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.Black), PorterDuff.Mode.SRC_ATOP);


        leedRepository = new LeedRepositoryImpl();
        progressDialogClass = new ProgressDialogClass(this);

        productsArrayList = new ArrayList<>();

        recycleProducts = (RecyclerView) findViewById(R.id.recycleProducts);
        getProducts();

    }

    private void getProducts() {
        progressDialogClass.showDialog(this.getString(R.string.loading), this.getString(R.string.PLEASE_WAIT));
        leedRepository.readProductsByCategory(Constant.CATEGORY_PROPERTIES, new CallBack() {
            @Override
            public void onSuccess(Object object) {
                if (object != null) {

                    productsArrayList = (ArrayList<Products>) object;

                    productsAdapter = new Products_Adapter(getApplication(), productsArrayList);
                    //adding adapter to recyclerview
                    recycleProducts.setAdapter(productsAdapter);
                    recycleProducts.setHasFixedSize(true);
                    recycleProducts.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    productsAdapter.notifyDataSetChanged();
                    progressDialogClass.dismissDialog();

                }else {
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


}
