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

import com.example.smtrick.smartnanded.R;
import com.example.smtrick.smartnanded.Views.Activities.Activity_Bike;
import com.example.smtrick.smartnanded.Views.Activities.Activity_Cars;
import com.example.smtrick.smartnanded.Views.Activities.Activity_City;
import com.example.smtrick.smartnanded.Views.Activities.Activity_Jobs;
import com.example.smtrick.smartnanded.Views.Activities.Activity_Market;
import com.example.smtrick.smartnanded.Views.Activities.Activity_Mobiles;
import com.example.smtrick.smartnanded.Views.Activities.Activity_Properties;
import com.example.smtrick.smartnanded.Views.Activities.Activity_Transport;
import com.example.smtrick.smartnanded.Views.Activities.Activity_Travels;
import com.example.smtrick.smartnanded.Views.Activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Home extends Fragment implements View.OnClickListener {

    ImageView imgMarket, imgCity, imgProperties, imgBikes, imgCars, imgTransport, imgTravel, imgJobs, imgMobiles;
    TextView txtSeeAll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

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


        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == txtSeeAll){
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        }
        else if (view == imgMarket){
            Intent intent = new Intent(getContext(), Activity_Market.class);
            startActivity(intent);
        }
        else if (view == imgCity){
            Intent intent = new Intent(getContext(), Activity_City.class);
            startActivity(intent);
        }
        else if (view == imgProperties){
            Intent intent = new Intent(getContext(), Activity_Properties.class);
            startActivity(intent);
        }
        else if (view == imgBikes){
            Intent intent = new Intent(getContext(), Activity_Bike.class);
            startActivity(intent);
        }
        else if (view == imgCars){
            Intent intent = new Intent(getContext(), Activity_Cars.class);
            startActivity(intent);
        }
        else if (view == imgTransport){
            Intent intent = new Intent(getContext(), Activity_Transport.class);
            startActivity(intent);
        }
        else if (view == imgTravel){
            Intent intent = new Intent(getContext(), Activity_Travels.class);
            startActivity(intent);
        }
        else if (view == imgJobs){
            Intent intent = new Intent(getContext(), Activity_Jobs.class);
            startActivity(intent);
        }
        else if (view == imgMobiles){
            Intent intent = new Intent(getContext(), Activity_Mobiles.class);
            startActivity(intent);
        }
    }
}
