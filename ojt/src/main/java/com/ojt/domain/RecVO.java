package com.ojt.domain;

public class RecVO {
	
	private String agentF;
	private String agentA;
	private String memberid;
	private int startNum;
	private int endNum;
	public RecVO() {}
	public RecVO(String agentF,String agentA, String memberid, int startNum, int endNum) {
		this.agentA=agentA;
		this.agentF=agentF;
		this.memberid=memberid;
		this.startNum=startNum;
		this.endNum=endNum;
	}
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	public int getEndNum() {
		return endNum;
	}
	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}
	public String getAgentF() {
		return agentF;
	}
	public void setAgentF(String agentF) {
		this.agentF = agentF;
	}
	public String getAgentA() {
		return agentA;
	}
	public void setAgentA(String agentA) {
		this.agentA = agentA;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	
}
