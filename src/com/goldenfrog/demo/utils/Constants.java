package com.goldenfrog.demo.utils;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public interface Constants {

	boolean isDebugMode = false;
	String LOCATION_FILE_NAME = "LocationInformation";
	String LOCATION_CHANGE_INTENT = "com.goldenfrog.demo.LOCATION_CHANGED";
//	int MINIMUM_TIME_INTERVAL_FOR_LOCATION_POLLING = 60000; // In Milliseconds
//	int MINIMUM_DISTANCE_INTERVAL_FOR_LOCATION_POLLING = 100; // In Meters
//	float DISTANCE_TO_TRAVEL_BEFORE_NOTIFICATION = (float) 1609.34 ; // 1 Mile => 1609.34 Meters
	
	int MINIMUM_TIME_INTERVAL_FOR_LOCATION_POLLING = 100; // In Milliseconds
	int MINIMUM_DISTANCE_INTERVAL_FOR_LOCATION_POLLING = 0; // In Meters	
	float DISTANCE_TO_TRAVEL_BEFORE_NOTIFICATION = (float) 1.00 ;
	
	BitmapDescriptor START_LOCATION_PIN_COLOR = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE);
	BitmapDescriptor END_LOCATION_PIN_COLOR = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
	
}
