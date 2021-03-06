package com.ojt.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ojt.domain.BasketVO;
import com.ojt.domain.SaleItemVO;
import com.ojt.domain.SaleListVO;
import com.ojt.domain.SaleVO;
import com.ojt.repository.SaleDAO;

@Service
public class SaleServiceImpl implements SaleService {
	@Inject
	private SaleDAO dao;
	
	@Override
	public void sale(SaleVO vo)throws Exception{
		dao.sale(vo);
	}
	
	@Override
	public void saleItem(SaleItemVO vo) throws Exception{
		dao.saleItem(vo);
	}
	
	@Override
	public void basket(BasketVO vo) throws Exception{
		dao.basket(vo);
	}
	
	@Override
	public List<BasketVO> basketList(String memberid) throws Exception{
		return dao.basketList(memberid);
	}
	
	@Override
	public void deleteBasket(int idx) throws Exception{
		dao.deleteBasket(idx);
	}
	
	@Override
	public void recAdd(SaleItemVO vo) throws Exception{
		dao.recAdd(vo);
	}
	@Override
	public List<SaleItemVO> showRecent(String memberid) throws Exception{
		return dao.showRecent(memberid);
	}
	@Override
	public List<SaleListVO> showSaleList(SaleItemVO vo) throws Exception{
		return dao.showSaleList(vo);
	}
	@Override
	public String getAgentName(String agent) throws Exception{
		return dao.getAgentName(agent);
	}
}
