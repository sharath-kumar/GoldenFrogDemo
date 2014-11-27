package com.goldenfrog.demo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

public class GoldenFrogDemoApplication extends Application {
	
    private static Context applicationContext;
    private static Activity mainUIXActivity;
    
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext(); 
    }

    public static Context getContext() {
        return applicationContext;
    }

    public static Activity getMainUIXActivity() {
        return mainUIXActivity;
    }
    
    public static void setMainUIXActivity(Activity mainUIXActivityInput) {
    	mainUIXActivity = mainUIXActivityInput;
    }
    
}
