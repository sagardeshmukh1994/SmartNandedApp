package com.example.smtrick.smartnanded.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.TextView;

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
    TextView txtTotalPrice;
    Button btnBuy;

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__cart);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.drower_icon_color), PorterDuff.Mode.SRC_ATOP);
        leedRepository = new LeedRepositoryImpl();
        appSharedPreference = new AppSharedPreference(this);

        cartProductsList = new ArrayList<>();

        recyclerViewCart = (RecyclerView) findViewById(R.id.cart_recycler);
        txtTotalPrice = (TextView) findViewById(R.id.totalPrice);
        btnBuy = (Button) findViewById(R.id.button_payment);

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

                    Double total = 0.00;
                    for (int i=0; i<cartProductsList.size(); i++){
                        total = total + Double.valueOf( cartProductsList.get(i).getTotalPrice());
                    }
                    txtTotalPrice.setText(String.valueOf(total));
                }
            }

            @Override
            public void onError(Object object) {

            }
        });
    }
}
