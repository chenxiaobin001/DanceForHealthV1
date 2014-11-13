package com.example.danceforhealth;

import java.util.Collections;
import java.util.Comparator;

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
				return o1.getDay() <= (o2.getDay())?1:-1;
			}
		}
}
