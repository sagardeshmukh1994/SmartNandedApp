package com.example.smtrick.smartnanded.Views.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smtrick.smartnanded.Models.Order;
import com.example.smtrick.smartnanded.R;
import com.example.smtrick.smartnanded.Views.Adapters.Completed_Orders_Adapter;
import com.example.smtrick.smartnanded.Views.Adapters.Users_Orders_Adapter;
import com.example.smtrick.smartnanded.callback.CallBack;
import com.example.smtrick.smartnanded.constants.Constant;
import com.example.smtrick.smartnanded.preferences.AppSharedPreference;
import com.example.smtrick.smartnanded.repository.LeedRepository;
import com.example.smtrick.smartnanded.repository.impl.LeedRepositoryImpl;

import java.util.ArrayList;


public class Fragment_User_Placed_Orders extends Fragment {

    RecyclerView recyclerOrders;
    LeedRepository leedRepository;
    ArrayList<Order> orderArrayList;
    private ArrayList<Order> orderArrayList1;
    Users_Orders_Adapter adapter;
    AppSharedPreference appSharedPreference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_user_placed_orders, container, false);

        leedRepository = new LeedRepositoryImpl();
        appSharedPreference = new AppSharedPreference(getContext());

        orderArrayList = new ArrayList<>();
        orderArrayList1 = new ArrayList<>();

        recyclerOrders = (RecyclerView) view.findViewById(R.id.recycleOrders);

        readOrders();
        return view;
    }

    private void readOrders() {
        leedRepository.readreadOrdersByUserId(appSharedPreference.getAgeniId(), new CallBack() {
            @Override
            public void onSuccess(Object object) {
                if (object != null) {
                    orderArrayList = (ArrayList<Order>) object;

                    for (int i =0; i<orderArrayList.size(); i++){
                        if (orderArrayList.get(i).getStatus().equalsIgnoreCase(Constant.STATUS_PLACED)){
                            orderArrayList1.add(orderArrayList.get(i));
                        }
                    }
                    adapter = new Users_Orders_Adapter(getContext(), orderArrayList1);
                    recyclerOrders.setAdapter(adapter);
                    recyclerOrders.setHasFixedSize(true);
                    recyclerOrders.setLayoutManager(new LinearLayoutManager(getContext()));
                }
            }

            @Override
            public void onError(Object object) {

            }
        });
    }


}