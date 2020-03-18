package com.example.smtrick.smartnanded.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.smtrick.smartnanded.Models.Products;
import com.example.smtrick.smartnanded.R;
import com.example.smtrick.smartnanded.Views.Adapters.CartProducts_Adapter;
import com.example.smtrick.smartnanded.Views.Adapters.Products_Adapter;
import com.example.smtrick.smartnanded.callback.CallBack;
import com.example.smtrick.smartnanded.preferences.AppSharedPreference;
import com.example.smtrick.smartnanded.repository.LeedRepository;
import com.example.smtrick.smartnanded.repository.impl.LeedRepositoryImpl;

import java.util.ArrayList;

public class Activity_Cart extends AppCompatActivity {

    private LeedRepository leedRepository;
    private AppSharedPreference appSharedPreference;
    private ArrayList<Products> cartProductsList;
    private RecyclerView recyclerViewCart;
    private CartProducts_Adapter cartProductsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__cart);

        leedRepository = new LeedRepositoryImpl();
        appSharedPreference = new AppSharedPreference(this);

        cartProductsList = new ArrayList<>();

        recyclerViewCart = (RecyclerView) findViewById(R.id.cart_recycler);

        readCart();
    }

    private void readCart() {
        leedRepository.readCartProductsByuserId(appSharedPreference.getAgeniId(), new CallBack() {
            @Override
            public void onSuccess(Object object) {
                if (object != null) {
                    cartProductsList = (ArrayList<Products>) object;

                    cartProductsAdapter = new CartProducts_Adapter(getApplication(), cartProductsList);
                    //adding adapter to recyclerview
                    recyclerViewCart.setAdapter(cartProductsAdapter);
                    recyclerViewCart.setHasFixedSize(true);
                    recyclerViewCart.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    cartProductsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(Object object) {

            }
        });
    }
}
