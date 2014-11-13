#List of Java files in src:

* Workout.java A workout object stores all necessary information in its field, used throught app	
* LoadingScreenActivity
* HomeActivity
* NewWorkoutActivity
* RatingActivity
* WeightAndStepsActivity (User has the option of inputting number of steps they took during the workout.)
* HeartRateActvity
* HeartRateTwo
* WorkoutSummary
* PrevWorkoutActivity
* -helper PrevWorkout.java (this is the “database” of workouts. it is the singleton pattern. So whenever the app needs to access the database, it uses getInstance of PrevWorkout.)
* MySimpleArrayAdapter (used to create the list view of workouts)
* -xml header.xml, footer.xml, and row_data.xml are all used together for the PrevWorkout view (to make the list possible with the title header and the back button at the bottom)
* GraphActivity
* WeekProgressActivity
* MonthProgressActivity
* YearProgressActivity
* DummyActivity only used for testing graphs (populates the graphs with dummy data to see if they are working correctly)
