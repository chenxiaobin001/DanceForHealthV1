package com.example.danceforhealth;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

import android.net.ParseException;
import android.util.Log;


public class Util {
	// calculates the level that the person is currently at by their time
		public int calculateTotalWorkingTime() {
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
		
		//calculates what achievement the person has got based on how long he/she has been keeping working out
		public int calculateContinuousWorkingWeeks() {
			PrevWorkout workouts = PrevWorkout.getInstance();
			if (workouts.size() == 0) {
				return 0;
			}		
			Collections.sort(workouts.getPrevious(), new CustomComparator());

			int previous  = workouts.getPrevious().get(0).getWeek();
			int count = 1;
			for (Workout w : workouts.getPrevious()) {
				Log.v("workingweeks", ""+w.getWeek());
				if (w.getWeek() == previous - 1) {
					count++;
				}
		
				previous = w.getWeek();
			}
		
			return count;
		}
		 		
		
		public class CustomComparator implements Comparator<Workout> {
			@Override
			public int compare(Workout o1, Workout o2) {
				String str1 = o1.getDate();
				String str2 = o2.getDate();
				Date date1 = new Date();
				Date date2 = new Date();
				
				try {
					Log.v("dateParse", "enter");
					date1 = new SimpleDateFormat("E  MMM/dd/yyyy",Locale.ENGLISH).parse(str1);
					Log.v("dateParse", date1.toString()+"only null");
					date2 = new SimpleDateFormat("E  MMM/dd/yyyy",Locale.ENGLISH).parse(str2);
					Log.v("dateParse", date2.toString()+"only null");
				
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return date1.compareTo(date2) > 0 ? -1 : 1;
			}
		}
}
