package com.ojt.domain;

public class SaleVO {
	private String noSaleslip;
	private int amount;
	private String agent;
	private String memberid;
	
	public SaleVO(String noSaleslip, int amount,String agent, String memberid) {
		this.noSaleslip=noSaleslip;
		this.amount=amount;
		this.agent=agent;
		this.memberid=memberid;
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
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
}
