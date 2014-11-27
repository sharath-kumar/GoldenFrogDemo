package com.goldenfrog.demo.activities;

import java.io.Serializable;
import com.goldenfrog.demo.services.AsyncMyCurrentLocationFinder;
import com.goldenfrog.demo.utils.LocationUtils;
import com.goldenfrog.demo.utils.UIUpdater;
import android.app.Activity;
import com.goldenfrog.demo.GoldenFrogDemoApplication;
import com.goldenfrog.demo.R;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity implements Serializable {

	private static final long serialVersionUID = 1L;

	protected void onCreate(Bundle savedInstanceState) {
		Log.d("MainActivity.onCreate()", "Entered");
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		GoldenFrogDemoApplication.setMainUIXActivity(this);
		
		drawInitialLocationOnScreen();
		
		LocationUtils.registerForLocationUpdates();
		LocationUtils.registerBroadCastReceiver();
	}

	public void drawInitialLocationOnScreen() {
		Log.d("MainActivity.drawInitialLocationOnScreen()", "Entered");
		
		AsyncMyCurrentLocationFinder asyncCurrentLocationFinderObj = new AsyncMyCurrentLocationFinder();
		asyncCurrentLocationFinderObj.processRequest(true, true);
	}

	protected void onStop() {
		Log.d("MainActivity.onStop()", "Entered");
		super.onStop();
		
		UIUpdater.setBackgroundMode(true);
	}

	protected void onResume() {
		Log.d("MainActivity.onResume()", "Entered");
		super.onResume();
		
		UIUpdater.setBackgroundMode(false);
	}

	protected void onDestroy() {
		Log.d("MainActivity.onDestroy()", "Entered");
		super.onDestroy();
		
		LocationUtils.unregisterBroadCastReceiver();
		LocationUtils.unregisterForLocationUpdates();		
	}
}