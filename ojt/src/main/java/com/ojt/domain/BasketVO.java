package com.ojt.domain;

public class BasketVO {
	private int idx;
	private String memberid;
	private int amount;//amount*¼ö·®
	private int price;//amount
	private String agent; 
	private String item;
	private int qty;
	public BasketVO() {};
	public BasketVO(String memberid, int amount, int price, String agent, String item, int qty) {
		this.memberid=memberid;
		this.amount=amount;
		this.price=price;
		this.agent=agent;
		this.item=item;
		this.qty=qty;
	}
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid=memberid;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
}
