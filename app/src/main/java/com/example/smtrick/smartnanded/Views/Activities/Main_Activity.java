package com.example.smtrick.smartnanded.Views.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.smtrick.smartnanded.Exception.ExceptionUtil;
import com.example.smtrick.smartnanded.Models.User;
import com.example.smtrick.smartnanded.R;
import com.example.smtrick.smartnanded.Views.Fragments.Fragment_Home;
import com.example.smtrick.smartnanded.Views.Fragments.Fragment_Reports;
import com.example.smtrick.smartnanded.Views.Fragments.Fragment_Upload_Offers;
import com.example.smtrick.smartnanded.Views.Fragments.Fragment_Upload_Products;
import com.example.smtrick.smartnanded.Views.Fragments.My_Orders_Tab_Fragment;
import com.example.smtrick.smartnanded.Views.Fragments.Orders_Tab_Fragment;
import com.example.smtrick.smartnanded.interfaces.OnFragmentInteractionListener;
import com.example.smtrick.smartnanded.preferences.AppSharedPreference;
import com.example.smtrick.smartnanded.utilities.Utility;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.infideap.drawerbehavior.Advance3DDrawerLayout;
import com.squareup.picasso.Picasso;

public class Main_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnFragmentInteractionListener {

    private Advance3DDrawerLayout drawer;
    private NavigationView navigationView;
    private Fragment selectedFragement;
    private AppSharedPreference appSharedPreference;
    String mobile;
    User username1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_3d);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            toolbar.setTitleTextColor(getColor(R.color.drower_icon_color));
        }

        appSharedPreference = new AppSharedPreference(this);

        mobile = appSharedPreference.getMobileNo();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //NOTE:  Checks first item in the navigation drawer initially
        navigationView.setCheckedItem(R.id.home);

        updateNavigationHeader();
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        Boolean per = isStoragePermissionGranted();
        if (per) {
            //   Toast.makeText(this, "Storage Premission Granted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Storage Premission Required", Toast.LENGTH_SHORT).show();
        }

        drawer = (Advance3DDrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer.setViewScale(Gravity.START, 0.96f);
        drawer.setRadius(Gravity.START, 20);
        drawer.setViewElevation(Gravity.START, 8);
        drawer.setViewRotation(Gravity.START, 15);

        selectedFragement = new Fragment_Home();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrame, selectedFragement);
        ft.commit();


    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                //   Log.v(TAG,"Permission is granted");
                return true;
            } else {

                // Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {
                    return true;
                } else {
                    return false;
                }
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            //  Log.v(TAG,"Permission is granted");
            return true;
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        //NOTE: creating fragment object
        Fragment fragment = null;
        if (id == R.id.home) {
            fragment = new Fragment_Home();
        }
        if (id == R.id.upload_product) {
            fragment = new Fragment_Upload_Products();
        }
        if (id == R.id.upload_add) {
            fragment = new Fragment_Upload_Offers();
        }
        if (id == R.id.reports) {
            fragment = new Fragment_Reports();
        }
        if (id == R.id.orders) {
            fragment = new Orders_Tab_Fragment();
        }
        if(id == R.id.myorders){
            fragment = new My_Orders_Tab_Fragment();
        }
        if (id == R.id.logout) {

            clearDataWithSignOut();


        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(String title) {

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.action_right_drawer:
//                drawer.openDrawer(Gravity.END);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }


    public void updateNavigationHeader() {
        try {
            View header = navigationView.getHeaderView(0);

            final TextView textViewName = (TextView) header.findViewById(R.id.textViewuserName);
            final TextView textViewMobileNumber = (TextView) header.findViewById(R.id.textViewMobile);
            ImageView imageViewProfile = (ImageView) header.findViewById(R.id.memberImage);
            textViewName.setText(appSharedPreference.getUserName());
            textViewMobileNumber.setText(appSharedPreference.getMobileNo());

            DatabaseReference Dref = FirebaseDatabase.getInstance().getReference("users");
            Dref.orderByChild("mobileNumber").equalTo(mobile).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
                        username1 = appleSnapshot.getValue(User.class);
                        textViewName.setText(username1.getUserName());
                        textViewMobileNumber.setText(username1.getMobileNumber());

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
                Query applesQuery1 = ref1.child("users").orderByChild("mobilenumber").equalTo(user.getPhoneNumber());

                applesQuery1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
                            User username = appleSnapshot.getValue(User.class);
                            textViewName.setText(username.getUserName());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //    Log.e(TAG, "onCancelled", databaseError.toException());
                    }
                });
                textViewMobileNumber.setText(user.getPhoneNumber());
            }


            if (!Utility.isEmptyOrNull(appSharedPreference.getProfileLargeImage())) {
                Picasso.with(this).load(appSharedPreference.getProfileLargeImage()).resize(200, 200).centerCrop().placeholder(R.drawable.user).into(imageViewProfile);
            } else
                imageViewProfile.setImageResource(R.drawable.user);


        } catch (Exception ex) {
            ExceptionUtil.logException(ex);
        }
    }

    private void clearDataWithSignOut() {
        FirebaseAuth.getInstance().signOut();
        appSharedPreference.clear();
        logOut();
    }

    private void logOut() {
        Intent intent = new Intent(this, LoginScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}
