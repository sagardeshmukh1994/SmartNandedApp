package com.example.smtrick.smartnanded.Views.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smtrick.smartnanded.Models.Order;
import com.example.smtrick.smartnanded.R;
import com.example.smtrick.smartnanded.Views.Adapters.Generated_Orders_Adapter;
import com.example.smtrick.smartnanded.callback.CallBack;
import com.example.smtrick.smartnanded.repository.LeedRepository;
import com.example.smtrick.smartnanded.repository.impl.LeedRepositoryImpl;

import java.util.ArrayList;


public class Fragment_View_Orders extends Fragment {

    RecyclerView recyclerOrders;
    LeedRepository leedRepository;
    ArrayList<Order> orderArrayList;
    Generated_Orders_Adapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_orders, container, false);

        leedRepository = new LeedRepositoryImpl();

        orderArrayList = new ArrayList<>();

        recyclerOrders = (RecyclerView) view.findViewById(R.id.recycleOrders);

        readOrders();
        return view;
    }

    private void readOrders() {
        leedRepository.readOrders(new CallBack() {
            @Override
            public void onSuccess(Object object) {
                if (object != null){
                    orderArrayList = (ArrayList<Order>) object;
                    adapter = new Generated_Orders_Adapter(getContext(),orderArrayList);
                }
            }

            @Override
            public void onError(Object object) {

            }
        });
    }


}