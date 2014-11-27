package com.goldenfrog.demo.services;

import com.goldenfrog.demo.GoldenFrogDemoApplication;
import com.goldenfrog.demo.location.LocationIdentifier;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

public class MyCurrentLocationFinder {

	private static MyCurrentLocationFinder locationFinderInstance = null;

	
	private MyCurrentLocationFinder() {
		
	}
	
	public static MyCurrentLocationFinder getInstance() {
		MyCurrentLocationFinder returnValue = null;
		
		if(locationFinderInstance!=null) {
			returnValue = locationFinderInstance;
		}
		else {
			returnValue = new MyCurrentLocationFinder();
		}
		
		return returnValue;
	}

	public LocationIdentifier findMyCurrentPosition() {
		Log.d("MyCurrentLocationFinder.findMyCurrentPosition()", "Entered");
		
		LocationIdentifier currentLocation = null;		
		LocationManager locationMgr = null;
		String locationProvider = "";
		
		locationMgr = (LocationManager) GoldenFrogDemoApplication.getContext().getSystemService(Context.LOCATION_SERVICE);

	    Criteria criteria  = new Criteria();
	    criteria.setAccuracy(Criteria.ACCURACY_COARSE);
	    criteria.setAltitudeRequired(false);
	    criteria.setBearingRequired(false);
	    criteria.setCostAllowed(true);
	    criteria.setPowerRequirement(Criteria.POWER_LOW);

	    locationProvider = locationMgr.getBestProvider(criteria, true);	    
	    Location locationTemp = locationMgr.getLastKnownLocation(locationProvider);
	    
	    if(locationTemp != null) {
	        currentLocation = new LocationIdentifier(locationTemp.getLatitude(), locationTemp.getLongitude());
	    }
	    	    
	    Log.d("MyCurrentLocationFinder.findMyCurrentPosition() ::: currentLocation", ""+currentLocation);	   
	    
	    return currentLocation;
	}

}
