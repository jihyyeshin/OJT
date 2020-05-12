package com.ojt.repository;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.ojt.domain.ItemVO;
import com.ojt.domain.RecVO;
import com.ojt.domain.SaleVO;

@Service
public class ItemDAOImpl implements ItemDAO {
	@Inject
	private SqlSession sql;
	
	private static String namespace="sql";
	
	@Override
	public List<ItemVO> itemList(RecVO vo) throws Exception{
		return sql.selectList(namespace+".itemList",vo);
	}
	
	@Override
	public ItemVO itemDetail(String item) throws Exception{
		return sql.selectOne(namespace+".itemDetail", item);
	}
	@Override
	public void itemCrawl(ItemVO vo) throws Exception{
		sql.update(namespace+".itemCrawl", vo);
	}
	@Override
	public List<ItemVO> itemRecommendList(RecVO vo) throws Exception{
		return sql.selectList(namespace+".itemRecommendList", vo);
	}
	@Override
	public int getListCnt(RecVO vo) throws Exception{
		return sql.selectOne(namespace+".getListCnt", vo);
	}
	@Override
	public List<ItemVO> itemLvlList(RecVO vo) throws Exception{
		System.out.println("itemLvlListDAO");
		return sql.selectList(namespace+".itemLvlList", vo);
	}
}
