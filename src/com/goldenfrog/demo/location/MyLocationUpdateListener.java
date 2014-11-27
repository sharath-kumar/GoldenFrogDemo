package com.goldenfrog.demo.location;

import com.goldenfrog.demo.GoldenFrogDemoApplication;
import com.goldenfrog.demo.utils.Constants;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

public class MyLocationUpdateListener implements LocationListener
{
	private static MyLocationUpdateListener instanceObj = null;
	
	private MyLocationUpdateListener() {
		
	}
	
	public static MyLocationUpdateListener getInstance() {
		if (instanceObj == null) {
			instanceObj = new MyLocationUpdateListener();
		}
		
		return instanceObj;
	}
	
    public void onLocationChanged(final Location loc) {
        Log.i("MyLocationUpdateListener.onLocationChanged()", "Entered");

        LocationIdentifier locationTempObj = new LocationIdentifier(loc.getLatitude(), loc.getLongitude());
        Log.d("MyLocationUpdateListener.onLocationChanged() ::: Updated Location", ""+loc.toString());
        
        broadcastLocationChange(locationTempObj);
    }

    public void onProviderDisabled(String provider) {
        
    }

    public void onProviderEnabled(String provider) {
        
    }

	public void onStatusChanged(String provider, int status, Bundle extras) {
		
	}
	
	public void broadcastLocationChange(LocationIdentifier newLocationInput) {
		Log.i("MyLocationUpdateListener.broadcastLocationChange()", "Entered");
		
		Intent intent = new Intent();
		intent.setAction(Constants.LOCATION_CHANGE_INTENT);
		intent.putExtra("UPDATED_LOCATION", newLocationInput);
	   
		GoldenFrogDemoApplication.getContext().sendBroadcast(intent);
	}

}