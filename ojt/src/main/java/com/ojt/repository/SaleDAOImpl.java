package com.ojt.repository;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.ojt.domain.SaleItemVO;
import com.ojt.domain.SaleVO;

@Service
public class SaleDAOImpl implements SaleDAO {
	@Inject
	private SqlSession sql;
	
	private static String namespace="sql";
	
	@Override
	public void sale(SaleVO vo) throws Exception{
		sql.insert(namespace+".sale", vo);
	}
	
	@Override
	public void saleItem(SaleItemVO vo) throws Exception{
		sql.insert(namespace+".saleItem", vo);
	}
}
