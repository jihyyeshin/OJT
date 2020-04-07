package com.ojt.domain;

public class LatLngVO {
	private String location;
	private float lat;
	private float lng;
	
	public LatLngVO(String location, float lat, float lng) {
		this.location=location;
		this.lat=lat;
		this.lng=lng;
	}
	public void setLocation(String location) {
		this.location=location;
	}
	public String getLocation() {
		return location;
	}
	public void setLat(float lat) {
		this.lat=lat;
	}
	public float getLat() {
		return lat;
	}
	public void setLng(float lng) {
		this.lng=lng;
	}
	public float getLng() {
		return lng;
	}
	
}
