package com.ojt.service;

import com.ojt.domain.SaleItemVO;
import com.ojt.domain.SaleVO;

public interface SaleService {
	public void sale(SaleVO vo)throws Exception;
	public void saleItem(SaleItemVO vo) throws Exception;
}
