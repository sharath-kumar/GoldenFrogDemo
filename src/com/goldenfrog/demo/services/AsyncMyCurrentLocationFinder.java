package com.goldenfrog.demo.services;

import com.goldenfrog.demo.location.LocationIdentifier;
import com.goldenfrog.demo.utils.GenericHelper;
import com.goldenfrog.demo.utils.LocationUtils;
import com.goldenfrog.demo.utils.UIUpdater;
import android.os.AsyncTask;
import android.util.Log;

public class AsyncMyCurrentLocationFinder extends AsyncTask<String, Integer, String> {

	LocationIdentifier currentLocation;
	boolean drawOnScreen = false;
	boolean storeInDatabase = false;

	public void processRequest(boolean drawOnScreenInput, boolean storeInDatabaseInput) {
		Log.i("AsyncMyCurrentLocationFinder.processRequest()", "Entered");
		
		drawOnScreen = drawOnScreenInput;
		storeInDatabase = storeInDatabaseInput;
		
		execute();
	}
	
	public void findMyCurrentPosition() {
		Log.i("AsyncMyCurrentLocationFinder.findMyCurrentPosition()", "Entered");
		
		currentLocation = MyCurrentLocationFinder.getInstance().findMyCurrentPosition();   
	}
	
	protected String doInBackground(String... urls) {
		try {
			if(GenericHelper.isNetworkConnected()) {
				findMyCurrentPosition();	
			}
		} catch(Exception err) {
			Log.e("AsyncMyCurrentLocationFinder.doInBackground()", "" + err.getLocalizedMessage());
		}
		
		return "";
	}

	protected void onPostExecute(String result) {
		Log.i("AsyncMyCurrentLocationFinder.onPostExecute()", "Entered");
		
		Log.d("AsyncMyCurrentLocationFinder.onPostExecute() : currentLocation", "" + currentLocation);
		
		if(storeInDatabase) {
			LocationUtils.setStartingPosition(currentLocation);
		}
		if(drawOnScreen) {
			UIUpdater.publish(currentLocation, false);	
		}		
   }

}
