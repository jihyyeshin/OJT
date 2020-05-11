package com.ojt.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ojt.domain.ItemVO;
import com.ojt.domain.RecVO;
import com.ojt.domain.SaleVO;
import com.ojt.repository.ItemDAO;

@Service
public class ItemServiceImpl implements ItemService{
	@Inject
	private ItemDAO dao;
	
	@Override
	public List<ItemVO> itemList(RecVO vo) throws Exception{
		return dao.itemList(vo);
	}
	@Override
	public ItemVO itemDetail(String item) throws Exception{
		return dao.itemDetail(item);
	}
	@Override
	public void itemCrawl(ItemVO vo) throws Exception{
		dao.itemCrawl(vo);
	}
	@Override
	public List<ItemVO> itemRecommendList(RecVO vo) throws Exception{
		return dao.itemRecommendList(vo);
	}
}
