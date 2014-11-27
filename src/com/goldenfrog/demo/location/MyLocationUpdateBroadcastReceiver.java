package com.goldenfrog.demo.location;

import com.goldenfrog.demo.utils.Constants;
import com.goldenfrog.demo.utils.GenericHelper;
import com.goldenfrog.demo.utils.LocationUtils;
import com.goldenfrog.demo.utils.UIUpdater;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import com.goldenfrog.demo.GoldenFrogDemoApplication;

public class MyLocationUpdateBroadcastReceiver extends BroadcastReceiver {

	private static MyLocationUpdateBroadcastReceiver instanceObj = null;
	
	// WTH!!! Turns out you can't have a private constructor for a BroadcastReceiver. Bye Bye Singleton!!! Welcome Faux Singleton!!!
	public MyLocationUpdateBroadcastReceiver() {
		
	}
	
	public static MyLocationUpdateBroadcastReceiver getInstance() {
		if (instanceObj == null) {
			instanceObj = new MyLocationUpdateBroadcastReceiver();
		}
		
		return instanceObj;
	}

	public void onReceive(Context context, Intent intent) {
		Log.i("MyLocationUpdateBroadcastReceiver.onReceive()", "Entered");
		
		LocationIdentifier updatedLocationTemp  = (LocationIdentifier) intent.getExtras().getSerializable("UPDATED_LOCATION");
		
		float distanceTravelledTemp = LocationUtils.getDistanceTravelledFromStartingPosition(updatedLocationTemp);
		Log.d("MyLocationUpdateBroadcastReceiver.onReceive()", "distanceTravelledTemp: " + distanceTravelledTemp);
		
		if(Constants.isDebugMode) {
			Toast.makeText(	GoldenFrogDemoApplication.getContext().getApplicationContext(),
					GenericHelper.getCurrentTime() + " - distanceTravelledTemp: " + distanceTravelledTemp, 
					Toast.LENGTH_SHORT)
					.show();				
		}

		if(distanceTravelledTemp > Constants.DISTANCE_TO_TRAVEL_BEFORE_NOTIFICATION) {
			Log.d("MyLocationUpdateBroadcastReceiver.onReceive()", "DISTANCE_TO_TRAVEL_BEFORE_NOTIFICATION Exceeded");
			
			if(Constants.isDebugMode) {
				Toast.makeText(	GoldenFrogDemoApplication.getContext().getApplicationContext(),
						"DISTANCE_TRAVELLED_THRESHOLD Exceeded", 
						Toast.LENGTH_SHORT)
						.show();				
			}
			
			UIUpdater.publish(updatedLocationTemp);

			LocationUtils.unregisterForLocationUpdates();
			LocationUtils.unregisterBroadCastReceiver();			
		}
	}

}
