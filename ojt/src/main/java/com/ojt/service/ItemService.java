package com.ojt.service;

import java.util.List;

import com.ojt.domain.ItemVO;

public interface ItemService {
	public List<ItemVO> itemList(String agent) throws Exception;
	public ItemVO itemDetail(String item) throws Exception;
}
