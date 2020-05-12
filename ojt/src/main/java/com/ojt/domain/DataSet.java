package com.ojt.domain;

import java.util.List;

public class DataSet {
	private List<ItemVO> list;
	private int totCnt;
	private int startNum;
	
	public List<ItemVO> getList() {
		return list;
	}
	public void setList(List<ItemVO> list) {
		this.list = list;
	}
	public int getTotCnt() {
		return totCnt;
	}
	public void setTotCnt(int totCnt) {
		this.totCnt = totCnt;
	}
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	
}
