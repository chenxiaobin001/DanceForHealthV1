package com.example.danceforhealth;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

import android.app.Application;

public class DanceForHealthApplication extends Application {
 
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this); 
        Parse.initialize(this, getString(R.string.app_id), getString(R.string.client_id));
 
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        ParseObject.registerSubclass(WorkoutDataStore.class);
        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);
 
        ParseACL.setDefaultACL(defaultACL, true);
    }
 
}
