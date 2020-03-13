package com.example.smtrick.smartnanded.Views.Fragments;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.smtrick.smartnanded.Models.Advertise;
import com.example.smtrick.smartnanded.R;
import com.example.smtrick.smartnanded.Views.Activities.Activity_Bike;
import com.example.smtrick.smartnanded.Views.Activities.Activity_Cars;
import com.example.smtrick.smartnanded.Views.Activities.Activity_City;
import com.example.smtrick.smartnanded.Views.Activities.Activity_Jobs;
import com.example.smtrick.smartnanded.Views.Activities.Activity_Market;
import com.example.smtrick.smartnanded.Views.Activities.Activity_Mobiles;
import com.example.smtrick.smartnanded.Views.Activities.Activity_Products;
import com.example.smtrick.smartnanded.Views.Activities.Activity_Properties;
import com.example.smtrick.smartnanded.Views.Activities.Activity_Transport;
import com.example.smtrick.smartnanded.Views.Activities.Activity_Travels;
import com.example.smtrick.smartnanded.Views.Activities.MainActivity;
import com.example.smtrick.smartnanded.Views.Adapters.ImageAdapter;
import com.example.smtrick.smartnanded.Views.Adapters.SliderAdapterExample;
import com.example.smtrick.smartnanded.callback.CallBack;
import com.example.smtrick.smartnanded.constants.Constant;
import com.example.smtrick.smartnanded.repository.LeedRepository;
import com.example.smtrick.smartnanded.repository.impl.LeedRepositoryImpl;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Home extends Fragment implements View.OnClickListener {

    ImageView imgMarket, imgCity, imgProperties, imgBikes, imgCars, imgTransport, imgTravel, imgJobs, imgMobiles;
    TextView txtSeeAll;
    private static int NUM_PAGES = 0;
    LeedRepository leedRepository;
    ArrayList<Advertise> advertiseArrayList;
    ViewPager viewPager;
    ArrayList<String> imageList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        leedRepository = new LeedRepositoryImpl();

        advertiseArrayList = new ArrayList<>();
        imageList = new ArrayList<>();

        viewPager = view.findViewById(R.id.viewPager);



        imgMarket = (ImageView) view.findViewById(R.id.imgMarket);
        imgCity = (ImageView) view.findViewById(R.id.imgCity);
        imgProperties = (ImageView) view.findViewById(R.id.imgProperties);
        imgBikes = (ImageView) view.findViewById(R.id.imgBikes);
        imgCars = (ImageView) view.findViewById(R.id.imgCars);
        imgTransport = (ImageView) view.findViewById(R.id.imgTransport);
        imgTravel = (ImageView) view.findViewById(R.id.imgTravel);
        imgJobs = (ImageView) view.findViewById(R.id.imgJobs);
        imgMobiles = (ImageView) view.findViewById(R.id.imgMobiles);
        txtSeeAll = (TextView) view.findViewById(R.id.txtSeeAll);

        txtSeeAll.setOnClickListener(this);
        imgMarket.setOnClickListener(this);
        imgCity.setOnClickListener(this);
        imgProperties.setOnClickListener(this);
        imgBikes.setOnClickListener(this);
        imgCars.setOnClickListener(this);
        imgTransport.setOnClickListener(this);
        imgTravel.setOnClickListener(this);
        imgJobs.setOnClickListener(this);
        imgMobiles.setOnClickListener(this);

        readAdvertise();





        return view;
    }

    private void readAdvertise() {
        leedRepository.readAdvertise(new CallBack() {
            @Override
            public void onSuccess(Object object) {
                if (object != null) {

                    advertiseArrayList = (ArrayList<Advertise>) object;

                    for (int i= 0; i<advertiseArrayList.size(); i++){
                        imageList.add(advertiseArrayList.get(i).getUrl());
                    }
                    NUM_PAGES = advertiseArrayList.size();
//            showDots();
                    ImageAdapter adapter = new ImageAdapter(getContext(), advertiseArrayList);
                    viewPager.setAdapter(adapter);
                    Timer timer = new Timer();
                    timer.scheduleAtFixedRate(new SliderTimer(), 500, 3000);

                }
            }

            @Override
            public void onError(Object object) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == txtSeeAll) {
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        } else if (view == imgMarket) {
            Intent intent = new Intent(getContext(), Activity_Products.class);
            intent.putExtra("CATEGORY", Constant.CATEGORY_MARKET);
            startActivity(intent);
        } else if (view == imgCity) {
            Intent intent = new Intent(getContext(), Activity_Products.class);
            intent.putExtra("CATEGORY", Constant.CATEGORY_CITY);
            startActivity(intent);
        } else if (view == imgProperties) {
            Intent intent = new Intent(getContext(), Activity_Products.class);
            intent.putExtra("CATEGORY", Constant.CATEGORY_PROPERTIES);
            startActivity(intent);
        } else if (view == imgBikes) {
            Intent intent = new Intent(getContext(), Activity_Products.class);
            intent.putExtra("CATEGORY", Constant.CATEGORY_BIKE);
            startActivity(intent);
        } else if (view == imgCars) {
            Intent intent = new Intent(getContext(), Activity_Products.class);
            intent.putExtra("CATEGORY", Constant.CATEGORY_CAR);
            startActivity(intent);
        } else if (view == imgTransport) {
            Intent intent = new Intent(getContext(), Activity_Products.class);
            intent.putExtra("CATEGORY", Constant.CATEGORY_TRANSPORT);
            startActivity(intent);
        } else if (view == imgTravel) {
            Intent intent = new Intent(getContext(), Activity_Products.class);
            intent.putExtra("CATEGORY", Constant.CATEGORY_TRAVELS);
            startActivity(intent);
        } else if (view == imgJobs) {
            Intent intent = new Intent(getContext(), Activity_Products.class);
            intent.putExtra("CATEGORY", Constant.CATEGORY_JOBS);
            startActivity(intent);
        } else if (view == imgMobiles) {
            Intent intent = new Intent(getContext(), Activity_Products.class);
            intent.putExtra("CATEGORY", Constant.CATEGORY_MOBILES);
            startActivity(intent);
        }
    }

    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            if (isVisible()) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (viewPager.getCurrentItem() < NUM_PAGES - 1) {
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }
    }


}
