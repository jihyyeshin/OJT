package com.ojt.service;

import java.util.List;

import com.ojt.domain.ItemVO;
import com.ojt.domain.RecVO;
import com.ojt.domain.SaleVO;

public interface ItemService {
	public List<ItemVO> itemList(RecVO vo) throws Exception;
	public ItemVO itemDetail(String item) throws Exception;
	public void itemCrawl(ItemVO vo) throws Exception;
	public List<ItemVO> itemRecommendList(RecVO vo) throws Exception;
	public int getListCnt(RecVO vo) throws Exception;
}
