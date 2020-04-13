package com.ojt.repository;

import java.util.List;

import com.ojt.domain.BasketVO;
import com.ojt.domain.SaleItemVO;
import com.ojt.domain.SaleVO;

public interface SaleDAO {
	public void sale(SaleVO vo) throws Exception;
	public void saleItem(SaleItemVO vo) throws Exception;
	public void basket(BasketVO vo) throws Exception;
	public List<BasketVO> basketList(String memberid) throws Exception;
	public void deleteBasket(int idx) throws Exception;
}