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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.parse.ParseException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends ActionBarActivity {

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
	
	private DrawerLayout drawerLayout;
	private ListView listView;
	private ActionBarDrawerToggle drawerListener;
	private MyAdatper myAdatper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Intent i = new Intent(this, HomeActivity.class);
		setContentView(R.layout.activity_home);
		
		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		listView = (ListView) findViewById(R.id.drawerList);
		myAdatper = new MyAdatper(this);
		listView.setAdapter(myAdatper);
		
		achievementTextView = (TextView) findViewById(R.id.prizeTextView);
		levelTextView = (TextView) findViewById(R.id.levelView);
		levelNumTextView = (TextView) findViewById(R.id.levelNumberView);
		nextLevelMinutes = (TextView) findViewById(R.id.nextLevelView);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		newWorkoutButton = (Button) findViewById(R.id.newWorkoutButton);
		preWorkoutButton = (Button) findViewById(R.id.preWorkoutButton);
		showProgressButton = (Button) findViewById(R.id.showProgressButton);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				listView.setItemChecked(position, true);
				switch (position){
					case 2:{
						showLogin(view);
						break;
					}
					case 3:{
						showSignup(view);
						break;
					}
					case 4:{
						logout();
						break;
					}
				}
			}
			
		});
		
		drawerListener = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_action_navigation_menu, R.string.drawer_open, R.string.drawer_close){
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
		//		Toast.makeText(HomeActivity.this, "Drawer opened", Toast.LENGTH_LONG).show();
			}
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
		//		Toast.makeText(HomeActivity.this, "Drawer closed", Toast.LENGTH_LONG).show();
			}
		};
		drawerLayout.setDrawerListener(drawerListener);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		
		// VxTTguk8Dq7tZ0h1GC6gxPSabh0=

/*		PackageInfo info;
		try {
		    info = getPackageManager().getPackageInfo("com.example.danceforhealth", PackageManager.GET_SIGNATURES);
		    for (Signature signature : info.signatures) {
		        MessageDigest md;
		        md = MessageDigest.getInstance("SHA");
		        md.update(signature.toByteArray());
		        String something = new String(Base64.encode(md.digest(), 0));
		        //String something = new String(Base64.encodeBytes(md.digest()));
		        Log.e("hash key", something);
		    }
		} catch (NameNotFoundException e1) {
		    Log.e("name not found", e1.toString());
		} catch (NoSuchAlgorithmException e) {
		    Log.e("no such an algorithm", e.toString());
		} catch (Exception e) {
		    Log.e("exception", e.toString());
		}*/
		

		updateState();
		setAchievement();
//		createAnonimousUser();
		
	}
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState){
		super.onPostCreate(savedInstanceState);
		drawerListener.syncState();
	}
	
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerListener.onConfigurationChanged(newConfig);
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


		previous.setLevel(level);
		previous.setLostWeight(weightLoss);
		previous.setWorkingTime(workingTime);
		previous.setWorkingWeeks(workingWeeks);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (drawerListener.onOptionsItemSelected(item)){
			return true;
		}
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
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
	
	
	private void showLogin(View view){
		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser == null || currentUser.getObjectId() == null) {
			LoginDialog loginDialog = new LoginDialog();
			loginDialog.show(getFragmentManager(), "login");
		} else {
			Toast.makeText(getApplicationContext(), "You already signed in, welcome back " + currentUser.getUsername(), Toast.LENGTH_SHORT).show();
		}
		
	}
	
	private void showSignup(View view){
		SignupDialog signupDialog = new SignupDialog();
		signupDialog.show(getFragmentManager(), "signup");
	}
	
	private void logout(){
		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser == null || currentUser.getObjectId() == null){
			Toast.makeText(getApplicationContext(), "Already signed out!", Toast.LENGTH_SHORT).show();
		}else{
			ParseUser.logOut();
			Toast.makeText(getApplicationContext(), "Successfully log out", Toast.LENGTH_SHORT).show();
		}
	}
	

	
	private void setAchievement(){
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


		Typeface komikaFont1 = Typeface.createFromAsset(getAssets(),
				"KOMIKAX_.ttf");
		Typeface komikaFont2 = Typeface.createFromAsset(getAssets(),
				"Komika_display.ttf");

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
	
	
	
class MyAdatper extends BaseAdapter{
		
		private Context context;
		private String[] items;
		int[] images = {R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher};
		public MyAdatper(Context context){
			items = context.getResources().getStringArray(R.array.items);
			this.context = context;
		}
		@Override
		public int getCount() {
			return items.length;
		}

		@Override
		public Object getItem(int position) {
			return items[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = null;
			if (convertView == null){
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				row = inflater.inflate(R.layout.navigation_item_layout, parent, false);
			}else{
				row = convertView;
			}
			TextView titleTextView = (TextView) row.findViewById(R.id.SignupTextView);
			ImageView titleImageView = (ImageView) row.findViewById(R.id.imageView1);
			
			titleTextView.setText(items[position]);
			titleTextView.setTextAppearance(HomeActivity.this, android.R.style.TextAppearance_DeviceDefault_Large);
			titleTextView.setTextColor(Color.parseColor("#ffffff"));
			titleImageView.setImageResource(images[position]);
			return row;
		}
	
	}
	
}

