package com.ojt.repository;

import java.util.List;

import com.ojt.domain.ItemVO;

public interface ItemDAO {
	// �븮�� �� ������ ����Ʈ
	public List<ItemVO> itemList(String agent) throws Exception;
	// ������ �� ����
	public ItemVO itemDetail(String item) throws Exception;
	// ������ �̹��� ũ�Ѹ�
	public void itemCrawl(ItemVO vo) throws Exception;
}
