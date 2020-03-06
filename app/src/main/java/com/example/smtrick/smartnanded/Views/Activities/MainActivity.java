package com.example.smtrick.smartnanded.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
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
            Intent intent = new Intent(MainActivity.this,Activity_Market.class);
            startActivity(intent);
        }
        else if (view == frdCity){
            Intent intent = new Intent(MainActivity.this,Activity_City.class);
            startActivity(intent);
        }
        else if (view == frdProperties){
            Intent intent = new Intent(MainActivity.this,Activity_Properties.class);
            startActivity(intent);
        }
        else if (view == frdbike){
            Intent intent = new Intent(MainActivity.this,Activity_Bike.class);
            startActivity(intent);
        }
        else if (view == frdCar){
            Intent intent = new Intent(MainActivity.this,Activity_Cars.class);
            startActivity(intent);
        }
        else if (view == frdTransport){
            Intent intent = new Intent(MainActivity.this,Activity_Transport.class);
            startActivity(intent);
        }
        else if (view == frdTravel){
            Intent intent = new Intent(MainActivity.this,Activity_Travels.class);
            startActivity(intent);
        }
        else if (view == frdJobs){
            Intent intent = new Intent(MainActivity.this,Activity_Jobs.class);
            startActivity(intent);
        }
        else if (view == frdMobiles){
            Intent intent = new Intent(MainActivity.this,Activity_Mobiles.class);
            startActivity(intent);
        }
        else if (view == frdAgree){
            Intent intent = new Intent(MainActivity.this,Activity_Aggriculture.class);
            startActivity(intent);
        }
        else if (view == frdOffers){
            Intent intent = new Intent(MainActivity.this,Activity_Offers.class);
            startActivity(intent);
        }
        else if (view == frdOthers){ 
            Intent intent = new Intent(MainActivity.this,Activity_Others.class);
            startActivity(intent);
        }
    }
}
