package com.ojt.repository;

import java.util.List;

import com.ojt.domain.ItemVO;
import com.ojt.domain.LatLngVO;
import com.ojt.domain.LocVO;

public interface LocDAO {
	// 위치 리스트
	public List<LocVO> locationList(LocVO vo) throws Exception;
	// 가장 가까운 위치
	public LocVO location(LatLngVO vo) throws Exception;
	// 대리점 별 아이템 리스트
	public List<ItemVO> itemList(String agent) throws Exception;
}
