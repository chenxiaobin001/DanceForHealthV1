package com.example.danceforhealth;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;
@ParseClassName("WorkoutInfo")
public class WorkoutDataStore extends ParseObject {
	public WorkoutDataStore(){
		
	}
	
	public void setUser(ParseUser user){
		put("User", user);
	}
	public String getUserID(){
		return getString("UserID");
	}
	
	public void setUserID(String userID){
		put("UserID", userID);
	}
	public String getType() {
		return getString("Type");
	}
	
	public void setType(String type) {
		put("Type", type);
	}
	
	public int getStrain() {
		return getInt("Strain");
	}
	
	public void setStrain(int strain) {
		put("Strain", strain);
	}
	
	public int getHeartRate() {
		return getInt("HeartRate");
	}
	
	public void setHeartrate(int heartrate) {
		put("HeartRate", heartrate);
	}
	
	public int getSteps() {
		return getInt("Steps");
	}
	
	public void setSteps(int steps) {
		put("Steps", steps);
	}
	
	public int getWeight() {
		return getInt("Weight");
	}
	
	public void setWeight(int weight) {
		put("Weight", weight);
	}
	
	public String getWorkingTime() {
		return getString("WorkingTime");
	}
	
	public void setWorkingTime(int minutes) {
		put("WorkingTime", minutes);
	}
	
	public int getLikedIndex() {
		return getInt("LikedIndex");
	}

	public void setLikedIndex(int r1) {
		put("LikedIndex", r1);
	}

	public int getFunIndex() {
		return getInt("FunIndex");
	}

	public void setFunIndex(int r2) {
		put("FunIndex", r2);
	}

	public int getTiredIndex() {
		return getInt("tiredIdx");
	}

	public void setTiredIndex(int r3) {
		put("tiredIdx", r3);
	}

	public int getWeek() {
		return getInt("Week");
	}
		
	public void setWeek(int week) {
		put("Week", week);
	}
		
	public int getDay() {
		return getInt("Day");
	}

	public void setDay(int day) {
		put("Day", day);
	}

	public void setWorkoutDate(String date) {
		put("WorkoutDate", date);
	}
	
	public String getWorkoutDate() {
		return getString("WorkoutDate");
	}

	
	public String getWorkoutTime() {
		return getString("WorkoutTime");
	}

	public void setWorkoutTime(String workoutTime) {
		put("WorkoutTime", workoutTime);
	}
	
	

	

	
	

	
	
}
