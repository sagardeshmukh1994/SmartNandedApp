package com.example.smtrick.smartnanded.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.smtrick.smartnanded.Models.Order;
import com.example.smtrick.smartnanded.Models.Products;
import com.example.smtrick.smartnanded.R;
import com.example.smtrick.smartnanded.Views.Adapters.CartProducts_Adapter;
import com.example.smtrick.smartnanded.Views.Adapters.Products_Adapter;
import com.example.smtrick.smartnanded.callback.CallBack;
import com.example.smtrick.smartnanded.constants.Constant;
import com.example.smtrick.smartnanded.preferences.AppSharedPreference;
import com.example.smtrick.smartnanded.repository.LeedRepository;
import com.example.smtrick.smartnanded.repository.impl.LeedRepositoryImpl;

import java.util.ArrayList;

public class Activity_Cart extends AppCompatActivity implements View.OnClickListener {

    private LeedRepository leedRepository;
    private AppSharedPreference appSharedPreference;
    private ArrayList<Products> cartProductsList;
    private ArrayList<String> orderProductsList;
    private RecyclerView recyclerViewCart;
    private CartProducts_Adapter cartProductsAdapter;
    TextView txtTotalPrice;
    EditText edtUserAddress;
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
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#24897B'>" + "My Cart" + "</font>"));

        leedRepository = new LeedRepositoryImpl();
        appSharedPreference = new AppSharedPreference(this);

        cartProductsList = new ArrayList<>();
        orderProductsList = new ArrayList<>();

        recyclerViewCart = (RecyclerView) findViewById(R.id.cart_recycler);
        txtTotalPrice = (TextView) findViewById(R.id.totalPrice);
        btnBuy = (Button) findViewById(R.id.button_payment);

        btnBuy.setOnClickListener(this);

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
                    for (int i = 0; i < cartProductsList.size(); i++) {
                        total = total + Double.valueOf(cartProductsList.get(i).getTotalPrice());
                    }
                    txtTotalPrice.setText(String.valueOf(total));
                }
            }

            @Override
            public void onError(Object object) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == btnBuy) {

            final Dialog dialog1 = new Dialog(Activity_Cart.this);
            dialog1.getWindow().setBackgroundDrawableResource(R.drawable.dialogboxanimation);
            dialog1.setContentView(R.layout.dialog_place_order);

            edtUserAddress = (EditText) dialog1.findViewById(R.id.txtdatetime);
            Button btnorder = (Button) dialog1.findViewById(R.id.btnsendrequest);
            Button btncancle = (Button) dialog1.findViewById(R.id.btncancle);

            dialog1.show();
            PlaceOrder();
        }
    }

    private void PlaceOrder() {
        Order order = fillUserModel();
    }

    private Order fillUserModel() {

        for (int i = 0; i < cartProductsList.size(); i++) {
            orderProductsList.add("Product" + cartProductsList.get(i).getProductName() + "\n" +
                    "Quantity" + cartProductsList.get(i).getProductQuantity() + "\n" +
                    "Total Price" + cartProductsList.get(i).getTotalPrice() + "\n");
        }
        String id = Constant.ORDER_TABLE_REF.push().getKey();
        Order order = new Order();

        order.setOrdertId(id);
        order.setUserAddress(edtUserAddress.getText().toString());
        order.setUserMobile(appSharedPreference.getMobileNo());
        order.setStatus(Constant.STATUS_PLACED);
        order.setUserName(appSharedPreference.getUserName());
        order.setOrderList(orderProductsList);


        return order;
    }
}
