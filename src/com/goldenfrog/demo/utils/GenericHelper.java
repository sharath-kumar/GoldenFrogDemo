package com.goldenfrog.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.goldenfrog.demo.GoldenFrogDemoApplication;
import com.goldenfrog.demo.location.LocationIdentifier;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class GenericHelper {
	
	private static ConnectivityManager connMgr;
	private static NetworkInfo networkInfo;
	private static Calendar calendarObj = Calendar.getInstance();
	private static SimpleDateFormat simpleDateFormatObj = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

	public static boolean isNetworkConnected() {
		boolean returnValue = false;
		
		connMgr = (ConnectivityManager) GoldenFrogDemoApplication.getContext().getSystemService(Activity.CONNECTIVITY_SERVICE);
		
		if(connMgr!=null) {
			networkInfo = connMgr.getActiveNetworkInfo();
			if (networkInfo != null && networkInfo.isConnected()) {
				returnValue = true;
			}
		}

		return returnValue;
	}
	
	public static void displayErrorMessage(Activity invokingActivity, String messageToDisplay) {
		Log.d("AsyncHttpRequest.displayErrorMessage() :: messageToDisplay", messageToDisplay);
		
        AlertDialog.Builder errorAlertMessage  = new AlertDialog.Builder(invokingActivity);

        errorAlertMessage.setMessage(messageToDisplay);
        errorAlertMessage.setTitle("Ooopsie!!!");
        errorAlertMessage.setPositiveButton("OK", null);
        errorAlertMessage.setCancelable(true);
        errorAlertMessage.create().show();

        errorAlertMessage.setPositiveButton("Ok",
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                	// For now, do nothing!
            }
        });
	}
	
	public static void setStartingPosition(LocationIdentifier locationInformationInput) {
	    SharedPreferences settings = GoldenFrogDemoApplication.getContext().getSharedPreferences(Constants.LOCATION_FILE_NAME, 0);
	    SharedPreferences.Editor editor = settings.edit();
	    editor.putString("START_LATITUDE", ""+locationInformationInput.getLatitude());
	    editor.putString("START_LONGITUDE", ""+locationInformationInput.getLongitude());
	    editor.commit();
	    editor.apply();
	}
	
	public static LocationIdentifier getStartingPosition() {		
	    SharedPreferences settings = GoldenFrogDemoApplication.getContext().getSharedPreferences(Constants.LOCATION_FILE_NAME, 0);
	    double latitude = Double.parseDouble(settings.getString("START_LATITUDE", "0.00"));
	    double longitude = Double.parseDouble(settings.getString("START_LONGITUDE", "0.00"));

		return new LocationIdentifier(latitude, longitude);
	}
	
	public static float getDistanceTravelledFromStartingPosition(LocationIdentifier currentPosition) {
		return getDistanceBetweenLocations(currentPosition, getStartingPosition());
	}
	
	public static float getDistanceBetweenLocations(LocationIdentifier positionA, LocationIdentifier positionB) {
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
	
	public static String getCurrentTime() {
		String currentDateTimeFormattedAsString = simpleDateFormatObj.format(calendarObj.getTime());
		
		return currentDateTimeFormattedAsString;
	}
	
	public static void forcefullyExitApplication() {
		android.os.Process.killProcess(android.os.Process.myPid());
	}

}
