package com.ojt.repository;

import com.ojt.domain.SaleItemVO;
import com.ojt.domain.SaleVO;

public interface SaleDAO {
	public void sale(SaleVO vo) throws Exception;
	public void saleItem(SaleItemVO vo) throws Exception;
}
