package com.ojt.domain;

public class ItemVO {
	private String nm_item;
	private int amt_amount;

	public void setItemName(String nm_item) {
		this.nm_item=nm_item;
	}
	public String getItemName() {
		return nm_item;
	}
	public void setLat(int amt_amount) {
		this.amt_amount=amt_amount;
	}
	public int getLat() {
		return amt_amount;
	}
}
