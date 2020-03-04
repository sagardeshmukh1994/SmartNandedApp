package com.example.smtrick.smartnanded.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.smtrick.smartnanded.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView frdMarket, frdCity, frdProperties, frdbike, frdCar, frdTransport, frdTravel, frdJobs, frdMobiles,
            frdAgree, frdOffers, frdOthers;

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.Black), PorterDuff.Mode.SRC_ATOP);

        frdMarket = (ImageView) findViewById(R.id.frdMarket);
        frdCity = (ImageView) findViewById(R.id.frdCity);
        frdProperties = (ImageView) findViewById(R.id.frdProperties);
        frdbike = (ImageView) findViewById(R.id.frdBikes);
        frdCar = (ImageView) findViewById(R.id.frdCars);
        frdTransport = (ImageView) findViewById(R.id.frdTransport);
        frdTravel = (ImageView) findViewById(R.id.frdTravel);
        frdJobs = (ImageView) findViewById(R.id.frdJobs);
        frdMobiles = (ImageView) findViewById(R.id.frdMobiles);
        frdAgree = (ImageView) findViewById(R.id.frdAggri);
        frdOffers = (ImageView) findViewById(R.id.frdOffers);
        frdOthers = (ImageView) findViewById(R.id.frdOthers);

        frdMarket.setOnClickListener(this);
        frdCity.setOnClickListener(this);
        frdProperties.setOnClickListener(this);
        frdbike.setOnClickListener(this);
        frdCar.setOnClickListener(this);
        frdTransport.setOnClickListener(this);
        frdTravel.setOnClickListener(this);
        frdJobs.setOnClickListener(this);
        frdAgree.setOnClickListener(this);
        frdOffers.setOnClickListener(this);
        frdOthers.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == frdMarket){
            
        }
        else if (view == frdCity){

        }
        else if (view == frdProperties){

        }
        else if (view == frdbike){

        }
        else if (view == frdCar){

        }
        else if (view == frdTransport){

        }
        else if (view == frdTravel){

        }
        else if (view == frdJobs){

        }
        else if (view == frdMobiles){

        }
        else if (view == frdAgree){

        }
        else if (view == frdOffers){

        }
        else if (view == frdOthers){

        }
    }
}
