package com.example.smtrick.smartnanded.Views.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.smtrick.smartnanded.R;
import com.example.smtrick.smartnanded.Views.Adapters.ViewPagerAdapter;
import com.example.smtrick.smartnanded.interfaces.OnFragmentInteractionListener;
import com.google.android.material.tabs.TabLayout;

public class Orders_Tab_Fragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    public OnFragmentInteractionListener mListener;

    public Orders_Tab_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mListener != null) {
            mListener.onFragmentInteraction("Requests");
        }
        View view = inflater.inflate(R.layout.fragment_orders_tab, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(3);

        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragement(new Fragment_View_Orders(), "Generated Orders");
        viewPagerAdapter.addFragement(new Fragment_View_Orders(), "Completed Orders");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setTabMode(1);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
