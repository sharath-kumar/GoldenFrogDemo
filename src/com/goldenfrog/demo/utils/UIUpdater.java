package com.goldenfrog.demo.utils;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;
import com.goldenfrog.demo.GoldenFrogDemoApplication;
import com.goldenfrog.demo.R;
import com.goldenfrog.demo.location.LocationIdentifier;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class UIUpdater {
	
	private static Activity invokingActivity = null;
	private static boolean isBackgroundMode = false;
	private static boolean allowUIUpdates = true;
	
	public static boolean isAllowUIUpdates() {
		return allowUIUpdates;
	}

	public static void setAllowUIUpdates(boolean allowUIUpdates) {
		UIUpdater.allowUIUpdates = allowUIUpdates;
	}

	public static boolean isBackgroundMode() {
		return isBackgroundMode;
	}

	public static void setBackgroundMode(boolean isBackgroundMode) {
		UIUpdater.isBackgroundMode = isBackgroundMode;
	}

	public UIUpdater() {
		init();
	}
	
	public UIUpdater(Activity invokingActivityInput) {
		UIUpdater.invokingActivity = invokingActivityInput;
	}
	
	public static void init() {
		UIUpdater.invokingActivity = GoldenFrogDemoApplication.getMainUIXActivity();
	}

	public static Activity getInvokingActivity() {
		return invokingActivity;
	}

	public static void setInvokingActivity(Activity invokingActivity) {
		UIUpdater.invokingActivity = invokingActivity;
	}
	
	public static void publish(LocationIdentifier locationInput) {
		publish(locationInput, isBackgroundMode()) ;
	}
	
	public static void publish(LocationIdentifier locationInput, boolean isBackgroundModeInput) {
		Log.i("UIUpdater.publish()", "Entered");
		
		Log.d("UIUpdater.publish() ::: locationInput, isBackgroundMode", "(" + locationInput + ", " + isBackgroundMode + ")");
			
		if(isBackgroundModeInput && isAllowUIUpdates()) {
			displayNotification(locationInput);
		}
		if(!isBackgroundModeInput && isAllowUIUpdates()){
			displayOnMap(locationInput);
		}
	}
	
	private static void displayOnMap(LocationIdentifier locationInput) {
		Log.i("UIUpdater.displayOnMap()", "Entered");
				
		if(invokingActivity==null) {
			init();
		}

		LatLng myCurrentPosition = new LatLng(locationInput.getLatitude(), locationInput.getLongitude());
		Log.d("UIUpdater.displayOnMap() :: invokingActivity", ""+invokingActivity);
		Log.d("UIUpdater.displayOnMap() :: locationInput", ""+locationInput);
		
		if(invokingActivity!=null) {
	        // Get a handle to the Map Fragment
	        try {
	        	GoogleMap map = ((MapFragment) invokingActivity.getFragmentManager().findFragmentById(R.id.map)).getMap();
		        map.moveCamera(CameraUpdateFactory.newLatLngZoom(myCurrentPosition, 18));
		        MarkerOptions markerTemp = new MarkerOptions();
		        markerTemp.title("Location - " +locationInput);
		        markerTemp.snippet("Distance Travelled - " + LocationUtils.getDistanceTravelledFromStartingPosition(locationInput));
		        markerTemp.position(myCurrentPosition);
		        markerTemp.icon(GenericHelper.getMarkerColor());
		        map.addMarker(markerTemp);
	        } catch (Exception err) {
	        	Log.e("UIUpdater.displayOnMap() :: Exception Encountered ", ""+ err.getLocalizedMessage());
	        }
		}
   }
	
	private static void displayNotification(LocationIdentifier locationInput) {
		Log.i("UIUpdater.displayNotification()", "Entered");

		NotificationManager notificationManager = (NotificationManager) GoldenFrogDemoApplication.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
				

		Notification notifyUserObj = new Notification();
		notifyUserObj.icon = R.drawable.ic_launcher;
		
		notifyUserObj.setLatestEventInfo( GoldenFrogDemoApplication.getContext(), 
										  "Goal Achieved", 
										  "Distance Travelled = " + LocationUtils.getDistanceTravelledFromStartingPositionInMiles(locationInput), 
										  null);

		notificationManager.notify(0, notifyUserObj); 
	}

}
