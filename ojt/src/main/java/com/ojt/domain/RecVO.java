package com.ojt.domain;

public class RecVO {
	
	private String agentF;
	private String agentA;
	private String memberid;
	public RecVO() {}
	public RecVO(String agentF,String agentA, String memberid) {
		this.agentA=agentA;
		this.agentF=agentF;
		this.memberid=memberid;
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
