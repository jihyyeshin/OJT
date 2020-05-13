package com.ojt.domain;

public class SaleItemVO {
	private String noSaleslip;
	private String dt_sale;
	private char gbn_agent;
	private int amount;
	private int price;
	private String agent; 
	private String item;
	private int qty;
	private int seq;
	private String memberid;
	private String name;
	private String delivDate;
	private int idx;
	
	public SaleItemVO(){};
	
	public SaleItemVO(String noSaleslip, String dt_sale, int amount, int price, String agent, 
			String item, int qty, int seq, String memberid, String name, String delivDate) {
		this.noSaleslip=noSaleslip;
		this.dt_sale=dt_sale;
		this.amount=amount;
		this.price=price;
		this.agent=agent;
		this.item=item;
		this.qty=qty;
		this.seq=seq;
		this.memberid=memberid;
		this.name=name;
		this.delivDate=delivDate;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getDelivDate() {
		return delivDate;
	}

	public void setDelivDate(String delivDate) {
		this.delivDate = delivDate;
	}

	public char getGbn_agent() {
		return gbn_agent;
	}

	public void setGbn_agent(char gbn_agent) {
		this.gbn_agent = gbn_agent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDt_sale() {
		return dt_sale;
	}
	public void setDt_sale(String dt_sale) {
		this.dt_sale = dt_sale;
	}
	public String getNoSaleslip() {
		return noSaleslip;
	}
	public void setNoSaleslip(String noSaleslip) {
		this.noSaleslip = noSaleslip;
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
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
}
