package com.ojt.repository;

import java.util.List;

import com.ojt.domain.ItemVO;

public interface ItemDAO {
	// �븮�� �� ������ ����Ʈ
	public List<ItemVO> itemList(String agent) throws Exception;
	// ������ �� ����
	public ItemVO itemDetail(String item) throws Exception;
}
