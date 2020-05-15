package com.ojt.domain;
// 주문 내역 조회 시 사용
public class SaleListVO {
	private String dt_deliv;
	private char gbn_agent;
	private String cd_agent;
	private String nm_item;
	private int amt_amount;
	private String nm_agentform;
	
	public String getNm_agentform() {
		return nm_agentform;
	}
	public void setNm_agentform(String nm_agentform) {
		this.nm_agentform = nm_agentform;
	}
	public String getDt_deliv() {
		return dt_deliv;
	}
	public void setDt_deliv(String dt_deliv) {
		this.dt_deliv = dt_deliv;
	}
	public char getGbn_agent() {
		return gbn_agent;
	}
	public void setGbn_agent(char gbn_agent) {
		this.gbn_agent = gbn_agent;
	}
	public String getCd_agent() {
		return cd_agent;
	}
	public void setCd_agent(String cd_agent) {
		this.cd_agent = cd_agent;
	}
	public String getNm_item() {
		return nm_item;
	}
	public void setNm_item(String nm_item) {
		this.nm_item = nm_item;
	}
	public int getAmt_amount() {
		return amt_amount;
	}
	public void setAmt_amount(int amt_amount) {
		this.amt_amount = amt_amount;
	}
	
	
}
