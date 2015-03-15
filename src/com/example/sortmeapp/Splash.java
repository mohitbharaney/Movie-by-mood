package com.example.sortmeapp;

//import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;

public class Splash extends ActionBarActivity {
	
	// Constant Value for Splash Screen time visibility
	private static int SPLASH_SCREEN_TIME = 5000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		// Create object of Handler class and call method postDelayed 
		new Handler().postDelayed(new Runnable() {
			public void run() {
				// This method will be executed when SPLASH_SCREEN_TIME is
				// over, Now you can call your Home Screen
				Intent iHomeScreen = new Intent(getApplicationContext(),MoodChoicesActivity.class);
				startActivity(iHomeScreen);
				
				// Finish Current Splash Screen, as it should be visible only once when application starts
				finish();
			}
		}, SPLASH_SCREEN_TIME);
	}
}


