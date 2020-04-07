package com.ojt.domain;

public class ItemVO {
	private String nm_item;
	private int amt_amount;

	public void setName(String nm_item) {
		this.nm_item=nm_item;
	}
	public String getName() {
		return nm_item;
	}
	public void setAmount(int amt_amount) {
		this.amt_amount=amt_amount;
	}
	public int getAmount() {
		return amt_amount;
	}
}
