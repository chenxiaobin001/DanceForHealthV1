package com.example.danceforhealth;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends Activity {

	private final String file = "data_workout";
	private boolean loadApp = true;
	
	private TextView welcomeTextView;
	private Button newWorkoutButton;
	private Button preWorkoutButton;
	private Button showProgressButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		Context context = this;

		// set fonts
		welcomeTextView = (TextView) findViewById(R.id.welcomeTextView);
		newWorkoutButton = (Button) findViewById(R.id.newWorkoutButton);
		preWorkoutButton = (Button) findViewById(R.id.preWorkoutButton);
		//Button d = (Button) findViewById(R.id.dummy); <- uncomment this for testing
		showProgressButton = (Button) findViewById(R.id.showProgressButton);
//		Typeface komikaFont1 = Typeface.createFromAsset(getAssets(), "KOMIKAX_.ttf");
//		Typeface komikaFont2 = Typeface.createFromAsset(getAssets(), "Komika_display.ttf");
//		welcomeTextView.setTypeface(komikaFont1);
//		newWorkoutButton.setTypeface(komikaFont2);
//		preWorkoutButton.setTypeface(komikaFont2);
		//d.setTypeface(font_two); <- uncomment this for testing
//		showProgressButton.setTypeface(komikaFont2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	public void onNewButtonClick(View view) {
		// create an Intent using the current Activity 
		// and the Class to be created
		Intent i = new Intent(this, NewWorkoutActivity.class);
		
		Intent i1 = new Intent(this, NewWorkoutPageSwipe.class);
		Log.v("before_1", "safd");
		// pass the Intent to the Activity, 
		// using the specified request code
//		startActivity(i);
		startActivity(i1);
	}

	public void onPrevButtonClick(View view) {
		// create an Intent using the current Activity 
		// and the Class to be created
		Intent i = new Intent(this, PrevWorkoutActivity.class);

		// pass the Intent to the Activity, 
		// using the specified request code
		startActivity(i);
		finish();
	}

	public void onProgressButtonClick(View view) {
		// create an Intent using the current Activity 
		// and the Class to be created
		Intent i = new Intent(this, GraphActivity.class);

		// pass the Intent to the Activity, 
		// using the specified request code
		startActivity(i);
		finish();
	}

	public void onDummyClick(View view) {
		// create an Intent using the current Activity 
		// and the Class to be created
		Intent i = new Intent(this, DummyActivity.class);

		// pass the Intent to the Activity, 
		// using the specified request code
		startActivity(i);
	}

}
