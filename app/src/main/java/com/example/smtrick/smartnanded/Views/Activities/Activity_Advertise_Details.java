package com.example.smtrick.smartnanded.Views.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.smtrick.smartnanded.Models.Advertise;
import com.example.smtrick.smartnanded.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Activity_Advertise_Details extends AppCompatActivity {

    ImageView AdImage;
    TextView AdName, AdDescription;
//    Button delete_add;


    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private FirebaseStorage mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertise_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);

        AdImage = (ImageView) findViewById(R.id.Ad_image);
        AdName = (TextView) findViewById(R.id.Ad_name);
        AdDescription = (TextView) findViewById(R.id.Ad_Description);
//        delete_add = (Button) findViewById(R.id.deleteadd);

        Intent intent = getIntent();

        final String imageurl = intent.getStringExtra("imageurl");

        Query query = FirebaseDatabase.getInstance().getReference("Advertise").orderByChild("url").equalTo(imageurl);

        query.addValueEventListener(valueEventListener);

//        delete_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Advertise_Details.this);
//                builder.setMessage("Do you want to delete the record?")
//                        .setCancelable(false)
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                                mStorage = FirebaseStorage.getInstance();
//                                DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
//                                Query applesQuery1 = ref1.child("Advertise").orderByChild("desc").equalTo(imagedesc);
//
//                                applesQuery1.addListenerForSingleValueEvent(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                                        for (DataSnapshot applesnapshot : dataSnapshot.getChildren()) {
//                                            applesnapshot.getRef().removeValue();
//                                            Intent intent = new Intent(Activity_Advertise_Details.this,MainActivity.class);
//                                            startActivity(intent);
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                    }
//                                });
//
//                                StorageReference imageRef = mStorage.getReferenceFromUrl(imageurl);
//                                imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void aVoid) {
//                                        Toast.makeText(getApplicationContext(), "Item deleted", Toast.LENGTH_SHORT).show();
//
//                                    }
//                                });
//
//                            }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                            }
//                        });
//
//                TextView myMsg = new TextView(Activity_Advertise_Details.this);
//                myMsg.setText("Delete");
//                myMsg.setTextColor(Color.RED);
//                myMsg.setGravity(Gravity.CENTER);
//                myMsg.setTextSize(20);
//                AlertDialog alert = builder.create();
//                //Setting the title manually
//                alert.setCustomTitle(myMsg);
//                alert.show();
//            }
//        });//End of Onclick


    }//End of OnCreate

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                Advertise upload = postSnapshot.getValue(Advertise.class);

                AdName.setText(upload.getName());
                AdDescription.setText(upload.getDesc());
                Glide.with(Activity_Advertise_Details.this).load(upload.getUrl())
                        .placeholder(R.drawable.loading)
                        .into(AdImage);

            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
