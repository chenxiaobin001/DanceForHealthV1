package com.example.danceforhealth;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class NewWorkoutPageSwipe extends ActionBarActivity {
	ViewPager viewPager = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.v("before_view", "afsdfdsf");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_workout_page_swipe);
		Log.v("after_view", "awefsdf");
		viewPager = (ViewPager) findViewById(R.id.pager);	
		FragmentManager fragmentManager = getSupportFragmentManager();
		viewPager.setAdapter(new MyAdapter(fragmentManager));
	}
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
	
	class MyAdapter extends FragmentStatePagerAdapter{

		public MyAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int i) {
			// TODO Auto-generated method stub
			Fragment fragment = null;
			switch (i){
				case 0: {
					fragment = new FragmentA();
					break;
				}
				case 1: {
					fragment = new FragmentB();
					break;
				}
				case 2:{
					fragment = new FragmentC();
					break;
				}
			}
			return fragment;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;
		}
		
		@Override
		public CharSequence getPageTitle(int position){
			String title = new String();
			switch (position){
				case 0: {
					title = "tab1";
					break;
				}
				case 1: {
					title = "tab2";
					break;
				}
				case 2:{
					title = "tab3";
					break;
				}
			}
			return title;
		}
		
	}
}

