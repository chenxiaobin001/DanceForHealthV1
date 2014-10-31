package com.example.danceforhealth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class LoadingScreenActivity extends Activity{
	
	private String file = "data_workout";
	private TextView welcomeTextView;
	private TextView levelTextView;

	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Intent i = new Intent(this, HomeActivity.class);
		setContentView(R.layout.loading_screen);
		
		try{
			
		//	Toast.makeText(getBaseContext(),"file read",
			//		Toast.LENGTH_SHORT).show();
			readWorkoutFromFile(file);
			Log.v("home", "read from log file");
		}catch(Exception e){

		}

		CountDownTimer timer = new CountDownTimer(3000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
					
			}

			@Override
			public void onFinish() {
				startActivity(i);
			}
			
		};
		Context context = this;
		String path = context.getFilesDir().getAbsolutePath() + "/data_workout";
		File file = new File(path);

		if (file.exists()) {
			try {
				readWorkoutFromFile(path);
				Log.v("loading", "read from log file");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			file = new File(context.getFilesDir(), "data_workout");
			Log.v("loading", "created data file");
		}
		// set fonts
		welcomeTextView = (TextView) findViewById(R.id.welcomeTextView);
		Typeface font = Typeface.createFromAsset(getAssets(), "KOMIKAX_.ttf");
		Typeface font_two = Typeface.createFromAsset(getAssets(),
				"Komika_display.ttf");
		welcomeTextView.setTypeface(font);
		
		levelTextView = (TextView) findViewById(R.id.workoutLevelTextView);
		// level up every 5 hours you work out
		// starting level is level 1
		int level = calculateTime()/300 + 1;
		int nextLevel = calculateTime()/300 + 2;
		int minutesUntilNext = nextLevel*300 - calculateTime();
		levelTextView.setText("You are currently at level " + level + " and have lost "+
				 + calculateWeightLoss() + " pounds!\nWork out for " +
				minutesUntilNext + " more minutes to level up!");
		levelTextView.setTypeface(font_two);
		//leveltxt.setTyp
		timer.start();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_workout, menu);
		return true;
	}
	
	public void addWorkout(Workout workout){
		PrevWorkout pw = PrevWorkout.getInstance();
		List<Workout> workouts = (ArrayList<Workout>) pw.getPrevious();
		workouts.add(workout);
	}
	
	public void readWorkoutFromFile(String filePath) throws Exception {
		
		String content = getStringFromFile(filePath);
		String[] contents = content.split("\n");
		
		Gson gson = new Gson();
		for (String c : contents){
			Log.v("reading", c);
			Workout workout = gson.fromJson(c, Workout.class);
			addWorkout(workout);
		}
	}
	
		
	// calculates the level that the person is currently at by their time
	public int calculateTime() {
		PrevWorkout workouts = PrevWorkout.getInstance();
		if(workouts.size() == 0) {
			return 0;
		}
		int sum = 0;
		for (Workout w : workouts.getPrevious()) {
			sum += w.getTime();
		}
		return sum;
	}

	// calculates the level the person is currently at by their weight loss
	public int calculateWeightLoss() {
		PrevWorkout workouts = PrevWorkout.getInstance();
		if(workouts.size()==0) {
			return 0;
		}
		int startWeight = workouts.getPrevious().get(0).getWeight();
		int finishWeight = workouts.getPrevious().get(workouts.size() - 1).getWeight();
		int weightLoss = finishWeight - startWeight;
		return weightLoss;
	}
	
	
	public String getStringFromFile(String filePath) throws Exception {
		File fl = new File(filePath);
		FileInputStream fin = new FileInputStream(fl);
		BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line).append("\n");
		}
		// Make sure you close all streams.
		reader.close();
		fin.close();
		return sb.toString();
	}

	
}
