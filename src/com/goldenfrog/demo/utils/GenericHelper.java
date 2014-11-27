package com.goldenfrog.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import com.goldenfrog.demo.GoldenFrogDemoApplication;
import com.google.android.gms.maps.model.BitmapDescriptor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class GenericHelper {
	
	private static ConnectivityManager connMgr;
	private static NetworkInfo networkInfo;
	private static Calendar calendarObj = Calendar.getInstance();
	private static SimpleDateFormat simpleDateFormatObj = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
	private static int markerCounter = 0;

	public static boolean isNetworkConnected() {
		Log.i("GenericHelper.isNetworkConnected()", "Entered");
		
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
		Log.i("GenericHelper.displayErrorMessage()", "Entered");
		
		Log.d("GenericHelper.displayErrorMessage() :: messageToDisplay", messageToDisplay);
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
	
	public static String getCurrentTime() {
		Log.i("GenericHelper.getCurrentTime()", "Entered");
		
		String currentDateTimeFormattedAsString = simpleDateFormatObj.format(calendarObj.getTime());
		
		return currentDateTimeFormattedAsString;
	}
	
	public static void forcefullyExitApplication() {
		Log.i("GenericHelper.forcefullyExitApplication()", "Entered");
		
		android.os.Process.killProcess(android.os.Process.myPid());
	}
	
	public static BitmapDescriptor getMarkerColor() {
		Log.i("GenericHelper.getMarkerColor()", "Entered");
		
		BitmapDescriptor returnValue;
		returnValue = Constants.ODD_LOCATION_PIN_COLOR;
		
		++markerCounter;
		if(markerCounter%2 == 0) {
			returnValue = Constants.EVEN_LOCATION_PIN_COLOR;
		}
		
		return returnValue;
	}

}
