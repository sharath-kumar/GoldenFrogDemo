package com.goldenfrog.demo.utils;

import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.goldenfrog.demo.GoldenFrogDemoApplication;
import com.goldenfrog.demo.location.LocationIdentifier;
import com.goldenfrog.demo.location.MyLocationUpdateBroadcastReceiver;
import com.goldenfrog.demo.location.MyLocationUpdateListener;

public class LocationUtils {
	
	public static void registerForLocationUpdates() {
		Log.i("LocationUtils.registerForLocationUpdates()", "Entered");

		LocationManager locationMgr = (LocationManager) GoldenFrogDemoApplication.getContext().getSystemService(Context.LOCATION_SERVICE);
		
		Criteria criteria  = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_COARSE);
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.NO_REQUIREMENT);

		String provider = locationMgr.getBestProvider(criteria, true);
		locationMgr.requestLocationUpdates( provider, 
											Constants.MINIMUM_TIME_INTERVAL_FOR_LOCATION_POLLING, 
											Constants.MINIMUM_DISTANCE_INTERVAL_FOR_LOCATION_POLLING, 
											MyLocationUpdateListener.getInstance());	
	}

	public static void registerBroadCastReceiver() {
		Log.i("LocationUtils.registerBroadCastReceiver()", "Entered");

		MyLocationUpdateBroadcastReceiver locationChangeReceiver = MyLocationUpdateBroadcastReceiver.getInstance();
		IntentFilter locationChangeIntentFilter = new IntentFilter(Constants.LOCATION_CHANGE_INTENT);
		LocalBroadcastManager.getInstance(GoldenFrogDemoApplication.getContext()).registerReceiver(locationChangeReceiver, locationChangeIntentFilter);
	}

	public static void unregisterForLocationUpdates() {
		Log.i("LocationUtils.unregisterForLocationUpdates()", "Entered");
		
		LocationManager locationMgr = (LocationManager) GoldenFrogDemoApplication.getContext().getSystemService(Context.LOCATION_SERVICE);

		locationMgr.removeUpdates(MyLocationUpdateListener.getInstance());
	}

	public static void unregisterBroadCastReceiver() {
		Log.i("LocationUtils.unregisterBroadCastReceiver()", "Entered");
		
		LocalBroadcastManager.getInstance(GoldenFrogDemoApplication.getContext()).unregisterReceiver(MyLocationUpdateBroadcastReceiver.getInstance());
	}

	public static void setStartingPosition(LocationIdentifier locationInformationInput) {
		Log.i("LocationUtils.registerForLocationUpdates()", "Entered");
		
	    SharedPreferences settings = GoldenFrogDemoApplication.getContext().getSharedPreferences(Constants.LOCATION_FILE_NAME, 0);
	    SharedPreferences.Editor editor = settings.edit();
	    editor.putString("START_LATITUDE", ""+locationInformationInput.getLatitude());
	    editor.putString("START_LONGITUDE", ""+locationInformationInput.getLongitude());
	    editor.commit();
	    editor.apply();
	}
	
	public static LocationIdentifier getStartingPosition() {
		Log.i("LocationUtils.getStartingPosition()", "Entered");
		
	    SharedPreferences settings = GoldenFrogDemoApplication.getContext().getSharedPreferences(Constants.LOCATION_FILE_NAME, 0);
	    double latitude = Double.parseDouble(settings.getString("START_LATITUDE", "0.00"));
	    double longitude = Double.parseDouble(settings.getString("START_LONGITUDE", "0.00"));

		return new LocationIdentifier(latitude, longitude);
	}
	
	public static float getDistanceTravelledFromStartingPosition(LocationIdentifier currentPosition) {
		Log.i("LocationUtils.getDistanceTravelledFromStartingPosition()", "Entered");
		
		return getDistanceBetweenLocations(currentPosition, getStartingPosition());
	}
	
	public static float getDistanceBetweenLocations(LocationIdentifier positionA, LocationIdentifier positionB) {
		Log.i("LocationUtils.getDistanceBetweenLocations()", "Entered");
		
		float returnValue = 0;
		
		Location locA = new Location("Point_A");
		locA.setLatitude(positionA.getLatitude());
		locA.setLongitude(positionA.getLongitude());
		
		Location locB = new Location("Point_B");
		locB.setLatitude(positionB.getLatitude());
		locB.setLongitude(positionB.getLongitude());
		
		returnValue = locB.distanceTo(locA);
		
		return returnValue;
	}

}
