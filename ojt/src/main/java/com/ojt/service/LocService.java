package com.ojt.service;

import java.util.List;

import com.ojt.domain.ItemVO;
import com.ojt.domain.LatLngVO;
import com.ojt.domain.LocVO;

public interface LocService {
	public List<LocVO> locationList(LocVO vo )throws Exception;
	public LocVO location(LatLngVO vo)throws Exception;
	
	// 대리점 아이디로 선택
	public LocVO agent(String agentId) throws Exception;
}
