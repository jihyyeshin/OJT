package com.ojt.repository;

import java.util.List;

import com.ojt.domain.ItemVO;

public interface ItemDAO {
	// 대리점 별 아이템 리스트
	public List<ItemVO> itemList(String agent) throws Exception;
	// 아이템 별 정보
	public ItemVO itemDetail(String item) throws Exception;
	// 아이템 이미지 크롤링
	public void itemCrawl(ItemVO vo) throws Exception;
}
