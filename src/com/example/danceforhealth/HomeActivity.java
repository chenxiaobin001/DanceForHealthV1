/*package com.example.danceforhealth;

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
		
	//	Intent i = new Intent(this, NewWorkoutActivity.class);
		
		Intent i = new Intent(this, NewWorkoutPageSwipe.class);
		startActivity(i);
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
		finish();
	}

}*/

package com.example.danceforhealth;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class HomeActivity extends Activity {

	private Button newWorkoutButton;
	private Button preWorkoutButton;
	private Button showProgressButton;
	private TextView levelTextView;
	private TextView levelNumTextView;
	private TextView nextLevelMinutes;
	private ProgressBar progressBar;
	private TextView achievementTextView;
	private PopupWindow mPopupWindow;
	private Util util = new Util();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Intent i = new Intent(this, HomeActivity.class);
		setContentView(R.layout.activity_home);

		
		achievementTextView = (TextView) findViewById(R.id.prizeTextView);
		levelTextView = (TextView) findViewById(R.id.levelView);
		levelNumTextView = (TextView) findViewById(R.id.levelNumberView);
		nextLevelMinutes = (TextView) findViewById(R.id.nextLevelView);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		newWorkoutButton = (Button) findViewById(R.id.newWorkoutButton);
		preWorkoutButton = (Button) findViewById(R.id.preWorkoutButton);
		showProgressButton = (Button) findViewById(R.id.showProgressButton);


		updateState();

		// use getInstance
		State state = State.getInstance();
		int level = state.getLevel();
		int workingTime = state.getWorkingTime();
		int workingWeeks = state.getWorkingWeeks();

		// calculate level - level up every 5 hours you work out, starting level
		// is level 1
		
		
		int nextLevel = level + 1;
		int minutesUntilNext = (nextLevel - 1) * 300 - workingTime;
		levelNumTextView.setText(" " + level);
		levelNumTextView.setTextColor(Color.WHITE);
		
		nextLevelMinutes.setText("Work out for " + minutesUntilNext
				+ " more minutes to level up");
		nextLevelMinutes.setTextColor(Color.WHITE);
		// set progress bar
		System.out.println((int) ((300 - minutesUntilNext) / 300.0 * 100));
		progressBar.setProgress((int) ((300 - minutesUntilNext) / 300.0 * 100));

		Typeface font2 = Typeface.createFromAsset(getAssets(),
				"Komika_display.ttf");
		levelTextView.setTypeface(font2);
		levelNumTextView.setTypeface(font2);
		nextLevelMinutes.setTypeface(font2);
		levelNumTextView.setTextSize(26);//
		levelTextView.setTextSize(26);//
		nextLevelMinutes.setTextSize(16);//

		// set fonts
		// welcomeTextView = (TextView) findViewById(R.id.welcomeTextView);
		
		// Button d = (Button) findViewById(R.id.dummy); <- uncomment this for
		// testing
		Typeface komikaFont1 = Typeface.createFromAsset(getAssets(),
				"KOMIKAX_.ttf");
		Typeface komikaFont2 = Typeface.createFromAsset(getAssets(),
				"Komika_display.ttf");
		// welcomeTextView.setTypeface(komikaFont1);
		//newWorkoutButton.setTypeface(komikaFont2);
		//preWorkoutButton.setTypeface(komikaFont2);
		// d.setTypeface(font_two); <- uncomment this for testing
		//showProgressButton.setTypeface(komikaFont2);
		newWorkoutButton.setTextSize(20);
		preWorkoutButton.setTextSize(20);
		showProgressButton.setTextSize(20);
		newWorkoutButton.setTextColor(Color.WHITE);
		preWorkoutButton.setTextColor(Color.WHITE);
		showProgressButton.setTextColor(Color.WHITE);

		// set achievement

		int achievement = workingWeeks / 4;
		achievementTextView.setTypeface(komikaFont2);
		achievementTextView.setTextSize(20);

		switch (achievement) {
		case 0:
			if (workingWeeks == 1) {
				achievementTextView.setText("You have been working out for "
						+ workingWeeks + " week");

			} else {
				achievementTextView.setText("You have been working out for "
						+ workingWeeks + " weeks");

			}
			break;
		case 6:
			achievementTextView
					.setText("You have been working out for half a year");
			break;
		case 12:
			achievementTextView.setText("You have been working out for 1 year");
			break;
		default:
			achievementTextView.setText("You have been working out for "
					+ achievement + " month!");
			break;
		}
	}

	private void updateState() {
		State previous = State.getInstance();
		int workingTime = util.calculateTotalWorkingTime();
		int level = workingTime / 300 + 1;
		int workingWeeks = util.calculateContinuousWorkingWeeks();
		Log.v("workingWeeks", "::::"+workingWeeks);
		int weightLoss = util.calculateWeightLoss();
		if (level > previous.getLevel()) {
			Log.v("popup", ":"+achievementTextView);
			Intent i = new Intent(this, CongratulationsActivity.class).putExtra("level", level).putExtra("workingTime", workingTime);
			startActivity(i);

			

		}
//		if (weightLoss > previous.getLostWeight()) {
//			Log.v("popup", ":"+achievementTextView);
//
//		}

		// TODO if new achievement

		previous.setLevel(level);
		previous.setLostWeight(weightLoss);
		previous.setWorkingTime(workingTime);
		previous.setWorkingWeeks(workingWeeks);
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
		Intent i = new Intent(this, NewWorkoutPageSwipe.class);

		// pass the Intent to the Activity,
		// using the specified request code
		startActivity(i);
		finish();
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
		finish();
	}

}

