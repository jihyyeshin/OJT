package com.ojt.repository;

import java.util.List;

import com.ojt.domain.ItemVO;
import com.ojt.domain.LatLngVO;
import com.ojt.domain.LocVO;

public interface LocDAO {
	// ��ġ ����Ʈ
	public List<LocVO> locationList(LocVO vo) throws Exception;
	// ���� ����� ��ġ
	public LocVO location(LatLngVO vo) throws Exception;
	// �븮�� �� ������ ����Ʈ
	public List<ItemVO> itemList(String agent) throws Exception;
}
