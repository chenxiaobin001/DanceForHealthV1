package com.example.danceforhealth;

import java.util.ArrayList;
import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.ParseException;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PrevWorkoutActivity extends ListActivity {
	
	private List<Workout> workoutDataStore;
	private MySimpleArrayAdapter adapter;
  @Override
public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    
	PrevWorkout preWorkout = PrevWorkout.getInstance();
	List<Workout> workouts = preWorkout.getPrevious();
	
    View header = getLayoutInflater().inflate(R.layout.header, null);
    View footer = getLayoutInflater().inflate(R.layout.footer, null);
    ListView listView = getListView();
    listView.addHeaderView(header);
    listView.addFooterView(footer);
    adapter = new MySimpleArrayAdapter(this, new ArrayList<Workout>());
	setListAdapter((ListAdapter) adapter);
    TextView t = (TextView) findViewById(R.id.SignupTextView);
	Button b = (Button) findViewById(R.id.preWorkoutButton);
	
	getWorkoutDatastroe();
  }
  
	public void onBackButtonClick(View view) {
		// create an Intent using the current Activity 
		// and the Class to be created
		Intent intent = new Intent(this, HomeActivity.class);

		// pass the Intent to the Activity, 
		// using the specified request code
		startActivity(intent);
		finish();	
	}
  
  
  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    Workout item = (Workout) getListAdapter().getItem(position - 1);
    Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
    
	Intent intent = new Intent();
	Bundle b = new Bundle();
	b.putParcelable("workout", item);
	intent.putExtras(b);

	intent.setClass(v.getContext(), WorkoutSummary.class);

	// pass the Intent to the Activity, 
	// using the specified request code
	startActivity(intent);    
	finish();
  }
  
  private void getWorkoutDatastroe(){
	  workoutDataStore = new ArrayList<Workout>();
	  ParseQuery<WorkoutDataStore> query = ParseQuery.getQuery(WorkoutDataStore.class);
	  query.whereEqualTo("User", ParseUser.getCurrentUser());
	  query.fromLocalDatastore();
	  query.findInBackground(new FindCallback<WorkoutDataStore>() {
		  
		  @Override
	      public void done(List<WorkoutDataStore> workouts, ParseException e) {
	          if (e == null) {
	        	  workoutDataStore.clear();
	        	  for (WorkoutDataStore workout : workouts ){
	        		  workoutDataStore.add(workout.toWorkoutObject());
	        	  }
	        	  adapter.clear();
	        	  adapter.addAll(workoutDataStore);
	          } else {
	        	  Toast.makeText(getApplicationContext(), "fails", Toast.LENGTH_SHORT);
	          }
	      }
	  });
	  
  }
  
} 