package com.example.danceforhealth;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WeightAndStepsActivity extends Activity{
	private int weight;
	private int steps;
	private Workout workout;
	private boolean isNew;
	private TextView stepTextView;
	private EditText stepEditText;
	private TextView weightTextView;
	private EditText weightEditText;
	private Button nextButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weight_and_steps);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			workout = (Workout) extras.get("workout");
			isNew = (Boolean) extras.get("new");
			Log.v("buttonID", "= " + Integer.toString(workout.getFunIndex()) + "/" + Integer.toString(workout.getLikedIndex()) + "/" + Integer.toString(workout.getTiredIndex()));
		}
		
		stepTextView = (TextView) findViewById(R.id.stepTextView);
		stepEditText = (EditText) findViewById(R.id.stepEditText);
		weightTextView = (TextView) findViewById(R.id.weightTextView);
		weightEditText = (EditText) findViewById(R.id.weightEditText);
		nextButton = (Button) findViewById(R.id.nextButton);
		Typeface font = Typeface.createFromAsset(getAssets(), "Komika_display.ttf");
		stepTextView.setTypeface(font);
		stepEditText.setTypeface(font);
		weightTextView.setTypeface(font);
		weightEditText.setTypeface(font);
		nextButton.setTypeface(font);
		
		if(!isNew){
			String step = Integer.toString(workout.getSteps());
			stepEditText.setText(step);
			String weight = Integer.toString(workout.getWeight());
			weightEditText.setText(weight);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weight_and_steps, menu);
		return true;
	}
	
	public void onNextButtonClick(View view) {
		
		saveStepAndWeightInfo();
		Log.v("duration", "= " + workout.getTime());
	
		// create an Intent using the current Activity 
		// and the Class to be created
		Intent intent = new Intent(this, HeartRateActivity.class).putExtra("workout", workout).putExtra("new", isNew);

		// pass the Intent to the Activity, 
		// using the specified request code
		startActivity(intent);
	}
	public void onBackButtonClick(View view) {
		
		saveStepAndWeightInfo();
		// create an Intent using the current Activity 
		// and the Class to be created
		Intent intent = new Intent(this, RatingActivity.class).putExtra("workout", workout);

		// pass the Intent to the Activity, 
		// using the specified request code
		startActivity(intent);
	}
	
	public void saveStepAndWeightInfo(){
		stepEditText = (EditText) findViewById(R.id.stepEditText);
		weightEditText = (EditText) findViewById(R.id.weightEditText);
		if(!stepEditText.getText().toString().trim().equals("")) {
			steps = Integer.parseInt(stepEditText.getText().toString());
			workout.setSteps(steps);
		}
		else {
			workout.setSteps(0);
		}
		if(!weightEditText.getText().toString().trim().equals("")) {
			weight = Integer.parseInt(weightEditText.getText().toString());
			workout.setWeight(weight);

		}
		else {
			workout.setWeight(0);
		}
	}
}
