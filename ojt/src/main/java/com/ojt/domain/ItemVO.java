package com.ojt.domain;

public class ItemVO {
	private String cd_item;
	private String nm_item;
	private int amt_amount;
	private String remark;//Ό³Έν
	private String src;
	
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public void setRemark(String remark) {
		this.remark=remark;
	}
	public String getRemark() {
		return remark;
	}

	public void setItem(String cd_item) {
		this.cd_item=cd_item;
	}
	public String getItem() {
		return cd_item;
	}
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
