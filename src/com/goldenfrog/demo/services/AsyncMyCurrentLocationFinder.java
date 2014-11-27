package com.goldenfrog.demo.services;

import com.goldenfrog.demo.location.LocationIdentifier;
import com.goldenfrog.demo.utils.GenericHelper;
import com.goldenfrog.demo.utils.UIUpdater;
import android.os.AsyncTask;
import android.util.Log;

public class AsyncMyCurrentLocationFinder extends AsyncTask<String, Integer, String> {

	LocationIdentifier currentLocation;
	boolean drawOnScreen = false;
	boolean storeInDatabase = false;

	public void processRequest(boolean drawOnScreenInput, boolean storeInDatabaseInput) {
		Log.d("AsyncCurrentLocationFinder.processRequest()", "Entered");
		
		drawOnScreen = drawOnScreenInput;
		storeInDatabase = storeInDatabaseInput;
		
		execute();
	}
	
	public void findMyCurrentPosition() {
		Log.d("AsyncCurrentLocationFinder.findMyCurrentPosition()", "Entered");
		currentLocation = MyCurrentLocationFinder.getInstance().findMyCurrentPosition();   
	}
	
	protected String doInBackground(String... urls) {
		try {
			if(GenericHelper.isNetworkConnected()) {
				findMyCurrentPosition();	
			}
		} catch(Exception err) {
			Log.e("AsyncCurrentLocationFinder.doInBackground()", "" + err.getLocalizedMessage());
		}
		
		return "";
	}

	protected void onPostExecute(String result) {
		Log.d("AsyncCurrentLocationFinder.onPostExecute()", "Entered");
		Log.d("AsyncCurrentLocationFinder.onPostExecute() : currentLocation", "" + currentLocation);
		
		if(storeInDatabase) {
			GenericHelper.setStartingPosition(currentLocation);
		}
		if(drawOnScreen) {
			UIUpdater.publish(currentLocation, false);	
		}		
   }

}
