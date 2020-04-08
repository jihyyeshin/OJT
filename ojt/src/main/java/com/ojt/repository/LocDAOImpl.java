package com.ojt.repository;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.ojt.domain.ItemVO;
import com.ojt.domain.LatLngVO;
import com.ojt.domain.LocVO;

@Service
public class LocDAOImpl implements LocDAO{
	@Inject
	private SqlSession sql;
	
	private static String namespace="sql";
	
	@Override
	public List<LocVO> locationList(LocVO vo) throws Exception{
		return sql.selectList(namespace+".locationList", vo);
	}
	@Override
	public LocVO location(LatLngVO vo) throws Exception{
		return sql.selectOne(namespace+".location", vo);
	}
	
}
