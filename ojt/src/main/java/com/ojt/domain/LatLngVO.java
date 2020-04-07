package com.ojt.domain;

public class LatLngVO {
	private String location;
	private float lat;
	private float lng;
	private char gbn;
	
	public LatLngVO(String location, float lat, float lng, char gbn) {
		this.location=location;
		this.lat=lat;
		this.lng=lng;
		this.gbn=gbn;
	}
	public void setGbn(char gbn) {
		this.gbn=gbn;
	}
	public char getGbn() {
		return gbn;
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
