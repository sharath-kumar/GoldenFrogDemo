package com.goldenfrog.demo.location;

import java.io.Serializable;

import android.util.Log;

public class LocationIdentifier implements Serializable {
	
	private static final long serialVersionUID = 1L;
	double latitude = 0.00;
	double longitude = 0.00;
	
	public LocationIdentifier(double latitudeInput, double longitudeInput) {
		setLatitude(latitudeInput);
		setLongitude(longitudeInput);
	}
	
	public void setLatitude(double latitudeInput) {
		this.latitude = latitudeInput;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLongitude(double longitudeInput) {
		this.longitude = longitudeInput;	
	}
	
	public double getLongitude() {
		return this.longitude;
	}
	
	public String toString() {
		Log.i("LocationIdentifier.toString() ::: (LAT, LON)", "(" + getLatitude() + ", " + getLongitude() + ")");
		
		return "(LAT, LON) => (" + getLatitude() + ", " + getLongitude() + ")";
	}

}
