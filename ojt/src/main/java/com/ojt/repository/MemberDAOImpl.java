package com.ojt.repository;

import javax.inject.Inject;


import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.ojt.domain.MemberVO;

@Service
public class MemberDAOImpl implements MemberDAO {
	@Inject
	private SqlSession sql;
	
	private static String namespace="sql";
	
	@Override
	public void signup(MemberVO vo) throws Exception{
		sql.insert(namespace+".signup", vo);
	}
	
	@Override
	public MemberVO login(MemberVO vo) throws Exception{
		return sql.selectOne(namespace+".login", vo);
	}
	
	@Override
	public void loginDate(MemberVO vo) throws Exception{
		sql.update(namespace+".loginDate", vo);
	}
	
	@Override
	public void loginFail(MemberVO vo) throws Exception{
		sql.update(namespace+".loginFail", vo);
	}
	
	@Override
	public void tempPwd(MemberVO vo) throws Exception{
		sql.update(namespace+".tmpPwd", vo);
	}
	
	@Override
	public void recInit() throws Exception{
		sql.update(namespace+"recInit");
	}
}
