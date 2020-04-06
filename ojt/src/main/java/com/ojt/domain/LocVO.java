package com.ojt.domain;

public class LocVO {
	private String cd_agent;
	private String nm_agentform;
	private String addr_zip;
	private String addr_detail;
	
	public void setAgentName(String nm_agentform) {
		this.nm_agentform=nm_agentform;
	}
	public String getAgentName() {
		return nm_agentform;
	}
	public void setAgent(String cd_agent) {
		this.cd_agent=cd_agent;
	}
	public void setZip(String addr_zip) {
		this.addr_zip=addr_zip;
	}
	public void setDetail(String addr_detail) {
		this.addr_detail=addr_detail;
	}
	
	public String getAgent() {
		return cd_agent;
	}
	public String getZip() {
		return addr_zip;
	}
	public String getDetail() {
		return addr_detail;
	}
	
}
