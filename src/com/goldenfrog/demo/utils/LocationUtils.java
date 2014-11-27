package com.goldenfrog.demo.utils;

import android.content.Context;
import android.content.IntentFilter;
import android.location.Criteria;
import android.location.LocationManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.goldenfrog.demo.GoldenFrogDemoApplication;
import com.goldenfrog.demo.location.MyLocationUpdateBroadcastReceiver;
import com.goldenfrog.demo.location.MyLocationUpdateListener;

public class LocationUtils {
	
	public static void registerForLocationUpdates() {
		Log.d("LocationUtils.registerForLocationUpdates()", "Entered");

		LocationManager locationMgr = (LocationManager) GoldenFrogDemoApplication.getContext().getSystemService(Context.LOCATION_SERVICE);
		
		Criteria criteria  = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_COARSE);
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.NO_REQUIREMENT);

		String provider = locationMgr.getBestProvider(criteria, true);
		locationMgr.requestLocationUpdates(provider, 3000, 0, MyLocationUpdateListener.getInstance());	
	}

	public static void registerBroadCastReceiver() {
		Log.d("LocationUtils.registerBroadCastReceiver()", "Entered");

		MyLocationUpdateBroadcastReceiver locationChangeReceiver = MyLocationUpdateBroadcastReceiver.getInstance();
		IntentFilter locationChangeIntentFilter = new IntentFilter(Constants.LOCATION_CHANGE_INTENT);
		LocalBroadcastManager.getInstance(GoldenFrogDemoApplication.getContext()).registerReceiver(locationChangeReceiver, locationChangeIntentFilter);
	}

	public static void unregisterForLocationUpdates() {
		Log.d("LocationUtils.unregisterForLocationUpdates()", "Entered");
		
		LocationManager locationMgr = (LocationManager) GoldenFrogDemoApplication.getContext().getSystemService(Context.LOCATION_SERVICE);

		locationMgr.removeUpdates(MyLocationUpdateListener.getInstance());
	}

	public static void unregisterBroadCastReceiver() {
		Log.d("LocationUtils.unregisterBroadCastReceiver()", "Entered");
		
		LocalBroadcastManager.getInstance(GoldenFrogDemoApplication.getContext()).unregisterReceiver(MyLocationUpdateBroadcastReceiver.getInstance());
	}


}
