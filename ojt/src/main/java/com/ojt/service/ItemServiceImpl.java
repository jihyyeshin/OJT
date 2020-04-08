package com.ojt.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ojt.domain.ItemVO;
import com.ojt.repository.ItemDAO;

@Service
public class ItemServiceImpl implements ItemService{
	@Inject
	private ItemDAO dao;
	
	@Override
	public List<ItemVO> itemList(String agent) throws Exception{
		return dao.itemList(agent);
	}
}
