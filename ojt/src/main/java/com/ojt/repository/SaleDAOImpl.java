package com.ojt.repository;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.ojt.domain.BasketVO;
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
	@Override
	public void basket(BasketVO vo) throws Exception{
		sql.insert(namespace+".basket", vo);
	}
	@Override
	public List<BasketVO> basketList(String memberid) throws Exception{
		return sql.selectList(namespace+".basketList", memberid);
	}
	@Override
	public void deleteBasket(int idx) throws Exception{
		//System.out.println("delete??");
		sql.delete(namespace+".deleteBasket", idx);
	}
}