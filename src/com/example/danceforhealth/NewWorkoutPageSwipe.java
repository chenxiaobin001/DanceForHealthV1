package com.example.danceforhealth;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import com.google.gson.Gson;
import com.parse.LogInCallback;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

=======
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
>>>>>>> 1e846774d35fa5cc5253bc3585026881331a6e30
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
<<<<<<< HEAD
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
=======
>>>>>>> 1e846774d35fa5cc5253bc3585026881331a6e30
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.opencsv.CSVWriter;
import com.opencsv.bean.BeanToCsv;
import com.opencsv.bean.ColumnPositionMappingStrategy;

public class NewWorkoutPageSwipe extends ActionBarActivity implements
		Communicator {
	private ViewPager viewPager = null;
	private PagerTitleStrip titleStrip = null;
	private MyAdapter myAdapter = null;
	private Workout workout = null;
	PrevWorkout preWorkout = PrevWorkout.getInstance();
	List<Workout> workouts = preWorkout.getPrevious();

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_workout_page_swipe);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			workout = (Workout) extras.get("workout");
		}

		getActionBar().setTitle("Workout form");
		viewPager = (ViewPager) findViewById(R.id.pager);
		titleStrip = (PagerTitleStrip) findViewById(R.id.titleStrip);
		titleStrip.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
		titleStrip.setTextColor(Color.WHITE);
		FragmentManager fragmentManager = getSupportFragmentManager();
		myAdapter = new MyAdapter(fragmentManager, workout);
		viewPager.setAdapter(myAdapter);
		viewPager.setOnPageChangeListener(pageChangeListener);
		Toast.makeText(getApplicationContext(), "Swipe to see different pages",
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		intent.setClass(this, HomeActivity.class);
		startActivity(intent);
		finish();
	}

	private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

		int currentPosition = 0;

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub

		}

	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void collectData(Workout workout) {

		workout = new Workout();
		for (int i = 0; i < myAdapter.getCount(); i++) {
			FragmentDataCollection fragment = (FragmentDataCollection) myAdapter
					.getItem(i);
			fragment.updateWorkoutInfo(workout);
		}
		workouts.add(workout);
		saveDateToDatastore(workout);
		saveDataToFile();
		Intent intent = new Intent();
		Bundle b = new Bundle();
		b.putParcelable("workout", workout);
		intent.putExtras(b);
		intent.setClass(this, WorkoutSummary.class);
		startActivity(intent);
		finish();
	}

	@Override
	public void cancelWorkoutData() {

		Intent i = new Intent(this, HomeActivity.class);
		startActivity(i);
		finish();
	}
<<<<<<< HEAD
	

	private void saveDateToDatastore(Workout workout){
		
		ParseUser user = ParseUser.getCurrentUser();
		WorkoutDataStore wds = new WorkoutDataStore();
		wds.setUser(user);
		wds.setType(workout.getType());
		wds.setStrain(workout.getStrain());
		wds.setHeartrate(workout.getHeartrate());
		wds.setSteps(workout.getSteps());
		wds.setWeight(workout.getWeight());
		wds.setWorkingTime(workout.getTime());
		wds.setLikedIndex(workout.getLikedIndex());
		wds.setFunIndex(workout.getFunIndex());
		wds.setTiredIndex(workout.getTiredIndex());
		wds.setWeek(workout.getWeek());
		wds.setDay(workout.getDay());
		wds.setWorkoutDate(workout.getDate());
		wds.setWorkoutTime(workout.getWorkoutTime());
		wds.pinInBackground( new SaveCallback( ) {
	        @Override
	        public void done( ParseException e ) {
	            if( e == null ) {
	 //             Toast.makeText(getApplicationContext(), "save to cloud", Toast.LENGTH_SHORT).show();
	            } else {
	 //           	Toast.makeText(getApplicationContext(), "save in local", Toast.LENGTH_SHORT).show();
	            }
	        }
	    } );
		wds.saveEventually(new SaveCallback( ) {
	        @Override
	        public void done( ParseException e ) {
	            if( e == null ) {
	 //               Toast.makeText(getApplicationContext(), "save to cloud", Toast.LENGTH_SHORT).show();
	            } else {
	 //           	Toast.makeText(getApplicationContext(), "save in local", Toast.LENGTH_SHORT).show();
	           }
	        }
	    } );
	 
	}
	
	private void saveDataToFile(){
		
=======

	private void saveDataToFile() {

>>>>>>> 1e846774d35fa5cc5253bc3585026881331a6e30
		Gson gson = new Gson();
		String file = "data_workout";
		String csv = "/mnt/sdcard/myfile.csv";

		try {
			CSVWriter writer = new CSVWriter(new FileWriter(csv), ',');
			String[] header = { "Type", "Date", "Duration", "HeartRate",
					"Steps", "Stain" };
			writer.writeNext(header);
			FileOutputStream fOut = openFileOutput(file, MODE_WORLD_WRITEABLE);
			String data = "";
			for (Workout w : workouts) {
				data += gson.toJson(w);
				data += "\n";
				String[] entries = { w.getType(), w.getDate(),
						w.getWorkoutTime(), "" + w.getHR(), "" + w.getSteps(),
						"" + w.getStrain() };
				writer.writeNext(entries);
			}
			fOut.write(data.getBytes());
			fOut.close();
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createAnonimousUser(){
		ParseUser user = ParseUser.getCurrentUser();
		if (user == null || !ParseAnonymousUtils.isLinked(user)){
			ParseAnonymousUtils.logIn(new LogInCallback() {
				  @Override
				  public void done(ParseUser user, ParseException e) {
				    if (e != null) {
				      Log.d("MyApp", "Anonymous login failed.");
				    } else {
				      Log.d("MyApp", "Anonymous user logged in.");
				    }
				  }
				});
		}else{
			Toast.makeText(getApplicationContext(), "Welcome back!", Toast.LENGTH_SHORT).show();
		}
	}

	class MyAdapter extends FragmentStatePagerAdapter {

		private List<Fragment> fragments;

		public MyAdapter(FragmentManager fm, Workout workout) {
			super(fm);
			this.fragments = new ArrayList<Fragment>();
			fragments.add(new FragmentTypeAndFeel());
			fragments.add(new FragmentWeightAndStep());
			fragments.add(new FragmentHeartRate());
			fragments.add(new FragmentTimeAndSubmit());
			for (int i = 0; i < fragments.size(); i++) {
				Fragment f = fragments.get(i);
				Bundle bundle = new Bundle();
				bundle.putParcelable("workout_info", workout);
				f.setArguments(bundle);
			}
		}

		@Override
		public Fragment getItem(int position) {

			return fragments.get(position);

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 4;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			String title = new String();
			switch (position) {
			case 0: {
				title = "Type/Feel";
				break;
			}
			case 1: {
				title = "Weight/Step";
				break;
			}
			case 2: {
				title = "HeartRate";
				break;
			}
			case 3: {
				title = "Date&Time";
			}
			}
			return title;
		}

	}

}
