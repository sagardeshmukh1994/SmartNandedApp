package com.example.smtrick.smartnanded.Views.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smtrick.smartnanded.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity implements AnimationListener {

	// Set Duration of the Splash Screen
	long Delay = 5000;
	ImageView imageView1;
	Animation animBounce;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splashscreen);

		imageView1 = (ImageView) findViewById(R.id.image);

//		animBounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
//
//		animBounce.setAnimationListener(this);
		
		imageView1.setVisibility(View.VISIBLE);
//		imageView1.startAnimation(animBounce);

		// Create a Timer
		Timer RunSplash = new Timer();

		// Task to do when the timer ends
		TimerTask ShowSplash = new TimerTask() {
			@Override
			public void run() {
				
				
				// Close SplashScreenActivity.class
				finish();

				// Start MainActivity.class
				Intent myIntent = new Intent(SplashScreen.this, LoginScreen.class);
				startActivity(myIntent);
				overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

			}
		};

		// Start the timer
		RunSplash.schedule(ShowSplash, Delay);

	}

	public void onAnimationEnd(Animation animation) {
		// Take any action after completing the animation

		// check for zoom in animation
		if (animation == animBounce) {
		}

	}

	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}

	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	}

}
