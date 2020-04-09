package com.ojt.repository;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.ojt.domain.ItemVO;

@Service
public class ItemDAOImpl implements ItemDAO {
	@Inject
	private SqlSession sql;
	
	private static String namespace="sql";
	
	@Override
	public List<ItemVO> itemList(String agent) throws Exception{
		return sql.selectList(namespace+".itemList",agent);
	}
	
	@Override
	public ItemVO itemDetail(String item) throws Exception{
		return sql.selectOne(namespace+".itemDetail", item);
	}
}
